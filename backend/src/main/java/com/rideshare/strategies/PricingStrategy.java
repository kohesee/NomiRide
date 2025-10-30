package com.rideshare.strategies;

import com.rideshare.vehicles.Vehicle;

/**
 * Interface defining the pricing strategy for ride fare calculation.
 * Demonstrates polymorphism - different implementations can provide
 * different pricing behaviors using the same interface.
 */
public interface PricingStrategy {
    
    /**
     * Calculates the fare for a ride based on distance and vehicle type.
     * Different implementations will use different formulas for calculation.
     * 
     * @param distance The distance of the ride in kilometers
     * @param vehicle The vehicle being used for the ride
     * @return The calculated fare amount
     */
    double calculateFare(double distance, Vehicle vehicle);
}
