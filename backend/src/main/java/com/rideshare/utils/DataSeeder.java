package com.rideshare.utils;

import com.rideshare.models.Driver;
import com.rideshare.models.Rider;
import com.rideshare.models.Location;
import com.rideshare.enums.DriverStatus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        seedData();
    }

    private void seedData() {
        // Seed 3 Riders
        seedRiders();
        
        // Seed 5 Drivers
        seedDrivers();
        
        System.out.println("✓ Data seeding completed!");
        System.out.println("  - Riders: " + DataStore.getRiderCount());
        System.out.println("  - Drivers: " + DataStore.getDriverCount());
    }

    private void seedRiders() {
        Rider rider1 = new Rider(1, "Aarav Sharma", "9876543210", 500.0);
        Rider rider2 = new Rider(2, "Priya Patel", "9876543211", 750.0);
        Rider rider3 = new Rider(3, "Rohan Kumar", "9876543212", 1000.0);

        DataStore.addRider(rider1);
        DataStore.addRider(rider2);
        DataStore.addRider(rider3);
    }

    private void seedDrivers() {
        // Driver 1 - Car, Available
        Location loc1 = new Location(19.0760, 72.8777, "Mumbai Central");
        Driver driver1 = new Driver(1, "Rajesh Verma", "Car", DriverStatus.AVAILABLE, loc1);
        
        // Driver 2 - Bike, Available
        Location loc2 = new Location(19.0896, 72.8656, "Bandra West");
        Driver driver2 = new Driver(2, "Amit Singh", "Bike", DriverStatus.AVAILABLE, loc2);
        
        // Driver 3 - Auto, Available
        Location loc3 = new Location(19.0176, 72.8561, "Andheri East");
        Driver driver3 = new Driver(3, "Suresh Yadav", "Auto", DriverStatus.AVAILABLE, loc3);
        
        // Driver 4 - Car, Busy
        Location loc4 = new Location(19.1197, 72.9059, "Powai");
        Driver driver4 = new Driver(4, "Deepak Joshi", "Car", DriverStatus.BUSY, loc4);
        
        // Driver 5 - Auto, Available
        Location loc5 = new Location(19.0330, 72.8698, "Worli");
        Driver driver5 = new Driver(5, "Vikram Reddy", "Auto", DriverStatus.AVAILABLE, loc5);

        DataStore.addDriver(driver1);
        DataStore.addDriver(driver2);
        DataStore.addDriver(driver3);
        DataStore.addDriver(driver4);
        DataStore.addDriver(driver5);
    }
}
