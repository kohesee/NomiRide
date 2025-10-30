package com.rideshare.vehicles;

import com.rideshare.enums.VehicleType;

/**
 * Factory class for creating Vehicle instances.
 * Demonstrates the Factory design pattern and polymorphism.
 * Returns different vehicle types based on the VehicleType enum.
 */
public class VehicleFactory {
    
    /**
     * Creates a Vehicle instance based on the provided VehicleType.
     * This method demonstrates polymorphism - the same method call
     * returns different vehicle objects at runtime.
     * 
     * @param type The type of vehicle to create (CAR, BIKE, or AUTO)
     * @return A Vehicle instance of the specified type
     * @throws IllegalArgumentException if an unknown vehicle type is provided
     */
    public static Vehicle createVehicle(VehicleType type) {
        if (type == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null");
        }
        
        switch (type) {
            case CAR:
                return new Car();
            case BIKE:
                return new Bike();
            case AUTO:
                return new Auto();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
