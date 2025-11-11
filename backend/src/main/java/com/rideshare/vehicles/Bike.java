package com.rideshare.vehicles;

public class Bike extends Vehicle {
    
    public Bike() {
        super("Bike");
    }

    @Override
    public double getMultiplier() {
        return 0.4;
    }

    @Override
    public int getCapacity() {
        return 1;
    }
}
