package com.rideshare.controllers;

import com.rideshare.models.Ride;
import com.rideshare.models.Driver;
import com.rideshare.services.RideService;
import com.rideshare.enums.VehicleType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api")
public class RideController {

    private final RideService rideService = new RideService();

    public static class CreateRideRequest {
        public String riderId;
        public double distance;
        public String vehicleType;
        public boolean useSurge;
        public CreateRideRequest() {}
    }

    @PostMapping("/rides")
    public ResponseEntity<Ride> createRide(@RequestBody CreateRideRequest req) {
        VehicleType vt = VehicleType.valueOf(req.vehicleType.toUpperCase());
        Ride ride = rideService.createRide(req.riderId, req.distance, vt, req.useSurge);
        return ResponseEntity.ok(ride);
    }

    @GetMapping("/rides/{id}")
    public ResponseEntity<Ride> getRide(@PathVariable String id) {
        return rideService.getRide(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/rides/{id}/complete")
    public ResponseEntity<Ride> completeRide(@PathVariable String id) {
        return rideService.completeRide(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/rides/history/{riderId}")
    public ResponseEntity<List<Ride>> history(@PathVariable String riderId) {
        return ResponseEntity.ok(rideService.getRideHistory(riderId));
    }

    @GetMapping("/drivers/available")
    public ResponseEntity<List<Driver>> availableDrivers() {
        return ResponseEntity.ok(rideService.getAvailableDrivers());
    }
}
