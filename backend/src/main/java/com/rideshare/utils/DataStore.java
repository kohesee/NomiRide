package com.rideshare.utils;

import com.rideshare.models.Driver;
import com.rideshare.models.Rider;
import com.rideshare.models.Ride;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataStore {
    // Private static storage - encapsulated data structures
    private static ArrayList<Rider> riders = new ArrayList<>();
    private static ArrayList<Driver> drivers = new ArrayList<>();
    private static ArrayList<Ride> rides = new ArrayList<>();

    // ============ Rider Methods ============
    
    // Add a rider to the data store
    public static void addRider(Rider rider) {
        if (rider == null) {
            throw new IllegalArgumentException("Rider cannot be null");
        }
        riders.add(rider);
    }

    // Get all riders
    public static List<Rider> getAllRiders() {
        return new ArrayList<>(riders); // Return copy to protect internal data
    }

    // Get rider by ID
    public static Rider getRiderById(int id) {
        Optional<Rider> rider = riders.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
        return rider.orElse(null);
    }

    // Update rider
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

    // ============ Driver Methods ============
    
    // Add a driver to the data store
    public static void addDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        drivers.add(driver);
    }

    // Get all drivers
    public static List<Driver> getAllDrivers() {
        return new ArrayList<>(drivers); // Return copy to protect internal data
    }

    // Get driver by ID
    public static Driver getDriverById(int id) {
        Optional<Driver> driver = drivers.stream()
                .filter(d -> d.getId() == id)
                .findFirst();
        return driver.orElse(null);
    }

    // Get all available drivers
    public static List<Driver> getAvailableDrivers() {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }

    // Update driver
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

    // ============ Ride Methods ============
    
    // Add a ride to the data store
    public static void addRide(Ride ride) {
        if (ride == null) {
            throw new IllegalArgumentException("Ride cannot be null");
        }
        rides.add(ride);
    }

    // Get all rides
    public static List<Ride> getAllRides() {
        return new ArrayList<>(rides); // Return copy to protect internal data
    }

    // Get ride by ID
    public static Ride getRideById(int id) {
        Optional<Ride> ride = rides.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
        return ride.orElse(null);
    }

    // Get rides by rider ID
    public static List<Ride> getRidesByRiderId(int riderId) {
        List<Ride> riderRides = new ArrayList<>();
        for (Ride ride : rides) {
            if (ride.getRiderId() == riderId) {
                riderRides.add(ride);
            }
        }
        return riderRides;
    }

    // Get rides by driver ID
    public static List<Ride> getRidesByDriverId(int driverId) {
        List<Ride> driverRides = new ArrayList<>();
        for (Ride ride : rides) {
            if (ride.getDriverId() == driverId) {
                driverRides.add(ride);
            }
        }
        return driverRides;
    }

    // Update ride
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

    // ============ Utility Methods ============
    
    // Clear all data (useful for testing)
    public static void clearAll() {
        riders.clear();
        drivers.clear();
        rides.clear();
    }

    // Get counts
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
