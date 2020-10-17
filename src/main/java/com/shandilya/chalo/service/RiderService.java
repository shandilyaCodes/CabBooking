package com.shandilya.chalo.service;

import com.shandilya.chalo.dto.RiderDTO;
import com.shandilya.chalo.dto.TripDTO;
import com.shandilya.chalo.exceptions.RiderAlreadyExistsException;
import com.shandilya.chalo.exceptions.RiderNotFoundException;
import com.shandilya.chalo.model.Rider;
import com.shandilya.chalo.model.Trip;
import com.shandilya.chalo.repository.RiderRepository;
import com.shandilya.chalo.repository.TripRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RiderService {

    private final TripRepository tripRepository;
    private final RiderRepository riderRepository;

    public void createRider(@NonNull final Rider rider) {
        final List<RiderDTO> riderDTO = riderRepository.findByUniqueId(rider.getUniqueId());
        if (!riderDTO.isEmpty()) {
            throw new RiderAlreadyExistsException("Rider with Unique ID : " + rider.getUniqueId() + " Exists!");
        }
        riderRepository.save(rider.mapToRiderDTO());
    }

    public Rider getRider(@NonNull final Long riderId) {
        final Optional<RiderDTO> riderDTO = riderRepository.findById(riderId);
        if (!riderDTO.isPresent()) {
            throw new RiderNotFoundException("Rider with uniqueID : " + riderId + " Not Found!");
        }
        return riderDTO.get().mapToRider();
    }

    public List<Trip> getAllTrips(@NonNull final String riderUniqueId) {
        return tripRepository.findByRiderUniqueId(riderUniqueId).stream()
                .map(TripDTO::mapToTrip)
                .collect(Collectors.toList());
    }
}