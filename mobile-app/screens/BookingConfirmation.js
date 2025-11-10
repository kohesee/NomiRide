import React from 'react';
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import api from '../services/api';

export default function BookingConfirmation({ route, navigation }) {
  const { ride } = route.params;

  const complete = async () => {
    try {
      const updated = await api.completeRide(ride.id);
      // Navigate to RideDetails and reset the navigation stack so back goes to MainMap
      navigation.reset({
        index: 1,
        routes: [
          { name: 'MainMap' },
          { name: 'RideDetails', params: { ride: updated } }
        ]
      });
    } catch (e) {
      console.error('Complete ride error:', e);
      const errorMsg = e.response?.data?.message || e.message || 'Unknown error';
      alert('Failed to complete ride!\n\n' + errorMsg);
    }
  };

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.emoji}>✓</Text>
        <Text style={styles.title}>Ride Confirmed</Text>
        <Text style={styles.subtitle}>Your driver is on the way</Text>
      </View>

      <View style={styles.card}>
        <View style={styles.row}>
          <Text style={styles.label}>Ride ID</Text>
          <Text style={styles.value}>#{ride.id}</Text>
        </View>
        <View style={styles.row}>
          <Text style={styles.label}>Vehicle</Text>
          <Text style={styles.value}>{ride.vehicleType}</Text>
        </View>
        <View style={styles.row}>
          <Text style={styles.label}>Distance</Text>
          <Text style={styles.value}>{ride.distance} km</Text>
        </View>
        <View style={styles.row}>
          <Text style={styles.label}>Status</Text>
          <Text style={styles.statusActive}>{ride.status}</Text>
        </View>
        <View style={styles.divider} />
        <View style={styles.row}>
          <Text style={styles.fareLabel}>Total Fare</Text>
          <Text style={styles.fareValue}>₹{Number(ride.fare).toFixed(2)}</Text>
        </View>
      </View>

      <TouchableOpacity style={styles.button} onPress={complete}>
        <Text style={styles.buttonText}>Complete Ride</Text>
      </TouchableOpacity>

      <TouchableOpacity style={styles.buttonSecondary} onPress={() => navigation.navigate('MainMap')}>
        <Text style={styles.buttonSecondaryText}>Back to Home</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: '#fff', padding: 20 },
  header: { alignItems: 'center', marginVertical: 40 },
  emoji: { fontSize: 80, color: '#4caf50', marginBottom: 20 },
  title: { fontSize: 28, fontWeight: 'bold', color: '#000', marginBottom: 8 },
  subtitle: { fontSize: 16, color: '#666' },
  card: { backgroundColor: '#f9f9f9', borderRadius: 12, padding: 20, marginBottom: 20 },
  row: { flexDirection: 'row', justifyContent: 'space-between', paddingVertical: 12 },
  label: { fontSize: 16, color: '#666' },
  value: { fontSize: 16, color: '#000', fontWeight: '600' },
  statusActive: { fontSize: 16, color: '#4caf50', fontWeight: 'bold' },
  divider: { height: 1, backgroundColor: '#ddd', marginVertical: 8 },
  fareLabel: { fontSize: 20, fontWeight: 'bold', color: '#000' },
  fareValue: { fontSize: 24, fontWeight: 'bold', color: '#4caf50' },
  button: { backgroundColor: '#000', padding: 18, borderRadius: 8, alignItems: 'center', marginBottom: 12 },
  buttonText: { color: '#fff', fontSize: 18, fontWeight: 'bold' },
  buttonSecondary: { backgroundColor: '#f5f5f5', padding: 18, borderRadius: 8, alignItems: 'center' },
  buttonSecondaryText: { color: '#000', fontSize: 18, fontWeight: '600' },
});
