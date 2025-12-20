import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

export default function StatusBadge({ status }) {
  const getColor = () => {
    switch (status) {
      case 'PENDING': return '#ffc107';
      case 'IN_PROGRESS': return '#2196f3';
      case 'COMPLETED': return '#4caf50';
      case 'CANCELLED': return '#f44336';
      default: return '#9e9e9e';
    }
  };

  return (
    <View style={[styles.badge, { backgroundColor: getColor() }]}>
      <Text style={styles.text}>{status}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  badge: {
    paddingHorizontal: 12,
    paddingVertical: 6,
    borderRadius: 12,
    alignSelf: 'flex-start'
  },
  text: {
    color: '#fff',
    fontWeight: 'bold',
    fontSize: 12
  }
});
