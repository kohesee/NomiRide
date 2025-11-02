package com.rideshare.vehicles;

public class Bike extends Vehicle {
    public Bike() { this.type = "BIKE"; this.baseFareMultiplier = 0.6; }
    @Override public double getMultiplier() { return baseFareMultiplier; }
    @Override public int getCapacity() { return 1; }
}
