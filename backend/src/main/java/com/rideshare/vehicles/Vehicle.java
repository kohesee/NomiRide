package com.rideshare.vehicles;

/**
 * Abstract base class for all vehicles in the ride-sharing system.
 * Demonstrates inheritance by providing common structure for all vehicle types.
 * Each concrete vehicle must implement the pricing multiplier and capacity.
 */
public abstract class Vehicle {
    protected final String type;
    /**
     * Constructor for Vehicle
     * @param type The type of vehicle (e.g., "Car", "Bike", "Auto")
     */
    protected Vehicle(String type) {
        this.type = type;
    }

    /**
     * Abstract method to get the fare multiplier for this vehicle type.
     * Must be implemented by each concrete vehicle class.
     * @return The multiplier used in fare calculation
     */
    public abstract double getMultiplier();

    /**
     * Abstract method to get the passenger capacity for this vehicle type.
     * Must be implemented by each concrete vehicle class.
     * @return The maximum number of passengers
     */
    public abstract int getCapacity();

    /**
     * Get the vehicle type
     * @return The type of vehicle
     */
    public String getType() {
        return type;
    }
    }
