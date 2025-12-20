package com.rideshare.strategies;

import com.rideshare.vehicles.Vehicle;

public class FlatPricing implements PricingStrategy {
    
    @Override
    public double calculateFare(double distance, Vehicle vehicle) {
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        
        double baseFare = 90.0;
        double distanceRate = 15.0;
        
        return baseFare + (distance * distanceRate * vehicle.getMultiplier());
    }
}
