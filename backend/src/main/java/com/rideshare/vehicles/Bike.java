package com.rideshare.vehicles;

/**
 * Bike class extending Vehicle.
 * Represents a bike/motorcycle vehicle type with specific multiplier and capacity.
 * Demonstrates inheritance by extending the abstract Vehicle class.
 */
public class Bike extends Vehicle {
    
    /**
     * Constructor for Bike
     */
    public Bike() {
        super("Bike");
        this.baseFareMultiplier = 0.6;
    }

    /**
     * Implementation of abstract method from Vehicle.
     * Returns the fare multiplier for bikes.
     * @return 0.6 (cheaper multiplier for bikes)
     */
    @Override
    public double getMultiplier() {
        return this.baseFareMultiplier;
    }

    /**
     * Implementation of abstract method from Vehicle.
     * Returns the passenger capacity for bikes.
     * @return 1 (only one passenger for a bike)
     */
    @Override
    public int getCapacity() {
        return 1;
    }
}
