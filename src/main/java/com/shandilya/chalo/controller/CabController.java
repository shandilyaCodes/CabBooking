package com.shandilya.chalo.controller;

import com.shandilya.chalo.model.Cab;
import com.shandilya.chalo.model.CabAvailability;
import com.shandilya.chalo.model.Location;
import com.shandilya.chalo.service.CabService;
import com.shandilya.chalo.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cab")
public class CabController {

    private final CabService cabService;
    private final TripService tripService;

    @PostMapping("/register")
    public ResponseEntity<Cab> registerCab(@RequestBody Cab cab) {
        cabService.createCab(cab);
        return new ResponseEntity<Cab>(cab, HttpStatus.OK);
    }

    @PostMapping("/update/location/{cabId}")
    public ResponseEntity<?> updateCabLocation(@PathVariable("cabId") Long cabId,
                                               @RequestBody Location location) {
        cabService.updateCabLocation(cabId, location);
        return ResponseEntity.ok("");
    }

    @PostMapping("/update/availability")
    public ResponseEntity<?> updateCabAvailability(@RequestBody CabAvailability cab) {
        cabService.updateCabAvailability(cab);
        return ResponseEntity.ok("");
    }

    @PostMapping("/end/trip/{tripId}")
    public ResponseEntity<?> endTrip(@PathVariable("tripId") Long tripId) {
        tripService.endTrip(tripId);
        return ResponseEntity.ok("");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCabs() {
        final List<Cab> cabs = cabService.findAllCabs();
        return ResponseEntity.ok(cabs);
    }
}