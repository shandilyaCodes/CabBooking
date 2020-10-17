package com.shandilya.chalo.service;

import com.shandilya.chalo.constants.TripConstants;
import com.shandilya.chalo.dto.CabDTO;
import com.shandilya.chalo.dto.TripDTO;
import com.shandilya.chalo.exceptions.CabNotFoundException;
import com.shandilya.chalo.exceptions.NoCabsAvailableException;
import com.shandilya.chalo.exceptions.RiderAlreadyInTripException;
import com.shandilya.chalo.exceptions.TripNotFoundException;
import com.shandilya.chalo.model.Cab;
import com.shandilya.chalo.model.Location;
import com.shandilya.chalo.model.Rider;
import com.shandilya.chalo.repository.CabRepository;
import com.shandilya.chalo.repository.TripRepository;
import com.shandilya.chalo.strategy.CabMatchingStrategy;
import com.shandilya.chalo.strategy.PricingStrategy;
import com.shandilya.chalo.utils.CommonUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {

    private final CabService cabService;
    private final CabRepository cabRepository;
    private final TripRepository tripRepository;
    private final PricingStrategy pricingStrategy;
    private final CabMatchingStrategy cabMatchingStrategy;

    public void createTrip(
            @NonNull final Rider rider,
            @NonNull final Location source,
            @NonNull final Location destination) {

        final List<TripDTO> riders = tripRepository.findByRiderId(rider.getId());
        final List<TripDTO> filteredTrips = riders.stream()
                .filter(tripDTO -> tripDTO.getTripStatus().equalsIgnoreCase(TripConstants.IN_PROGRESS))
                .collect(Collectors.toList());
        if (filteredTrips.size() > 0) {
            throw new RiderAlreadyInTripException("Rider Is Already Taking A Trip, Can't book another trip!");
        }

        final List<Cab> cabsInRange = cabService.getAllCabsInRange(source, TripConstants.MAX_TRIP_DISTANCE_MATCHING);

        final List<Cab> availableCabs = cabsInRange.stream()
                .filter(Cab::getIsAvailable)
                .collect(Collectors.toList());

        final Cab allottedCab = cabMatchingStrategy.matchCabToRider(rider, availableCabs, source, destination);

        if (allottedCab == null) {
            throw new NoCabsAvailableException("Sorry! No Cabs Available Currently");
        }

        final Optional<CabDTO> cab = cabRepository.findById(allottedCab.getId());

        if (!cab.isPresent()) {
            throw new CabNotFoundException("Cab not found!");
        }

        final CabDTO cabDTO = cab.get();
        cabDTO.setIsAvailable(false);
        cabRepository.save(cabDTO);

        final Double fare = pricingStrategy.findPrice(source, destination);

        final TripDTO trip = TripDTO.builder()
                .riderId(rider.getId())
                .cabId(allottedCab.getId())
                .fare(fare)
                .sourceLocation(CommonUtils.locationToString(source))
                .destinationLocation(CommonUtils.locationToString(destination))
                .tripStatus(TripConstants.IN_PROGRESS)
                .build();

        tripRepository.save(trip);
    }

    public void endTrip(@NonNull final Long tripId) {
        final Optional<TripDTO> tripDTO = tripRepository.findById(tripId);
        if (!tripDTO.isPresent()) {
            throw new TripNotFoundException("Trip with the ID : " + tripId + " Not Found!");
        }
        final Optional<CabDTO> cab = cabRepository.findById(tripDTO.get().getCabId());
        if (!cab.isPresent()) {
            throw new CabNotFoundException("Cab with ID not found!");
        }
        final CabDTO cabInTrip = cab.get();
        cabInTrip.setIsAvailable(true);
        cabRepository.save(cabInTrip);
        tripDTO.get().setTripStatus(TripConstants.FINISHED);
        tripRepository.save(tripDTO.get());
    }
}