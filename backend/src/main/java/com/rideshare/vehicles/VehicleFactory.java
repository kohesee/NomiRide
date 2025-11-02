package com.rideshare.vehicles;

import com.rideshare.enums.VehicleType;

public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type) {
        switch (type) {
            case CAR:
                return new Car();
            case BIKE:
                return new Bike();
            case AUTO:
                return new Auto();
            default:
                return new Car();
        }
    }
}
