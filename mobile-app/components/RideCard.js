import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';
import StatusBadge from './StatusBadge';

export default function RideCard({ ride, onPress }) {
  const formatDate = (timestamp) => {
    if (!timestamp) return 'N/A';
    const date = new Date(timestamp);
    return date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
  };

  return (
    <TouchableOpacity style={styles.card} onPress={onPress}>
      <View style={styles.row}>
        <Text style={styles.id}>Ride #{ride.id}</Text>
        <StatusBadge status={ride.status} />
      </View>
      <Text style={styles.detail}>Driver: {ride.driverId || 'Not assigned'}</Text>
      <Text style={styles.detail}>Distance: {ride.distance} km</Text>
      <Text style={styles.detail}>Fare: ₹{Number(ride.fare).toFixed(2)}</Text>
      <Text style={styles.timestamp}>{formatDate(ride.timestamp)}</Text>
    </TouchableOpacity>
  );
}

const styles = StyleSheet.create({
  card: {
    backgroundColor: '#fff',
    padding: 15,
    borderRadius: 10,
    marginBottom: 12,
    elevation: 2,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 8
  },
  id: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#333'
  },
  detail: {
    fontSize: 14,
    color: '#555',
    marginBottom: 4
  },
  timestamp: {
    fontSize: 12,
    color: '#999',
    marginTop: 8
  }
});
