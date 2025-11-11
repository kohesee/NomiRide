package com.rideshare.utils;

import com.rideshare.models.Driver;
import com.rideshare.models.Rider;
import com.rideshare.models.Ride;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataStore {
    private static ArrayList<Rider> riders = new ArrayList<>();
    private static ArrayList<Driver> drivers = new ArrayList<>();
    private static ArrayList<Ride> rides = new ArrayList<>();

    
    public static void addRider(Rider rider) {
        if (rider == null) {
            throw new IllegalArgumentException("Rider cannot be null");
        }
        riders.add(rider);
    }

    public static List<Rider> getAllRiders() {
        return new ArrayList<>(riders);
    }

    public static Rider getRiderById(int id) {
        Optional<Rider> rider = riders.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
        return rider.orElse(null);
    }

    public static boolean updateRider(Rider updatedRider) {
        if (updatedRider == null) {
            return false;
        }
        for (int i = 0; i < riders.size(); i++) {
            if (riders.get(i).getId() == updatedRider.getId()) {
                riders.set(i, updatedRider);
                return true;
            }
        }
        return false;
    }

    
    public static void addDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        drivers.add(driver);
    }

    public static List<Driver> getAllDrivers() {
        return new ArrayList<>(drivers);
    }

    public static Driver getDriverById(int id) {
        Optional<Driver> driver = drivers.stream()
                .filter(d -> d.getId() == id)
                .findFirst();
        return driver.orElse(null);
    }

    public static List<Driver> getAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

    public static boolean updateDriver(Driver updatedDriver) {
        if (updatedDriver == null) {
            return false;
        }
        for (int i = 0; i < drivers.size(); i++) {
            if (drivers.get(i).getId() == updatedDriver.getId()) {
                drivers.set(i, updatedDriver);
                return true;
            }
        }
        return false;
    }

    
    public static void addRide(Ride ride) {
        if (ride == null) {
            throw new IllegalArgumentException("Ride cannot be null");
        }
        rides.add(ride);
    }

    public static List<Ride> getAllRides() {
        return new ArrayList<>(rides);
    }

    public static Ride getRideById(int id) {
        Optional<Ride> ride = rides.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
        return ride.orElse(null);
    }

    public static List<Ride> getRidesByRiderId(int riderId) {
        List<Ride> riderRides = new ArrayList<>();
        for (Ride ride : rides) {
            if (ride.getRiderId() == riderId) {
                riderRides.add(ride);
            }
        }
        return riderRides;
    }

    public static List<Ride> getRidesByDriverId(int driverId) {
        List<Ride> driverRides = new ArrayList<>();
        for (Ride ride : rides) {
            if (ride.getDriverId() == driverId) {
                driverRides.add(ride);
            }
        }
        return driverRides;
    }

    public static boolean updateRide(Ride updatedRide) {
        if (updatedRide == null) {
            return false;
        }
        for (int i = 0; i < rides.size(); i++) {
            if (rides.get(i).getId() == updatedRide.getId()) {
                rides.set(i, updatedRide);
                return true;
            }
        }
        return false;
    }

    
    public static void clearAll() {
        riders.clear();
        drivers.clear();
        rides.clear();
    }

    public static int getRiderCount() {
        return riders.size();
    }

    public static int getDriverCount() {
        return drivers.size();
    }

    public static int getRideCount() {
        return rides.size();
    }
}
