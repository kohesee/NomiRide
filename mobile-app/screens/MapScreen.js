import React, { useState, useEffect } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, TextInput, ScrollView } from 'react-native';
import MapView, { Marker, Polyline } from 'react-native-maps';
import api from '../services/api';

export default function MapScreen({ navigation }) {
  const [fromLat, setFromLat] = useState('18.5204');
  const [fromLon, setFromLon] = useState('73.8567');
  const [toLat, setToLat] = useState('18.5314');
  const [toLon, setToLon] = useState('73.8446');
  const [drivers, setDrivers] = useState([]);
  const [showMap, setShowMap] = useState(false);

  const fetchDrivers = async () => {
    try {
      const data = await api.getNearbyDrivers(
        parseFloat(fromLat),
        parseFloat(fromLon),
        parseFloat(toLat),
        parseFloat(toLon)
      );
      setDrivers(data);
      setShowMap(true);
    } catch (error) {
      alert('Failed to fetch drivers: ' + error.message);
    }
  };

  const getVehicleEmoji = (type) => {
    if (type === 'Car') return '🚗';
    if (type === 'Bike') return '🏍️';
    if (type === 'Auto') return '🛺';
    return '🚕';
  };

  const getMarkerColor = (type) => {
    if (type === 'Car') return '#4caf50';
    if (type === 'Bike') return '#2196f3';
    if (type === 'Auto') return '#ff9800';
    return '#9e9e9e';
  };

  return (
    <View style={styles.container}>
      {!showMap ? (
        <ScrollView style={styles.inputContainer}>
          <Text style={styles.title}>Enter Locations</Text>
          
          <Text style={styles.label}>From Latitude</Text>
          <TextInput
            style={styles.input}
            value={fromLat}
            onChangeText={setFromLat}
            keyboardType="numeric"
            placeholder="18.5204"
          />
          
          <Text style={styles.label}>From Longitude</Text>
          <TextInput
            style={styles.input}
            value={fromLon}
            onChangeText={setFromLon}
            keyboardType="numeric"
            placeholder="73.8567"
          />
          
          <Text style={styles.label}>To Latitude</Text>
          <TextInput
            style={styles.input}
            value={toLat}
            onChangeText={setToLat}
            keyboardType="numeric"
            placeholder="18.5314"
          />
          
          <Text style={styles.label}>To Longitude</Text>
          <TextInput
            style={styles.input}
            value={toLon}
            onChangeText={setToLon}
            keyboardType="numeric"
            placeholder="73.8446"
          />
          
          <TouchableOpacity style={styles.button} onPress={fetchDrivers}>
            <Text style={styles.buttonText}>Show Map</Text>
          </TouchableOpacity>

          <View style={styles.presetContainer}>
            <Text style={styles.presetTitle}>Popular Routes in Pune:</Text>
            <TouchableOpacity 
              style={styles.presetButton}
              onPress={() => {
                setFromLat('18.5204');
                setFromLon('73.8567');
                setToLat('18.5642');
                setToLon('73.7769');
              }}
            >
              <Text style={styles.presetText}>Pune Station → Hinjewadi</Text>
            </TouchableOpacity>
            
            <TouchableOpacity 
              style={styles.presetButton}
              onPress={() => {
                setFromLat('18.5314');
                setFromLon('73.8446');
                setToLat('18.5089');
                setToLon('73.9332');
              }}
            >
              <Text style={styles.presetText}>Koregaon Park → Kharadi</Text>
            </TouchableOpacity>
          </View>
        </ScrollView>
      ) : (
        <View style={styles.mapContainer}>
          <MapView
            style={styles.map}
            initialRegion={{
              latitude: parseFloat(fromLat),
              longitude: parseFloat(fromLon),
              latitudeDelta: 0.1,
              longitudeDelta: 0.1,
            }}
          >
            <Marker
              coordinate={{
                latitude: parseFloat(fromLat),
                longitude: parseFloat(fromLon),
              }}
              title="Pickup"
              pinColor="green"
            />
            
            <Marker
              coordinate={{
                latitude: parseFloat(toLat),
                longitude: parseFloat(toLon),
              }}
              title="Dropoff"
              pinColor="red"
            />
            
            <Polyline
              coordinates={[
                { latitude: parseFloat(fromLat), longitude: parseFloat(fromLon) },
                { latitude: parseFloat(toLat), longitude: parseFloat(toLon) },
              ]}
              strokeColor="#2196f3"
              strokeWidth={3}
            />
            
            {drivers.map((d) => (
              <Marker
                key={d.driver.id}
                coordinate={{
                  latitude: d.location.latitude,
                  longitude: d.location.longitude,
                }}
                title={`${d.driver.name} (${d.driver.vehicleType})`}
                description={`${d.distanceToPickup}km away • ₹${d.estimatedCost}`}
              >
                <View style={[styles.driverMarker, { backgroundColor: getMarkerColor(d.driver.vehicleType) }]}>
                  <Text style={styles.vehicleEmoji}>{getVehicleEmoji(d.driver.vehicleType)}</Text>
                </View>
              </Marker>
            ))}
          </MapView>
          
          <View style={styles.infoPanel}>
            <Text style={styles.infoPanelTitle}>Available Drivers ({drivers.length})</Text>
            <ScrollView style={styles.driverList}>
              {drivers.map((d) => (
                <View key={d.driver.id} style={styles.driverCard}>
                  <View style={styles.driverHeader}>
                    <Text style={styles.driverEmoji}>{getVehicleEmoji(d.driver.vehicleType)}</Text>
                    <View style={styles.driverInfo}>
                      <Text style={styles.driverName}>{d.driver.name}</Text>
                      <Text style={styles.driverType}>{d.driver.vehicleType}</Text>
                    </View>
                    <Text style={styles.driverCost}>₹{d.estimatedCost}</Text>
                  </View>
                  <Text style={styles.driverDistance}>
                    {d.distanceToPickup}km away • Trip: {d.tripDistance}km
                  </Text>
                </View>
              ))}
            </ScrollView>
          </View>
          
          <TouchableOpacity 
            style={styles.backButton} 
            onPress={() => setShowMap(false)}
          >
            <Text style={styles.backButtonText}>← Change Route</Text>
          </TouchableOpacity>
        </View>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
  },
  inputContainer: {
    padding: 20,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
    color: '#333',
  },
  label: {
    fontSize: 16,
    fontWeight: 'bold',
    marginBottom: 5,
    marginTop: 10,
    color: '#555',
  },
  input: {
    borderWidth: 1,
    borderColor: '#ddd',
    padding: 12,
    borderRadius: 8,
    backgroundColor: '#fff',
    fontSize: 16,
  },
  button: {
    backgroundColor: '#2196f3',
    padding: 15,
    borderRadius: 8,
    alignItems: 'center',
    marginTop: 20,
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
  },
  presetContainer: {
    marginTop: 30,
  },
  presetTitle: {
    fontSize: 16,
    fontWeight: 'bold',
    marginBottom: 10,
    color: '#555',
  },
  presetButton: {
    backgroundColor: '#fff',
    padding: 15,
    borderRadius: 8,
    marginBottom: 10,
    borderWidth: 1,
    borderColor: '#ddd',
  },
  presetText: {
    fontSize: 14,
    color: '#333',
  },
  mapContainer: {
    flex: 1,
  },
  map: {
    flex: 1,
  },
  driverMarker: {
    width: 40,
    height: 40,
    borderRadius: 20,
    justifyContent: 'center',
    alignItems: 'center',
    borderWidth: 2,
    borderColor: '#fff',
  },
  vehicleEmoji: {
    fontSize: 20,
  },
  infoPanel: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    backgroundColor: '#fff',
    borderTopLeftRadius: 20,
    borderTopRightRadius: 20,
    padding: 15,
    maxHeight: 250,
    elevation: 5,
  },
  infoPanelTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
    color: '#333',
  },
  driverList: {
    maxHeight: 180,
  },
  driverCard: {
    backgroundColor: '#f9f9f9',
    padding: 12,
    borderRadius: 8,
    marginBottom: 8,
  },
  driverHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 5,
  },
  driverEmoji: {
    fontSize: 24,
    marginRight: 10,
  },
  driverInfo: {
    flex: 1,
  },
  driverName: {
    fontSize: 16,
    fontWeight: 'bold',
    color: '#333',
  },
  driverType: {
    fontSize: 12,
    color: '#666',
  },
  driverCost: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#4caf50',
  },
  driverDistance: {
    fontSize: 12,
    color: '#999',
    marginLeft: 34,
  },
  backButton: {
    position: 'absolute',
    top: 10,
    left: 10,
    backgroundColor: '#fff',
    padding: 12,
    borderRadius: 8,
    elevation: 3,
  },
  backButtonText: {
    fontSize: 14,
    fontWeight: 'bold',
    color: '#2196f3',
  },
});
