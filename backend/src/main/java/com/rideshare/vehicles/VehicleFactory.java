package com.rideshare.vehicles;

import com.rideshare.enums.VehicleType;

public class VehicleFactory {
    
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
