package com.rideshare.vehicles;

public class Auto extends Vehicle {
    public Auto() { this.type = "AUTO"; this.baseFareMultiplier = 0.8; }
    @Override public double getMultiplier() { return baseFareMultiplier; }
    @Override public int getCapacity() { return 3; }
}
