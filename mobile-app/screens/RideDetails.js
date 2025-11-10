import React from 'react';
import { View, Text, StyleSheet, ScrollView } from 'react-native';
import StatusBadge from '../components/StatusBadge';

export default function RideDetails({ route }) {
  const { ride } = route.params;

  const formatDate = (timestamp) => {
    if (!timestamp) return 'N/A';
    const date = new Date(timestamp);
    return date.toLocaleString();
  };

  return (
    <ScrollView style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Ride Details</Text>
        <Text style={styles.rideId}>#{ride.id}</Text>
      </View>

      <View style={styles.content}>
        <View style={styles.section}>
          <Text style={styles.sectionTitle}>Trip Information</Text>
          
          <View style={styles.infoRow}>
            <Text style={styles.label}>Rider ID</Text>
            <Text style={styles.value}>{ride.riderId}</Text>
          </View>

          <View style={styles.infoRow}>
            <Text style={styles.label}>Driver ID</Text>
            <Text style={styles.value}>{ride.driverId || 'Not Assigned'}</Text>
          </View>

          <View style={styles.infoRow}>
            <Text style={styles.label}>Distance</Text>
            <Text style={styles.value}>{ride.distance} km</Text>
          </View>

          <View style={styles.infoRow}>
            <Text style={styles.label}>Fare</Text>
            <Text style={styles.fareValue}>₹{ride.fare}</Text>
          </View>
        </View>

        <View style={styles.section}>
          <Text style={styles.sectionTitle}>Status</Text>
          <StatusBadge status={ride.status} />
        </View>

        <View style={styles.section}>
          <Text style={styles.sectionTitle}>Timestamp</Text>
          <Text style={styles.timestampText}>{formatDate(ride.timestamp)}</Text>
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
  header: {
    backgroundColor: '#2196f3',
    padding: 30,
    alignItems: 'center'
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#fff',
    marginBottom: 5
  },
  rideId: {
    fontSize: 18,
    color: '#fff',
    opacity: 0.9
  },
  content: {
    padding: 20
  },
  section: {
    backgroundColor: '#fff',
    padding: 20,
    marginBottom: 15,
    borderRadius: 10,
    elevation: 2,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4
  },
  sectionTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 15,
    borderBottomWidth: 1,
    borderBottomColor: '#e0e0e0',
    paddingBottom: 10
  },
  infoRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingVertical: 10,
    borderBottomWidth: 1,
    borderBottomColor: '#f5f5f5'
  },
  label: {
    fontSize: 16,
    color: '#666'
  },
  value: {
    fontSize: 16,
    color: '#333',
    fontWeight: '600'
  },
  fareValue: {
    fontSize: 20,
    color: '#4caf50',
    fontWeight: 'bold'
  },
  timestampText: {
    fontSize: 16,
    color: '#555'
  }
});
