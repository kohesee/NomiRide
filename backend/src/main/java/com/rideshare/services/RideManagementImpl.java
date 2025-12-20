package com.rideshare.services;

import com.rideshare.models.Ride;
import com.rideshare.models.Rider;
import com.rideshare.enums.VehicleType;
import com.rideshare.utils.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideManagementImpl implements IRideManagement {

    @Autowired
    private RideService rideService;

    @Override
    public Ride requestRide(int riderId, double distance, VehicleType type, boolean surge) {
        return rideService.createRide(riderId, distance, type, surge);
    }

    @Override
    public Ride completeRide(int rideId) {
        return rideService.completeRide(rideId);
    }

    @Override
    public List<Ride> getUserRides(int riderId) {
        return rideService.getRideHistory(riderId);
    }

    @Override
    public Rider getRider(int riderId) {
        return DataStore.getRiderById(riderId);
    }
}
