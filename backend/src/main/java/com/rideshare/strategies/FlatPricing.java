package com.rideshare.strategies;

import com.rideshare.vehicles.Vehicle;

/**
 * Flat pricing strategy implementation.
 * Implements standard pricing without surge multipliers.
 * Demonstrates polymorphism by providing a specific implementation of PricingStrategy.
 */
public class FlatPricing implements PricingStrategy {
    
    /**
     * Calculates fare using flat pricing strategy.
     * Formula: base fare + (distance * rate * vehicle multiplier)
     * 
     * @param distance The distance of the ride in kilometers
     * @param vehicle The vehicle being used for the ride
     * @return The calculated fare amount
     */
    @Override
    public double calculateFare(double distance, Vehicle vehicle) {
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        
        double baseFare = 90.0;  // Starting fare
        double distanceRate = 15.0; // Per km rate
        
        return baseFare + (distance * distanceRate * vehicle.getMultiplier());
    }
}
