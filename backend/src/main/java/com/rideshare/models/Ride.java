package com.rideshare.models;
import com.rideshare.enums.RideStatus;
import java.time.LocalDateTime;

public class Ride {
    // Private fields
    private int id;
    private int riderId;
    private int driverId;
    private double distance;
    private double fare;
    private RideStatus status;
    private LocalDateTime timestamp;

    // Constructor (initial status is PENDING)
    public Ride(int id, int riderId, double distance) {
        this.id = id;
        this.riderId = riderId;
        this.distance = distance;
        this.status = RideStatus.PENDING;
    }

    // Getters and setters for id, riderId, driverId
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getRiderId() { return riderId; }
    public void setRiderId(int riderId) { this.riderId = riderId; }

    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }

    // Getter and setter for distance
    public double getDistance() { return distance; }
    public void setDistance(double distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        this.distance = distance;
    }

    // Getter and setter for fare (with validation)
    public double getFare() { return fare; }
    public void setFare(double fare) {
        if (fare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }
        this.fare = fare;
    }

    // Getter for status (no direct setter to force use of methods)
    public RideStatus getStatus() { return status; }

    // Getter for timestamp
    public LocalDateTime getTimestamp() { return timestamp; }

    // Mark the ride as in progress (driver has picked up the rider)
    public void markInProgress() {
        if (this.status != RideStatus.PENDING) {
            throw new IllegalStateException("Ride can only be started from PENDING status");
        }
        this.status = RideStatus.IN_PROGRESS;
        this.timestamp = LocalDateTime.now(); // record start time
    }

    // Mark the ride as completed if possible
    public void markCompleted() {
        // Allow completion from both PENDING (for quick testing) and IN_PROGRESS
        if (this.status != RideStatus.PENDING && this.status != RideStatus.IN_PROGRESS) {
            throw new IllegalStateException("Ride cannot be completed from status: " + status);
        }
        this.status = RideStatus.COMPLETED;
        this.timestamp = LocalDateTime.now(); // record completion time
    }
}
