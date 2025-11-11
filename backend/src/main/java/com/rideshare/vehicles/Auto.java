package com.rideshare.vehicles;

public class Auto extends Vehicle {
    
    public Auto() {
        super("Auto");
    }

    @Override
    public double getMultiplier() {
        return 0.8;
    }

    @Override
    public int getCapacity() {
        return 3;
    }
}
