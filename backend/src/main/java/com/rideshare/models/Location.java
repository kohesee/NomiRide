package com.rideshare.models;

public class Location {
    private double latitude;
    private double longitude;
    private String address;

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double distanceTo(Location other) {
        // Haversine simplified (not exact) for demo
        double dx = this.latitude - other.latitude;
        double dy = this.longitude - other.longitude;
        return Math.sqrt(dx*dx + dy*dy);
    }
}
