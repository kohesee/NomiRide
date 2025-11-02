package com.rideshare.strategies;

import com.rideshare.vehicles.Vehicle;

public class FlatPricing implements PricingStrategy {
    @Override
    public double calculateFare(double distance, Vehicle vehicle) {
        return 5 + distance * 2 * vehicle.getMultiplier();
    }
}
