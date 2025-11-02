package com.rideshare.models;

import com.rideshare.enums.RideStatus;
import java.time.LocalDateTime;

public class Ride {
    private String id;
    private String riderId;
    private String driverId;
    private double distance;
    private double fare;
    private RideStatus status;
    private LocalDateTime timestamp;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getRiderId() { return riderId; }
    public void setRiderId(String riderId) { this.riderId = riderId; }
    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }
    public double getDistance() { return distance; }
    public void setDistance(double distance) { this.distance = distance; }
    public double getFare() { return fare; }
    public void setFare(double fare) { if (fare >= 0) this.fare = fare; }
    public RideStatus getStatus() { return status; }
    public void setStatus(RideStatus status) { this.status = status; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public void markCompleted() { this.status = RideStatus.COMPLETED; }
    public boolean canComplete() { return this.status == RideStatus.IN_PROGRESS || this.status == RideStatus.PENDING; }
}
