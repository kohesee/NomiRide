package com.rideshare.vehicles;

public abstract class Vehicle {
    protected final String type;
    protected Vehicle(String type) {
        this.type = type;
    }

    public abstract double getMultiplier();

    public abstract int getCapacity();

    public String getType() {
        return type;
    }
    }
