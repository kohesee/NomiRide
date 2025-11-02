import React, { useState } from 'react';
import { View, Text, TextInput, Button, TouchableOpacity } from 'react-native';
import api from '../services/api';

const VehicleButton = ({ label, selected, onPress }) => (
  <TouchableOpacity onPress={onPress} style={{ padding: 10, backgroundColor: selected ? '#4caf50' : '#ddd', margin: 5 }}>
    <Text>{label}</Text>
  </TouchableOpacity>
);

export default function Home({ navigation }) {
  const [distance, setDistance] = useState('5');
  const [vehicle, setVehicle] = useState('CAR');
  const [surge, setSurge] = useState(false);
  const [estimate, setEstimate] = useState(null);

  const calculate = () => {
    // Simple local estimate matching FlatPricing: base 5 + distance*2*multiplier
    const mult = vehicle === 'CAR' ? 1.0 : vehicle === 'BIKE' ? 0.6 : 0.8;
    const d = parseFloat(distance) || 0;
    const est = 5 + d * 2 * mult;
    const final = surge ? (5 + d * 3 * mult) : est;
    setEstimate(final.toFixed(2));
  };

  const book = async () => {
    const payload = { riderId: 'rider-1', distance: parseFloat(distance), vehicleType: vehicle, useSurge: surge };
    try {
      const ride = await api.createRide(payload);
      navigation.navigate('BookingConfirmation', { ride });
    } catch (e) {
      alert('Booking failed: ' + e.message);
    }
  };

  return (
    <View style={{ padding: 20 }}>
      <Text>Distance (km)</Text>
      <TextInput value={distance} onChangeText={setDistance} keyboardType="numeric" style={{ borderWidth: 1, marginBottom: 10 }} />

      <Text>Vehicle</Text>
      <View style={{ flexDirection: 'row' }}>
        <VehicleButton label="Car" selected={vehicle==='CAR'} onPress={()=>setVehicle('CAR')} />
        <VehicleButton label="Bike" selected={vehicle==='BIKE'} onPress={()=>setVehicle('BIKE')} />
        <VehicleButton label="Auto" selected={vehicle==='AUTO'} onPress={()=>setVehicle('AUTO')} />
      </View>

      <View style={{ flexDirection: 'row', alignItems: 'center', marginVertical: 10 }}>
        <Text>Surge</Text>
        <TouchableOpacity onPress={()=>setSurge(s=>!s)} style={{ marginLeft: 10, padding: 8, backgroundColor: surge? '#f44336' : '#ccc' }}>
          <Text>{surge ? 'ON' : 'OFF'}</Text>
        </TouchableOpacity>
      </View>

      <Button title="Calculate Fare" onPress={calculate} />
      {estimate && <Text style={{ marginVertical: 10 }}>Estimate: ₹{estimate}</Text>}

      <Button title="Book Ride" onPress={book} />
    </View>
  );
}
