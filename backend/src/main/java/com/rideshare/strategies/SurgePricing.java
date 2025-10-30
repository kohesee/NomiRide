package com.rideshare.strategies;

import com.rideshare.vehicles.Vehicle;

/**
 * Surge pricing strategy implementation.
 * Implements higher pricing during peak demand periods.
 * Demonstrates polymorphism by providing a different implementation of PricingStrategy.
 */
public class SurgePricing implements PricingStrategy {
    
    private static final double BASE_FARE = 5.0;
    private static final double SURGE_DISTANCE_RATE = 3.0;
    
    /**
     * Calculates fare using surge pricing strategy.
     * Formula: base fare (5) + (distance * 3 * vehicle multiplier)
     * Higher rate compared to flat pricing due to surge conditions.
     * 
     * @param distance The distance of the ride in kilometers
     * @param vehicle The vehicle being used for the ride
     * @return The calculated fare amount with surge pricing
     */
    @Override
    public double calculateFare(double distance, Vehicle vehicle) {
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        
        return BASE_FARE + (distance * SURGE_DISTANCE_RATE * vehicle.getMultiplier());
    }
}
