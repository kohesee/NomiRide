package com.rideshare.models;

import com.rideshare.enums.DriverStatus;

public class Driver {
    private int id;
    private String name;
    private String vehicleType;
    private DriverStatus status;
    private Location location;

    public Driver(int id, String name, String vehicleType, DriverStatus status, Location location) {
        this.id = id;
        this.name = name;
        this.vehicleType = vehicleType;
        this.status = status;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        if (vehicleType == null || vehicleType.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle type cannot be null or empty");
        }
        this.vehicleType = vehicleType;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void markBusy() {
        this.status = DriverStatus.BUSY;
    }

    public void markAvailable() {
        this.status = DriverStatus.AVAILABLE;
    }

    public boolean isAvailable() {
        return this.status == DriverStatus.AVAILABLE;
    }
}
