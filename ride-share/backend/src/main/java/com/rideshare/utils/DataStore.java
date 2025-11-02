package com.rideshare.utils;

import com.rideshare.models.Rider;
import com.rideshare.models.Driver;
import com.rideshare.models.Ride;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataStore {
    private static final ArrayList<Rider> riders = new ArrayList<>();
    private static final ArrayList<Driver> drivers = new ArrayList<>();
    private static final ArrayList<Ride> rides = new ArrayList<>();

    public static void addRider(Rider r) { riders.add(r); }
    public static void addDriver(Driver d) { drivers.add(d); }
    public static void addRide(Ride r) { rides.add(r); }

    public static List<Rider> getRiders() { return riders; }
    public static List<Driver> getDrivers() { return drivers; }
    public static List<Ride> getRides() { return rides; }

    public static Optional<Rider> getRiderById(String id) { return riders.stream().filter(r->r.getId()!=null && r.getId().equals(id)).findFirst(); }
    public static Optional<Driver> getDriverById(String id) { return drivers.stream().filter(d->d.getId()!=null && d.getId().equals(id)).findFirst(); }
    public static Optional<Ride> getRideById(String id) { return rides.stream().filter(r->r.getId()!=null && r.getId().equals(id)).findFirst(); }

    public static void updateDriver(Driver d) {
        getDriverById(d.getId()).ifPresent(existing -> {
            existing.setName(d.getName());
            existing.setVehicleType(d.getVehicleType());
            existing.setStatus(d.getStatus());
            existing.setLocation(d.getLocation());
        });
    }

    public static void updateRider(Rider r) {
        getRiderById(r.getId()).ifPresent(existing -> {
            existing.setName(r.getName());
            existing.setPhone(r.getPhone());
            existing.setWalletBalance(r.getWalletBalance());
        });
    }

    public static void updateRide(Ride ride) {
        getRideById(ride.getId()).ifPresent(existing -> {
            existing.setDriverId(ride.getDriverId());
            existing.setFare(ride.getFare());
            existing.setStatus(ride.getStatus());
        });
    }
}
