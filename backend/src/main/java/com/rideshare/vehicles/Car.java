package com.rideshare.vehicles;

/**
 * Car class extending Vehicle.
 * Represents a car vehicle type with specific multiplier and capacity.
 * Demonstrates inheritance by extending the abstract Vehicle class.
 */
public class Car extends Vehicle {
    
    /**
     * Constructor for Car
     */
    public Car() {
        super("Car");
        this.baseFareMultiplier = 1.0;
    }

    /**
     * Implementation of abstract method from Vehicle.
     * Returns the fare multiplier for cars.
     * @return 1.0 (standard multiplier for cars)
     */
    @Override
    public double getMultiplier() {
        return 1.0;
    }

    /**
     * Implementation of abstract method from Vehicle.
     * Returns the passenger capacity for cars.
     * @return 4 (maximum passengers for a car)
     */
    @Override
    public int getCapacity() {
        return 4;
    }
}
