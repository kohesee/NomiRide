package com.rideshare.services;

import com.rideshare.models.Driver;
import com.rideshare.models.Ride;
import com.rideshare.models.Rider;
import com.rideshare.enums.VehicleType;
import com.rideshare.utils.DataStore;
import com.rideshare.vehicles.Vehicle;
import com.rideshare.vehicles.VehicleFactory;
import com.rideshare.strategies.PricingStrategy;
import com.rideshare.strategies.FlatPricing;
import com.rideshare.strategies.SurgePricing;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RideService {

    private final Random random = new Random();
    private int rideIdCounter = 1;

    public Ride createRide(int riderId, double distance, VehicleType vehicleType, boolean useSurge) {
        // find available drivers
        List<Driver> available = DataStore.getAvailableDrivers();

        Driver assigned = null;
        if (!available.isEmpty()) {
            assigned = available.get(random.nextInt(available.size()));
            assigned.markBusy();
            DataStore.updateDriver(assigned);
        }

        Vehicle vehicle = VehicleFactory.createVehicle(vehicleType);
        PricingStrategy strategy = useSurge ? new SurgePricing() : new FlatPricing();
        double fare = strategy.calculateFare(distance, vehicle);

        Ride ride = new Ride(rideIdCounter++, riderId, distance);
        if (assigned != null) {
            ride.setDriverId(assigned.getId());
        }
        ride.setFare(fare);

        DataStore.addRide(ride);
        return ride;
    }

    public Ride completeRide(int rideId) {
        Ride ride = DataStore.getRideById(rideId);
        if (ride == null) {
            return null;
        }

        Rider rider = DataStore.getRiderById(ride.getRiderId());
        if (rider != null) {
            boolean ok = rider.deductFromWallet(ride.getFare());
            if (!ok) {
                // insufficient balance
                return ride;
            }
            DataStore.updateRider(rider);
        }

        // mark driver available
        Driver driver = DataStore.getDriverById(ride.getDriverId());
        if (driver != null) {
            driver.markAvailable();
            DataStore.updateDriver(driver);
        }

        ride.markCompleted();
        DataStore.updateRide(ride);
        return ride;
    }

    public List<Ride> getRideHistory(int riderId) {
        return DataStore.getRidesByRiderId(riderId);
    }

    public Ride getRide(int rideId) {
        return DataStore.getRideById(rideId);
    }

    public List<Driver> getAvailableDrivers() {
        return DataStore.getAvailableDrivers();
    }
}
