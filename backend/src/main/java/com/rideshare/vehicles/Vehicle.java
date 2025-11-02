package com.rideshare.vehicles;

public abstract class Vehicle {
    protected String type;
    protected double baseFareMultiplier;

    public String getType() { return type; }
    public double getBaseFareMultiplier() { return baseFareMultiplier; }

    public abstract double getMultiplier();
    public abstract int getCapacity();
}
