package com.rideshare.controllers;

import com.rideshare.models.Ride;
import com.rideshare.models.Driver;
import com.rideshare.models.Rider;
import com.rideshare.models.Location;
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
        public Double fare;  // Optional: if provided, use this fare instead of calculating
        public CreateRideRequest() {}
    }

    @PostMapping("/rides")
    public ResponseEntity<Ride> createRide(@RequestBody CreateRideRequest req) {
        VehicleType vt = VehicleType.valueOf(req.vehicleType.toUpperCase());
        Ride ride = rideManagement.requestRide(req.riderId, req.distance, vt, req.useSurge);
        
        // If fare is provided (from frontend), override the calculated fare
        if (req.fare != null && req.fare > 0) {
            ride.setFare(req.fare);
        }
        
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
    
    @GetMapping("/drivers/nearby")
    public ResponseEntity<List<java.util.Map<String, Object>>> getNearbyDrivers(
            @org.springframework.web.bind.annotation.RequestParam double fromLat,
            @org.springframework.web.bind.annotation.RequestParam double fromLon,
            @org.springframework.web.bind.annotation.RequestParam double toLat,
            @org.springframework.web.bind.annotation.RequestParam double toLon,
            @org.springframework.web.bind.annotation.RequestParam(defaultValue = "false") boolean useSurge) {
        
        Location fromLocation = new Location(fromLat, fromLon, "Pickup");
        Location toLocation = new Location(toLat, toLon, "Dropoff");
        double tripDistance = fromLocation.distanceTo(toLocation);
        
        // Select pricing strategy based on surge parameter
        com.rideshare.strategies.PricingStrategy pricingStrategy = useSurge 
            ? new com.rideshare.strategies.SurgePricing() 
            : new com.rideshare.strategies.FlatPricing();
        
        List<java.util.Map<String, Object>> result = new java.util.ArrayList<>();
        
        for (Driver driver : rideService.getAvailableDrivers()) {
            Location driverLoc = driver.getLocation();
            double distanceToPickup = driverLoc.distanceTo(fromLocation);
            
            VehicleType vType = VehicleType.valueOf(driver.getVehicleType().toUpperCase());
            com.rideshare.vehicles.Vehicle vehicle = com.rideshare.vehicles.VehicleFactory.createVehicle(vType);
            
            // Calculate trip fare using pricing strategy
            double tripCost = pricingStrategy.calculateFare(tripDistance, vehicle);
            
            // Add pickup cost (₹8 per km)
            double pickupFeePerKm = 8.0;
            double pickupCost = distanceToPickup * pickupFeePerKm * vehicle.getMultiplier();
            
            double estimatedCost = tripCost + pickupCost;
            
            java.util.Map<String, Object> driverInfo = new java.util.HashMap<>();
            driverInfo.put("driver", driver);
            driverInfo.put("distanceToPickup", Math.round(distanceToPickup * 100.0) / 100.0);
            driverInfo.put("tripDistance", Math.round(tripDistance * 100.0) / 100.0);
            driverInfo.put("estimatedCost", Math.round(estimatedCost * 100.0) / 100.0);
            driverInfo.put("location", driverLoc);
            driverInfo.put("surgeActive", useSurge);
            
            result.add(driverInfo);
        }
        
        return ResponseEntity.ok(result);
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
