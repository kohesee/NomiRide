package com.rideshare.controllers;

import com.rideshare.models.Ride;
import com.rideshare.models.Driver;
import com.rideshare.models.Rider;
import com.rideshare.services.IRideManagement;
import com.rideshare.services.RideService;
import com.rideshare.enums.VehicleType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RideController {

    @Autowired
    private IRideManagement rideManagement;

    @Autowired
    private RideService rideService;

    public static class CreateRideRequest {
        public int riderId;
        public double distance;
        public String vehicleType;
        public boolean useSurge;
        public CreateRideRequest() {}
    }

    @PostMapping("/rides")
    public ResponseEntity<Ride> createRide(@RequestBody CreateRideRequest req) {
        VehicleType vt = VehicleType.valueOf(req.vehicleType.toUpperCase());
        Ride ride = rideManagement.requestRide(req.riderId, req.distance, vt, req.useSurge);
        return ResponseEntity.ok(ride);
    }

    @GetMapping("/rides/{id}")
    public ResponseEntity<Ride> getRide(@PathVariable int id) {
        Ride ride = rideService.getRide(id);
        if (ride == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ride);
    }

    @PatchMapping("/rides/{id}/complete")
    public ResponseEntity<Ride> completeRide(@PathVariable int id) {
        Ride ride = rideManagement.completeRide(id);
        if (ride == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ride);
    }

    @GetMapping("/rides/history/{riderId}")
    public ResponseEntity<List<Ride>> history(@PathVariable int riderId) {
        return ResponseEntity.ok(rideManagement.getUserRides(riderId));
    }

    @GetMapping("/drivers/available")
    public ResponseEntity<List<Driver>> availableDrivers() {
        return ResponseEntity.ok(rideService.getAvailableDrivers());
    }

    @GetMapping("/riders/{riderId}")
    public ResponseEntity<Rider> getRider(@PathVariable int riderId) {
        Rider rider = rideManagement.getRider(riderId);
        if (rider == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rider);
    }

    @PatchMapping("/riders/{riderId}/wallet")
    public ResponseEntity<Rider> addToWallet(@PathVariable int riderId, @RequestBody WalletRequest req) {
        Rider rider = rideManagement.getRider(riderId);
        if (rider == null) {
            return ResponseEntity.notFound().build();
        }
        rider.addToWallet(req.amount);
        return ResponseEntity.ok(rider);
    }

    public static class WalletRequest {
        public double amount;
        public WalletRequest() {}
    }
}
