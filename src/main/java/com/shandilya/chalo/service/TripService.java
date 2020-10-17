package com.shandilya.chalo.service;

import com.shandilya.chalo.constants.TripConstants;
import com.shandilya.chalo.dto.CabDTO;
import com.shandilya.chalo.dto.TripDTO;
import com.shandilya.chalo.exceptions.NoCabsAvailableException;
import com.shandilya.chalo.exceptions.TripNotFoundException;
import com.shandilya.chalo.model.Cab;
import com.shandilya.chalo.model.Location;
import com.shandilya.chalo.model.Rider;
import com.shandilya.chalo.model.Trip;
import com.shandilya.chalo.repository.TripRepository;
import com.shandilya.chalo.strategy.CabMatchingStrategy;
import com.shandilya.chalo.strategy.PricingStrategy;
import com.shandilya.chalo.utils.CommonUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {

    private final CabService cabService;
    private final TripRepository tripRepository;
    private final PricingStrategy pricingStrategy;
    private final CabMatchingStrategy cabMatchingStrategy;

    public void createTrip(
            @NonNull final Rider rider,
            @NonNull final Location source,
            @NonNull final Location destination) {
        final List<Cab> cabsInRange = cabService.getAllCabsInRange(source, TripConstants.MAX_TRIP_DISTANCE_MATCHING);

        final List<Cab> availableCabs = cabsInRange.stream()
                .filter(Cab::getIsAvailable)
                .collect(Collectors.toList());

        final Cab allottedCab = cabMatchingStrategy.matchCabToRider(rider, availableCabs, source, destination);

        if (allottedCab == null) throw new NoCabsAvailableException("Sorry! No Cabs Available Currently");

        final Double fare = pricingStrategy.findPrice(source, destination);

        final TripDTO trip = TripDTO.builder()
                .riderUniqueId(rider.getUniqueId())
                .cabLicencePlateNumber(allottedCab.getLicencePlate())
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
        final CabDTO cabInTrip = cabService.getCabByLicencePlate(tripDTO.get().getCabLicencePlateNumber()).mapToCabDTO();
        cabInTrip.setIsAvailable(false);
        cabService.createCab(cabInTrip.mapToCab());
        tripDTO.get().setTripStatus(TripConstants.FINISHED);
        tripRepository.save(tripDTO.get());
    }
}