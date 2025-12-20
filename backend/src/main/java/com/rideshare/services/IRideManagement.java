package com.rideshare.services;

import com.rideshare.models.Ride;
import com.rideshare.models.Rider;
import com.rideshare.enums.VehicleType;
import java.util.List;

public interface IRideManagement {
    Ride requestRide(int riderId, double distance, VehicleType type, boolean surge);
    
    Ride completeRide(int rideId);
    
    List<Ride> getUserRides(int riderId);
    
    Rider getRider(int riderId);
}
