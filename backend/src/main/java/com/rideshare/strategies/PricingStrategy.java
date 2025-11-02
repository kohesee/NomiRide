package com.rideshare.strategies;

import com.rideshare.vehicles.Vehicle;

public interface PricingStrategy {
    double calculateFare(double distance, Vehicle vehicle);
}
