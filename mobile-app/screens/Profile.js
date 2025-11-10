import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, ActivityIndicator, ScrollView } from 'react-native';
import api from '../services/api';
import VehicleCard from '../components/VehicleCard';

export default function Profile() {
  const [rider, setRider] = useState(null);
  const [loading, setLoading] = useState(true);

  const riderId = 1;

  const fetchRider = async () => {
    try {
      const data = await api.getRider(riderId);
      setRider(data);
    } catch (error) {
      alert('Failed to fetch profile: ' + error.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchRider();
  }, []);

  const handleAddMoney = async () => {
    try {
      const updated = await api.addToWallet(riderId, 100);
      setRider(updated);
      alert('₹100 added to wallet successfully!');
    } catch (error) {
      alert('Failed to add money: ' + error.message);
    }
  };

  if (loading) {
    return (
      <View style={styles.center}>
        <ActivityIndicator size="large" color="#2196f3" />
      </View>
    );
  }

  return (
    <ScrollView style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.title}>Profile</Text>
      </View>

      <View style={styles.section}>
        <Text style={styles.label}>Name</Text>
        <Text style={styles.value}>{rider?.name || 'N/A'}</Text>
      </View>

      <View style={styles.section}>
        <Text style={styles.label}>Phone</Text>
        <Text style={styles.value}>{rider?.phone || 'N/A'}</Text>
      </View>

      <View style={styles.section}>
        <Text style={styles.label}>Wallet Balance</Text>
        <Text style={styles.balance}>₹{rider?.walletBalance?.toFixed(2) || '0.00'}</Text>
      </View>

      <TouchableOpacity style={styles.button} onPress={handleAddMoney}>
        <Text style={styles.buttonText}>Add ₹100 to Wallet</Text>
      </TouchableOpacity>

      <View style={styles.divider} />

      <View style={styles.infoSection}>
        <Text style={styles.sectionTitle}>Vehicle Pricing Information</Text>
        <VehicleCard type="Car" multiplier={1.0} capacity={4} />
        <VehicleCard type="Auto" multiplier={0.8} capacity={3} />
        <VehicleCard type="Bike" multiplier={0.6} capacity={1} />
      </View>

      <View style={styles.infoSection}>
        <Text style={styles.sectionTitle}>Pricing Details</Text>
        <View style={styles.pricingCard}>
          <Text style={styles.pricingText}>Flat Pricing: ₹100 base + ₹15/km × vehicle multiplier</Text>
          <Text style={styles.pricingText}>Surge Pricing: ₹100 base + ₹15/km × 1.5 × vehicle multiplier</Text>
          <Text style={styles.pricingText}>Pickup Fee: ₹8/km × vehicle multiplier</Text>
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
  center: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center'
  },
  header: {
    backgroundColor: '#fff',
    padding: 20,
    borderBottomWidth: 1,
    borderBottomColor: '#e0e0e0'
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#333'
  },
  section: {
    backgroundColor: '#fff',
    padding: 20,
    marginTop: 10,
    borderBottomWidth: 1,
    borderBottomColor: '#e0e0e0'
  },
  label: {
    fontSize: 14,
    color: '#999',
    marginBottom: 5
  },
  value: {
    fontSize: 18,
    color: '#333'
  },
  balance: {
    fontSize: 32,
    fontWeight: 'bold',
    color: '#4caf50'
  },
  button: {
    backgroundColor: '#2196f3',
    padding: 15,
    margin: 20,
    borderRadius: 8,
    alignItems: 'center'
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold'
  },
  divider: {
    height: 10,
    backgroundColor: '#e0e0e0'
  },
  infoSection: {
    padding: 20
  },
  sectionTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 15
  },
  pricingCard: {
    backgroundColor: '#fff',
    padding: 15,
    borderRadius: 8
  },
  pricingText: {
    fontSize: 14,
    color: '#555',
    marginBottom: 8
  }
});
