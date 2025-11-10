import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import api from '../services/api';
import StatusBadge from '../components/StatusBadge';

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
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Booking Confirmed!</Text>
      </View>

      <View style={styles.content}>
        <View style={styles.infoCard}>
          <Text style={styles.label}>Ride ID</Text>
          <Text style={styles.value}>#{ride.id}</Text>
        </View>

        <View style={styles.infoCard}>
          <Text style={styles.label}>Driver</Text>
          <Text style={styles.value}>{ride.driverId ? `Driver #${ride.driverId}` : 'Not assigned yet'}</Text>
        </View>

        <View style={styles.infoCard}>
          <Text style={styles.label}>Distance</Text>
          <Text style={styles.value}>{ride.distance} km</Text>
        </View>

        <View style={styles.infoCard}>
          <Text style={styles.label}>Fare</Text>
          <Text style={styles.fareValue}>₹{ride.fare}</Text>
        </View>

        <View style={styles.infoCard}>
          <Text style={styles.label}>Status</Text>
          <StatusBadge status={ride.status} />
        </View>

        <TouchableOpacity style={styles.completeButton} onPress={complete}>
          <Text style={styles.buttonText}>Complete Ride</Text>
        </TouchableOpacity>

        <TouchableOpacity 
          style={styles.detailsButton} 
          onPress={() => navigation.navigate('RideDetails', { ride })}
        >
          <Text style={styles.detailsButtonText}>View Full Details</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5'
  },
  header: {
    backgroundColor: '#4caf50',
    padding: 30,
    alignItems: 'center'
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#fff'
  },
  content: {
    padding: 20
  },
  infoCard: {
    backgroundColor: '#fff',
    padding: 15,
    marginBottom: 12,
    borderRadius: 8,
    elevation: 1,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.1,
    shadowRadius: 2
  },
  label: {
    fontSize: 14,
    color: '#999',
    marginBottom: 5
  },
  value: {
    fontSize: 18,
    color: '#333',
    fontWeight: '600'
  },
  fareValue: {
    fontSize: 24,
    color: '#4caf50',
    fontWeight: 'bold'
  },
  completeButton: {
    backgroundColor: '#4caf50',
    padding: 15,
    borderRadius: 8,
    alignItems: 'center',
    marginTop: 20
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold'
  },
  detailsButton: {
    backgroundColor: '#fff',
    padding: 15,
    borderRadius: 8,
    alignItems: 'center',
    marginTop: 12,
    borderWidth: 2,
    borderColor: '#2196f3'
  },
  detailsButtonText: {
    color: '#2196f3',
    fontSize: 16,
    fontWeight: 'bold'
  }
});
