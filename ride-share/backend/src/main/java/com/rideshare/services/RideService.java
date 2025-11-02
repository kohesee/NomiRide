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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Random;
import java.time.LocalDateTime;

public class RideService {

    private final Random random = new Random();

    public Ride createRide(String riderId, double distance, VehicleType vehicleType, boolean useSurge) {
        // find available drivers
        List<Driver> drivers = DataStore.getDrivers();
    List<Driver> available = drivers.stream().filter(Driver::isAvailable).collect(Collectors.toList());

        Driver assigned = null;
        if (!available.isEmpty()) {
            assigned = available.get(random.nextInt(available.size()));
            assigned.markBusy();
            DataStore.updateDriver(assigned);
        }

        Vehicle vehicle = VehicleFactory.createVehicle(vehicleType);
        PricingStrategy strategy = useSurge ? new SurgePricing() : new FlatPricing();
        double fare = strategy.calculateFare(distance, vehicle);

        Ride ride = new Ride();
        ride.setId("ride-" + System.currentTimeMillis());
        ride.setRiderId(riderId);
        ride.setDriverId(assigned != null ? assigned.getId() : null);
        ride.setDistance(distance);
        ride.setFare(fare);
        ride.setStatus(com.rideshare.enums.RideStatus.PENDING);
        ride.setTimestamp(LocalDateTime.now());

        DataStore.addRide(ride);
        return ride;
    }

    public Optional<Ride> completeRide(String rideId) {
        Optional<Ride> maybeRide = DataStore.getRideById(rideId);
        if (maybeRide.isEmpty()) return maybeRide;

        Ride ride = maybeRide.get();
        if (!ride.canComplete()) return Optional.of(ride);

        Optional<Rider> maybeRider = DataStore.getRiderById(ride.getRiderId());
        if (maybeRider.isPresent()) {
            Rider rider = maybeRider.get();
            boolean ok = rider.deductFromWallet(ride.getFare());
            if (!ok) {
                // insufficient balance
                return Optional.of(ride);
            }
            DataStore.updateRider(rider);
        }

        // mark driver available
        if (ride.getDriverId() != null) {
            Optional<Driver> maybeDriver = DataStore.getDriverById(ride.getDriverId());
            if (maybeDriver.isPresent()) {
                Driver d = maybeDriver.get();
                d.markAvailable();
                DataStore.updateDriver(d);
            }
        }

        ride.markCompleted();
        DataStore.updateRide(ride);
        return Optional.of(ride);
    }

    public List<Ride> getRideHistory(String riderId) {
    return DataStore.getRides().stream().filter(r -> r.getRiderId() != null && r.getRiderId().equals(riderId)).collect(Collectors.toList());
    }

    public Optional<Ride> getRide(String rideId) {
        return DataStore.getRideById(rideId);
    }

    public List<Driver> getAvailableDrivers() {
    return DataStore.getDrivers().stream().filter(Driver::isAvailable).collect(Collectors.toList());
    }
}
