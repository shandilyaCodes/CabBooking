package com.shandilya.chalo.controller;

import com.shandilya.chalo.model.Booking;
import com.shandilya.chalo.model.Rider;
import com.shandilya.chalo.model.Trip;
import com.shandilya.chalo.service.RiderService;
import com.shandilya.chalo.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rider")
public class RiderController {

    private final RiderService riderService;
    private final TripService tripService;

    @PostMapping("/register")
    public ResponseEntity<?> registerRider(@RequestBody Rider rider) {
        riderService.createRider(rider);
        return ResponseEntity.ok("");
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookCab(@RequestBody Booking booking) {
        tripService.createTrip(
                riderService.getRider(booking.getRiderId()),
                booking.getSource(),
                booking.getDestination());
        return ResponseEntity.ok("");
    }

    @GetMapping("/rides/{riderId}")
    public ResponseEntity<?> fetchRideHistory(@PathVariable("riderId") Long riderId) {
        final List<Trip> trips = riderService.getAllTrips(riderId);
        return ResponseEntity.ok(trips);
    }
}