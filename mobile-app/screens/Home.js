import React, { useState } from 'react';
import { View, Text, TextInput, Button, TouchableOpacity, StyleSheet, ScrollView } from 'react-native';
import api from '../services/api';

const VehicleButton = ({ label, selected, onPress }) => (
  <TouchableOpacity onPress={onPress} style={[styles.vehicleButton, selected && styles.vehicleButtonSelected]}>
    <Text style={[styles.vehicleButtonText, selected && styles.vehicleButtonTextSelected]}>{label}</Text>
  </TouchableOpacity>
);

export default function Home({ navigation }) {
  const [distance, setDistance] = useState('5');
  const [vehicle, setVehicle] = useState('CAR');
  const [surge, setSurge] = useState(false);
  const [estimate, setEstimate] = useState(null);

  const calculate = () => {
    const mult = vehicle === 'CAR' ? 1.0 : vehicle === 'BIKE' ? 0.6 : 0.8;
    const d = parseFloat(distance) || 0;
    const est = 5 + d * 2 * mult;
    const final = surge ? (5 + d * 3 * mult) : est;
    setEstimate(final.toFixed(2));
  };

  const book = async () => {
    const payload = { riderId: 1, distance: parseFloat(distance), vehicleType: vehicle, useSurge: surge };
    try {
      const ride = await api.createRide(payload);
      navigation.navigate('BookingConfirmation', { ride });
    } catch (e) {
      alert('Booking failed: ' + e.message);
    }
  };

  return (
    <ScrollView style={styles.container}>
      <View style={styles.content}>
        <Text style={styles.label}>Distance (km)</Text>
        <TextInput 
          value={distance} 
          onChangeText={setDistance} 
          keyboardType="numeric" 
          style={styles.input} 
          placeholder="Enter distance"
        />

        <Text style={styles.label}>Vehicle Type</Text>
        <View style={styles.vehicleContainer}>
          <VehicleButton label="Car" selected={vehicle==='CAR'} onPress={()=>setVehicle('CAR')} />
          <VehicleButton label="Bike" selected={vehicle==='BIKE'} onPress={()=>setVehicle('BIKE')} />
          <VehicleButton label="Auto" selected={vehicle==='AUTO'} onPress={()=>setVehicle('AUTO')} />
        </View>

        <View style={styles.surgeContainer}>
          <Text style={styles.label}>Surge Pricing</Text>
          <TouchableOpacity 
            onPress={()=>setSurge(s=>!s)} 
            style={[styles.surgeToggle, surge && styles.surgeToggleOn]}
          >
            <Text style={styles.surgeText}>{surge ? 'ON' : 'OFF'}</Text>
          </TouchableOpacity>
        </View>

        <TouchableOpacity style={styles.calculateButton} onPress={calculate}>
          <Text style={styles.buttonText}>Calculate Fare</Text>
        </TouchableOpacity>

        {estimate && (
          <View style={styles.estimateContainer}>
            <Text style={styles.estimateLabel}>Estimated Fare:</Text>
            <Text style={styles.estimateValue}>₹{estimate}</Text>
          </View>
        )}

        <TouchableOpacity style={styles.bookButton} onPress={book}>
          <Text style={styles.buttonText}>Book Ride</Text>
        </TouchableOpacity>

        <View style={styles.navButtons}>
          <TouchableOpacity 
            style={styles.navButton} 
            onPress={() => navigation.navigate('History')}
          >
            <Text style={styles.navButtonText}>View History</Text>
          </TouchableOpacity>
          
          <TouchableOpacity 
            style={styles.navButton} 
            onPress={() => navigation.navigate('Profile')}
          >
            <Text style={styles.navButtonText}>My Profile</Text>
          </TouchableOpacity>
        </View>
      </View>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5'
  },
  content: {
    padding: 20
  },
  label: {
    fontSize: 16,
    fontWeight: 'bold',
    marginBottom: 8,
    color: '#333'
  },
  input: {
    borderWidth: 1,
    borderColor: '#ddd',
    padding: 12,
    marginBottom: 20,
    borderRadius: 8,
    backgroundColor: '#fff',
    fontSize: 16
  },
  vehicleContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 20
  },
  vehicleButton: {
    flex: 1,
    padding: 12,
    backgroundColor: '#fff',
    margin: 5,
    borderRadius: 8,
    borderWidth: 2,
    borderColor: '#ddd',
    alignItems: 'center'
  },
  vehicleButtonSelected: {
    backgroundColor: '#4caf50',
    borderColor: '#4caf50'
  },
  vehicleButtonText: {
    fontSize: 16,
    color: '#333'
  },
  vehicleButtonTextSelected: {
    color: '#fff',
    fontWeight: 'bold'
  },
  surgeContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginBottom: 20
  },
  surgeToggle: {
    padding: 10,
    paddingHorizontal: 20,
    backgroundColor: '#ccc',
    borderRadius: 8
  },
  surgeToggleOn: {
    backgroundColor: '#f44336'
  },
  surgeText: {
    color: '#fff',
    fontWeight: 'bold',
    fontSize: 14
  },
  calculateButton: {
    backgroundColor: '#2196f3',
    padding: 15,
    borderRadius: 8,
    alignItems: 'center',
    marginBottom: 15
  },
  bookButton: {
    backgroundColor: '#4caf50',
    padding: 15,
    borderRadius: 8,
    alignItems: 'center',
    marginBottom: 20
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold'
  },
  estimateContainer: {
    backgroundColor: '#fff',
    padding: 15,
    borderRadius: 8,
    marginBottom: 15,
    alignItems: 'center',
    borderWidth: 2,
    borderColor: '#4caf50'
  },
  estimateLabel: {
    fontSize: 14,
    color: '#666',
    marginBottom: 5
  },
  estimateValue: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#4caf50'
  },
  navButtons: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: 10
  },
  navButton: {
    flex: 1,
    backgroundColor: '#673ab7',
    padding: 12,
    borderRadius: 8,
    alignItems: 'center',
    margin: 5
  },
  navButtonText: {
    color: '#fff',
    fontSize: 14,
    fontWeight: 'bold'
  }
});
