package com.rideshare.models;

import com.rideshare.enums.DriverStatus;

public class Driver {
    // Private fields (hidden from external access)
    private int id;
    private String name;
    private String vehicleType;
    private DriverStatus status;
    private Location location;

    // Constructor
    public Driver(int id, String name, String vehicleType, DriverStatus status, Location location) {
        this.id = id;
        this.name = name;
        this.vehicleType = vehicleType;
        this.status = status;
        this.location = location;
    }

    // Getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    // Getter and setter for vehicleType
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        if (vehicleType == null || vehicleType.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle type cannot be null or empty");
        }
        this.vehicleType = vehicleType;
    }

    // Getter and setter for status
    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }

    // Getter and setter for location
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // Mark driver as busy (controlled state change)
    public void markBusy() {
        this.status = DriverStatus.BUSY;
    }

    // Mark driver as available (controlled state change)
    public void markAvailable() {
        this.status = DriverStatus.AVAILABLE;
    }

    // Check if driver is available
    public boolean isAvailable() {
        return this.status == DriverStatus.AVAILABLE;
    }
}
