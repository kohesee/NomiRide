package com.rideshare.models;
import com.rideshare.enums.RideStatus;
import java.time.LocalDateTime;

public class Ride {
    private int id;
    private int riderId;
    private int driverId;
    private double distance;
    private double fare;
    private RideStatus status;
    private LocalDateTime timestamp;

    public Ride(int id, int riderId, double distance) {
        this.id = id;
        this.riderId = riderId;
        this.distance = distance;
        this.status = RideStatus.PENDING;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getRiderId() { return riderId; }
    public void setRiderId(int riderId) { this.riderId = riderId; }

    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }

    public double getDistance() { return distance; }
    public void setDistance(double distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        this.distance = distance;
    }

    public double getFare() { return fare; }
    public void setFare(double fare) {
        if (fare < 0) {
            throw new IllegalArgumentException("Fare cannot be negative");
        }
        this.fare = fare;
    }

    public RideStatus getStatus() { return status; }

    public LocalDateTime getTimestamp() { return timestamp; }

    public void markInProgress() {
        if (this.status != RideStatus.PENDING) {
            throw new IllegalStateException("Ride can only be started from PENDING status");
        }
        this.status = RideStatus.IN_PROGRESS;
        this.timestamp = LocalDateTime.now();
    }

    public void markCompleted() {
        if (this.status != RideStatus.PENDING && this.status != RideStatus.IN_PROGRESS) {
            throw new IllegalStateException("Ride cannot be completed from status: " + status);
        }
        this.status = RideStatus.COMPLETED;
        this.timestamp = LocalDateTime.now();
    }
}
