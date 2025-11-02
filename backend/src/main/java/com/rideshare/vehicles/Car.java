package com.rideshare.vehicles;

public class Car extends Vehicle {
    public Car() { this.type = "CAR"; this.baseFareMultiplier = 1.0; }
    @Override public double getMultiplier() { return baseFareMultiplier; }
    @Override public int getCapacity() { return 4; }
}
