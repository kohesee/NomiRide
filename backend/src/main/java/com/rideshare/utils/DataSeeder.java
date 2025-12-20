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
        seedRiders();
        
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
        Location loc1 = new Location(18.5204, 73.8567, "Pune Station");
        Driver driver1 = new Driver(1, "Rajesh Verma", "Car", DriverStatus.AVAILABLE, loc1);
        
        Location loc2 = new Location(18.5314, 73.8446, "Koregaon Park");
        Driver driver2 = new Driver(2, "Amit Singh", "Bike", DriverStatus.AVAILABLE, loc2);
        
        Location loc3 = new Location(18.5074, 73.8077, "Shivajinagar");
        Driver driver3 = new Driver(3, "Suresh Yadav", "Auto", DriverStatus.AVAILABLE, loc3);
        
        Location loc4 = new Location(18.5362, 73.9125, "Viman Nagar");
        Driver driver4 = new Driver(4, "Deepak Joshi", "Car", DriverStatus.AVAILABLE, loc4);
        
        Location loc5 = new Location(18.4635, 73.8665, "Katraj");
        Driver driver5 = new Driver(5, "Vikram Reddy", "Auto", DriverStatus.AVAILABLE, loc5);
        
        Location loc6 = new Location(18.5642, 73.7769, "Hinjewadi");
        Driver driver6 = new Driver(6, "Ramesh Kulkarni", "Bike", DriverStatus.AVAILABLE, loc6);
        
        Location loc7 = new Location(18.5089, 73.9332, "Kharadi");
        Driver driver7 = new Driver(7, "Anil Patil", "Car", DriverStatus.AVAILABLE, loc7);
        
        Location loc8 = new Location(18.4574, 73.8544, "Swargate");
        Driver driver8 = new Driver(8, "Sandeep Deshmukh", "Auto", DriverStatus.AVAILABLE, loc8);

        DataStore.addDriver(driver1);
        DataStore.addDriver(driver2);
        DataStore.addDriver(driver3);
        DataStore.addDriver(driver4);
        DataStore.addDriver(driver5);
        DataStore.addDriver(driver6);
        DataStore.addDriver(driver7);
        DataStore.addDriver(driver8);
    }
}
