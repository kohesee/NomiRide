package com.rideshare.vehicles;

/**
 * Auto class extending Vehicle.
 * Represents an auto-rickshaw vehicle type with specific multiplier and capacity.
 * Demonstrates inheritance by extending the abstract Vehicle class.
 */
public class Auto extends Vehicle {
    
    /**
     * Constructor for Auto
     */
    public Auto() {
        super("Auto");
        this.baseFareMultiplier = 0.8;
    }

    /**
     * Implementation of abstract method from Vehicle.
     * Returns the fare multiplier for autos.
     * @return 0.8 (moderate multiplier for auto-rickshaws)
     */
    @Override
    public double getMultiplier() {
        return 0.8;
    }

    /**
     * Implementation of abstract method from Vehicle.
     * Returns the passenger capacity for autos.
     * @return 3 (maximum passengers for an auto-rickshaw)
     */
    @Override
    public int getCapacity() {
        return 3;
    }
}
