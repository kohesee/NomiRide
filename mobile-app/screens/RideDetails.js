import React from 'react';
import { View, Text } from 'react-native';

export default function RideDetails({ route }) {
  const { ride } = route.params;

  return (
    <View style={{ padding: 20 }}>
      <Text>Ride ID: {ride.id}</Text>
      <Text>Rider: {ride.riderId}</Text>
      <Text>Driver: {ride.driverId ?? 'N/A'}</Text>
      <Text>Distance: {ride.distance} km</Text>
      <Text>Fare: ₹{ride.fare}</Text>
      <Text>Status: {ride.status}</Text>
      <Text>Timestamp: {ride.timestamp}</Text>
    </View>
  );
}
