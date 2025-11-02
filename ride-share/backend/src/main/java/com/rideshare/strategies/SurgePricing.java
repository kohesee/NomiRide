package com.rideshare.strategies;

import com.rideshare.vehicles.Vehicle;

public class SurgePricing implements PricingStrategy {
    @Override
    public double calculateFare(double distance, Vehicle vehicle) {
        return 5 + distance * 3 * vehicle.getMultiplier();
    }
}
