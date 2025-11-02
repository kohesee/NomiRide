import React from 'react';
import { View, Text, Button } from 'react-native';
import api from '../services/api';

export default function BookingConfirmation({ route, navigation }) {
  const { ride } = route.params;

  const complete = async () => {
    try {
      const updated = await api.completeRide(ride.id);
      navigation.navigate('RideDetails', { ride: updated });
    } catch (e) {
      alert('Complete failed: ' + e.message);
    }
  };

  return (
    <View style={{ padding: 20 }}>
      <Text>Driver: {ride.driverId ?? 'Not assigned'}</Text>
      <Text>Vehicle: {ride.driverId ? 'Assigned' : 'Unassigned'}</Text>
      <Text>Fare: ₹{ride.fare}</Text>
      <Text>Status: {ride.status}</Text>

      <Button title="Complete Ride" onPress={complete} />
    </View>
  );
}
