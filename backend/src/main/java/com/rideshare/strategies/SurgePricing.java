package com.rideshare.strategies;

import com.rideshare.vehicles.Vehicle;

/**
 * Surge pricing strategy implementation.
 * Implements higher pricing during peak demand periods.
 * Demonstrates polymorphism by providing a different implementation of PricingStrategy.
 */
public class SurgePricing implements PricingStrategy {
    
    /**
     * Calculates fare using surge pricing strategy.
     * Formula: base fare + (distance * surge rate * vehicle multiplier)
     * Higher rate compared to flat pricing due to peak demand (1.5x multiplier).
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
        
        double baseFare = 100.0;  // Same base fare
        double distanceRate = 15.0; // Base rate
        double surgeMultiplier = 1.5; // 1.5x surge during peak hours
        
        return baseFare + (distance * distanceRate * surgeMultiplier * vehicle.getMultiplier());
    }
}
