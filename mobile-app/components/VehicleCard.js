import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

export default function VehicleCard({ type, multiplier, capacity }) {
  return (
    <View style={styles.card}>
      <Text style={styles.type}>{type}</Text>
      <Text style={styles.detail}>Multiplier: {multiplier}x</Text>
      <Text style={styles.detail}>Capacity: {capacity} passengers</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  card: {
    backgroundColor: '#f5f5f5',
    padding: 15,
    borderRadius: 8,
    marginBottom: 10,
    borderLeftWidth: 4,
    borderLeftColor: '#2196f3'
  },
  type: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 5
  },
  detail: {
    fontSize: 14,
    color: '#666'
  }
});
