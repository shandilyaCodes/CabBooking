package com.shandilya.chalo.service;

import com.shandilya.chalo.dto.CabDTO;
import com.shandilya.chalo.exceptions.CabAlreadyExistsException;
import com.shandilya.chalo.exceptions.CabNotFoundException;
import com.shandilya.chalo.exceptions.NoCabsFoundInRange;
import com.shandilya.chalo.model.Cab;
import com.shandilya.chalo.model.CabAvailability;
import com.shandilya.chalo.model.Location;
import com.shandilya.chalo.repository.CabRepository;
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
public class CabService {

    private final CabRepository cabRepository;

    public void createCab(@NonNull final Cab cab) {
        if (cabRepository.findByLicencePlate(cab.getLicencePlate()).size() > 0) {
            throw new CabAlreadyExistsException("Cab With Licence Plate Number : " + cab.getLicencePlate() + " Exists!");
        }
        cabRepository.save(cab.mapToCabDTO());
    }

    /*public void updateCab(@NonNull final CabDTO cabDTO) {
        cabRepository.save(cabDTO);
    }*/

    public Cab getCabByLicencePlate(@NonNull final String licencePlateNumber) {
        final List<CabDTO> cabDTOList = cabRepository.findByLicencePlate(licencePlateNumber);
        if (cabDTOList.isEmpty()) {
            throw new CabNotFoundException("Cab with ID : " + licencePlateNumber + " is not present!");
        }
        return cabDTOList.get(0).mapToCab();
    }

    public void updateCabLocation(@NonNull final Long cabId, @NonNull final Location location) {
        final Optional<CabDTO> cab = cabRepository.findById(cabId);
        if (!cab.isPresent()) {
            throw new CabNotFoundException("Cab with ID : " + cabId + " is not present!");
        }
        final CabDTO cabDTO = cab.get();
        cabDTO.setCurrentLocation(location.getX()+","+location.getY());
        cabRepository.save(cabDTO);
    }

    public void updateCabAvailability(@NonNull final CabAvailability cabAvailability) {
        final Optional<CabDTO> cab = cabRepository.findById(cabAvailability.getCabId());
        if (!cab.isPresent()) {
            throw new CabNotFoundException("Cab with ID : " + cabAvailability.getCabId() + " is not present!");
        }
        final CabDTO cabDTO = cab.get();
        cabDTO.setIsAvailable(cabAvailability.getIsAvailable());
        cabRepository.save(cabDTO);
    }

    public List<Cab> getAllCabsInRange(@NonNull final Location source, @NonNull final Double distance) {
        List<Cab> result = new ArrayList<>();
        final List<CabDTO> allCabs = cabRepository.findAll();
        if (allCabs.isEmpty()) {
            throw new NoCabsFoundInRange("Currently there are no cabs in your area!");
        }
        for (CabDTO cabDTO : allCabs) {
            if (cabDTO.getIsAvailable() &&
                    CommonUtils.stringToLocation(cabDTO.getCurrentLocation()).distance(source) <= distance) {
                result.add(cabDTO.mapToCab());
            }
        }
        return result;
    }

    public List<Cab> findAllCabs() {
        final List<CabDTO> allCabs = cabRepository.findAll();
        return allCabs.stream()
                .map(CabDTO::mapToCab)
                .collect(Collectors.toList());
    }
}