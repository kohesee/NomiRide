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
        
        double baseFare = PricingStrategy.BASE_FARE;
        double surgeDistanceRate = 3.0; // Higher rate for surge pricing
        
        return baseFare + (distance * surgeDistanceRate * vehicle.getMultiplier());
    }
}
