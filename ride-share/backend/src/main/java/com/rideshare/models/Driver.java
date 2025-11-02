package com.rideshare.models;

import com.rideshare.enums.DriverStatus;

public class Driver {
    private String id;
    private String name;
    private String vehicleType;
    private DriverStatus status = DriverStatus.AVAILABLE;
    private Location location;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    public DriverStatus getStatus() { return status; }
    public void setStatus(DriverStatus status) { this.status = status; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    public void markBusy() { this.status = DriverStatus.BUSY; }
    public void markAvailable() { this.status = DriverStatus.AVAILABLE; }
    public boolean isAvailable() { return this.status == DriverStatus.AVAILABLE; }
}
