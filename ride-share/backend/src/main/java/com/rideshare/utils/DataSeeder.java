package com.rideshare.utils;

import com.rideshare.models.Driver;
import com.rideshare.models.Location;
import com.rideshare.models.Rider;

public class DataSeeder {
    public static void seed() {
        for (int i = 1; i <= 5; i++) {
            Driver d = new Driver();
            d.setId("driver-" + i);
            d.setName("Driver " + i);
            d.setVehicleType(i % 3 == 0 ? "BIKE" : i % 3 == 1 ? "CAR" : "AUTO");
            Location loc = new Location();
            loc.setLatitude(12.9 + i * 0.01);
            loc.setLongitude(77.5 + i * 0.01);
            loc.setAddress("Address " + i);
            d.setLocation(loc);
            DataStore.addDriver(d);
        }

        for (int i = 1; i <= 3; i++) {
            Rider r = new Rider();
            r.setId("rider-" + i);
            r.setName("Rider " + i);
            r.setPhone("99999" + i);
            r.setWalletBalance(500);
            DataStore.addRider(r);
        }
    }
}
