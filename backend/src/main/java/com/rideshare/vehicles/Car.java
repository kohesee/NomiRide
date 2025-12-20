package com.rideshare.vehicles;

public class Car extends Vehicle {
    
    public Car() {
        super("Car");
    }

    @Override
    public double getMultiplier() {
        return 1.0;
    }

    @Override
    public int getCapacity() {
        return 4;
    }
}
