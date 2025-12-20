import React, { useState, useRef, useEffect } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Modal, ScrollView } from 'react-native';
import { WebView } from 'react-native-webview';
import api from '../services/api';

const PUNE_LOCATIONS = [
  { id: 1, name: 'Pune Station', lat: 18.5204, lon: 73.8567 },
  { id: 2, name: 'Koregaon Park', lat: 18.5314, lon: 73.8446 },
  { id: 3, name: 'Shivajinagar', lat: 18.5074, lon: 73.8077 },
  { id: 4, name: 'Viman Nagar', lat: 18.5362, lon: 73.9125 },
  { id: 5, name: 'Katraj', lat: 18.4635, lon: 73.8665 },
  { id: 6, name: 'Hinjewadi', lat: 18.5642, lon: 73.7769 },
  { id: 7, name: 'Kharadi', lat: 18.5089, lon: 73.9332 },
  { id: 8, name: 'Swargate', lat: 18.4574, lon: 73.8544 },
];

export default function MainMap({ navigation }) {
  const [fromLocation, setFromLocation] = useState(PUNE_LOCATIONS[0]);
  const [toLocation, setToLocation] = useState(PUNE_LOCATIONS[1]);
  const [drivers, setDrivers] = useState([]);
  const [selectedDriver, setSelectedDriver] = useState(null);
  const [showFromModal, setShowFromModal] = useState(false);
  const [showToModal, setShowToModal] = useState(false);
  const [showDrivers, setShowDrivers] = useState(false);
  const [menuOpen, setMenuOpen] = useState(false);
  const [surgeActive, setSurgeActive] = useState(false);
  const webViewRef = useRef(null);

  useEffect(() => {
    if (drivers.length > 0) {
      updateMap();
    }
  }, [drivers]);

  const updateMap = () => {
    const markers = [
      { lat: fromLocation.lat, lon: fromLocation.lon, color: 'green', title: fromLocation.name },
      { lat: toLocation.lat, lon: toLocation.lon, color: 'red', title: toLocation.name },
      ...drivers.map(d => ({ 
        lat: d.location.latitude, 
        lon: d.location.longitude, 
        color: 'blue', 
        emoji: getVehicleEmoji(d.driver.vehicleType),
        title: d.driver.vehicleType 
      }))
    ];
    
    if (webViewRef.current) {
      webViewRef.current.injectJavaScript(`
        updateMarkers(${JSON.stringify(markers)});
        true;
      `);
    }
  };

  const fetchDrivers = async () => {
    try {
      const data = await api.getNearbyDrivers(fromLocation.lat, fromLocation.lon, toLocation.lat, toLocation.lon, surgeActive);
      // Sort by distance to pickup (closest drivers first)
      const sortedData = data.sort((a, b) => a.distanceToPickup - b.distanceToPickup);
      setDrivers(sortedData);
      setShowDrivers(true);
    } catch (error) {
      alert('Failed to fetch drivers');
    }
  };

  const bookRide = async (driver) => {
    const distance = driver.tripDistance;
    const vehicleType = driver.driver.vehicleType.toUpperCase();
    const estimatedCost = driver.estimatedCost;
    
    try {
      // Check wallet balance from backend
      const rider = await api.getRider(1);
      const walletBalance = rider.walletBalance || 0;
      
      if (estimatedCost > walletBalance) {
        alert(`Insufficient balance! You need ₹${estimatedCost.toFixed(2)} but have ₹${walletBalance.toFixed(2)}\n\nPlease add money to your wallet from Profile.`);
        return;
      }
      
      const ride = await api.createRide({ 
        riderId: 1, 
        distance, 
        vehicleType, 
        useSurge: surgeActive,
        fare: estimatedCost  // Pass the exact fare from the bottom sheet
      });
      navigation.navigate('BookingConfirmation', { ride });
      setShowDrivers(false);
      setSelectedDriver(null);
    } catch (error) {
      alert('Booking failed: ' + (error.response?.data?.message || error.message));
    }
  };

  const getVehicleEmoji = (type) => {
    if (type === 'Car') return '🚗';
    if (type === 'Bike') return '🏍️';
    if (type === 'Auto') return '🛺';
    return '🚕';
  };

  const mapHTML = `
    <!DOCTYPE html>
    <html>
    <head>
      <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
      <link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" />
      <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
      <style>
        body { margin: 0; padding: 0; }
        #map { height: 100vh; width: 100vw; }
        .leaflet-control-zoom {
          position: absolute !important;
          top: 100px !important;
          left: 15px !important;
          bottom: auto !important;
        }
        .leaflet-control-zoom a {
          width: 35px !important;
          height: 35px !important;
          line-height: 40px !important;
          font-size: 20px !important;
        }
      </style>
    </head>
    <body>
      <div id="map"></div>
      <script>
        const map = L.map('map', {
          zoomControl: false
        }).setView([${fromLocation.lat}, ${fromLocation.lon}], 13);
        
        L.control.zoom({
          position: 'topleft'
        }).addTo(map);
        
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
          attribution: '© OpenStreetMap contributors'
        }).addTo(map);

        let markers = [];

        function updateMarkers(newMarkers) {
          markers.forEach(m => map.removeLayer(m));
          markers = [];
          
          newMarkers.forEach(marker => {
            let icon;
            if (marker.emoji) {
              icon = L.divIcon({
                html: '<div style="font-size:24px;text-align:center;line-height:30px;">' + marker.emoji + '</div>',
                iconSize: [30, 30],
                className: ''
              });
            } else {
              icon = L.icon({
                iconUrl: 'https://raw.githubusercontent.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-' + marker.color + '.png',
                shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
                iconSize: [25, 41],
                iconAnchor: [12, 41],
                popupAnchor: [1, -34],
                shadowSize: [41, 41]
              });
            }
            
            const m = L.marker([marker.lat, marker.lon], {icon: icon})
              .addTo(map)
              .bindPopup(marker.title);
            markers.push(m);
          });
          
          if (newMarkers.length > 0) {
            const bounds = L.latLngBounds(newMarkers.map(m => [m.lat, m.lon]));
            map.fitBounds(bounds, { padding: [50, 50] });
          }
        }

        // Initial markers
        updateMarkers([
          { lat: ${fromLocation.lat}, lon: ${fromLocation.lon}, color: 'green', title: '${fromLocation.name}' },
          { lat: ${toLocation.lat}, lon: ${toLocation.lon}, color: 'red', title: '${toLocation.name}' }
        ]);
      </script>
    </body>
    </html>
  `;

  return (
    <View style={styles.container}>
      <WebView
        ref={webViewRef}
        style={styles.map}
        originWhitelist={['*']}
        source={{ html: mapHTML }}
        javaScriptEnabled={true}
        domStorageEnabled={true}
      />

      <TouchableOpacity style={styles.menuButton} onPress={() => setMenuOpen(true)}>
        <Text style={styles.menuIcon}>☰</Text>
      </TouchableOpacity>

      <View style={styles.topCard}>
        <TouchableOpacity style={styles.locationRow} onPress={() => setShowFromModal(true)}>
          <Text style={styles.dot}>●</Text>
          <Text style={styles.locationText}>{fromLocation.name}</Text>
        </TouchableOpacity>
        <View style={styles.divider} />
        <TouchableOpacity style={styles.locationRow} onPress={() => setShowToModal(true)}>
          <Text style={styles.dot}>■</Text>
          <Text style={styles.locationText}>{toLocation.name}</Text>
        </TouchableOpacity>
      </View>

      {!showDrivers ? (
        <View style={styles.bottomControls}>
          <TouchableOpacity 
            style={[styles.surgeButton, surgeActive && styles.surgeButtonActive]} 
            onPress={() => setSurgeActive(!surgeActive)}
          >
            <Text style={[styles.surgeButtonText, surgeActive && styles.surgeButtonTextActive]}>
              ⚡ Surge {surgeActive ? 'ON (1.5x)' : 'OFF'}
            </Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.searchButton} onPress={fetchDrivers}>
            <Text style={styles.searchButtonText}>Find Rides</Text>
          </TouchableOpacity>
        </View>
      ) : (
        <View style={styles.bottomSheet}>
          <View style={styles.sheetHeader}>
            <View style={styles.sheetTitleContainer}>
              <Text style={styles.sheetTitle}>Choose a ride</Text>
              {drivers.length > 0 && (
                <Text style={styles.tripDistance}>
                  {drivers[0].tripDistance.toFixed(1)} km trip
                </Text>
              )}
            </View>
            <TouchableOpacity 
              style={styles.backButton} 
              onPress={() => {
                setShowDrivers(false);
                setSelectedDriver(null);
                setDrivers([]);
              }}
            >
              <Text style={styles.backButtonText}>✕</Text>
            </TouchableOpacity>
          </View>
          <ScrollView 
            style={styles.driverList}
            showsVerticalScrollIndicator={true}
            persistentScrollbar={true}
          >
            {drivers.map((d) => (
              <TouchableOpacity
                key={d.driver.id}
                style={[styles.driverCard, selectedDriver?.driver.id === d.driver.id && styles.driverCardSelected]}
                onPress={() => setSelectedDriver(d)}
              >
                <Text style={styles.driverCardEmoji}>{getVehicleEmoji(d.driver.vehicleType)}</Text>
                <View style={styles.driverCardInfo}>
                  <Text style={styles.driverCardType}>{d.driver.vehicleType}</Text>
                  <Text style={styles.driverCardTime}>{Math.ceil(d.distanceToPickup)} min away</Text>
                </View>
                <Text style={styles.driverCardPrice}>₹{d.estimatedCost.toFixed(2)}</Text>
              </TouchableOpacity>
            ))}
          </ScrollView>
          {selectedDriver && (
            <TouchableOpacity style={styles.confirmButton} onPress={() => bookRide(selectedDriver)}>
              <Text style={styles.confirmButtonText}>Confirm {selectedDriver.driver.vehicleType}</Text>
            </TouchableOpacity>
          )}
        </View>
      )}

      <Modal visible={showFromModal} transparent animationType="slide">
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            <Text style={styles.modalTitle}>Select Pickup</Text>
            <ScrollView>
              {PUNE_LOCATIONS.map((loc) => (
                <TouchableOpacity
                  key={loc.id}
                  style={styles.modalItem}
                  onPress={() => {
                    setFromLocation(loc);
                    setShowFromModal(false);
                    setShowDrivers(false);
                  }}
                >
                  <Text style={styles.modalItemText}>{loc.name}</Text>
                </TouchableOpacity>
              ))}
            </ScrollView>
            <TouchableOpacity style={styles.modalClose} onPress={() => setShowFromModal(false)}>
              <Text style={styles.modalCloseText}>Cancel</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>

      <Modal visible={showToModal} transparent animationType="slide">
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            <Text style={styles.modalTitle}>Select Destination</Text>
            <ScrollView>
              {PUNE_LOCATIONS.map((loc) => (
                <TouchableOpacity
                  key={loc.id}
                  style={styles.modalItem}
                  onPress={() => {
                    setToLocation(loc);
                    setShowToModal(false);
                    setShowDrivers(false);
                  }}
                >
                  <Text style={styles.modalItemText}>{loc.name}</Text>
                </TouchableOpacity>
              ))}
            </ScrollView>
            <TouchableOpacity style={styles.modalClose} onPress={() => setShowToModal(false)}>
              <Text style={styles.modalCloseText}>Cancel</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>

      <Modal visible={menuOpen} transparent animationType="slide">
        <View style={styles.menuModal}>
          <View style={styles.menuContent}>
            <TouchableOpacity style={styles.menuHeader} onPress={() => setMenuOpen(false)}>
              <Text style={styles.menuClose}>✕</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.menuItem} onPress={() => { setMenuOpen(false); navigation.navigate('Profile'); }}>
              <Text style={styles.menuItemIcon}>👤</Text>
              <Text style={styles.menuItemText}>Profile</Text>
            </TouchableOpacity>
            <TouchableOpacity style={styles.menuItem} onPress={() => { setMenuOpen(false); navigation.navigate('History'); }}>
              <Text style={styles.menuItemIcon}>📜</Text>
              <Text style={styles.menuItemText}>Your Trips</Text>
            </TouchableOpacity>
          </View>
        </View>
      </Modal>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1 },
  map: { flex: 1 },
  menuButton: { position: 'absolute', top: 50, left: 20, width: 50, height: 50, backgroundColor: '#fff', borderRadius: 25, justifyContent: 'center', alignItems: 'center', elevation: 5 },
  menuIcon: { fontSize: 24, color: '#000' },
  topCard: { position: 'absolute', top: 50, left: 80, right: 20, backgroundColor: '#fff', borderRadius: 8, padding: 15, elevation: 5 },
  locationRow: { flexDirection: 'row', alignItems: 'center', paddingVertical: 10 },
  dot: { fontSize: 20, marginRight: 15, width: 20 },
  locationText: { fontSize: 16, color: '#000', flex: 1 },
  divider: { height: 1, backgroundColor: '#eee', marginVertical: 5 },
  bottomControls: { position: 'absolute', bottom: 30, left: 20, right: 20, gap: 10 },
  surgeButton: { backgroundColor: '#f5f5f5', padding: 14, borderRadius: 8, alignItems: 'center', borderWidth: 2, borderColor: '#ddd' },
  surgeButtonActive: { backgroundColor: '#fff3cd', borderColor: '#ffc107' },
  surgeButtonText: { color: '#666', fontSize: 16, fontWeight: 'bold' },
  surgeButtonTextActive: { color: '#ff6f00' },
  searchButton: { backgroundColor: '#000', padding: 18, borderRadius: 8, alignItems: 'center', elevation: 5 },
  searchButtonText: { color: '#fff', fontSize: 18, fontWeight: 'bold' },
  bottomSheet: { position: 'absolute', bottom: 0, left: 0, right: 0, backgroundColor: '#fff', borderTopLeftRadius: 20, borderTopRightRadius: 20, padding: 20, minHeight: 300, elevation: 10, shadowColor: '#000', shadowOffset: { width: 0, height: -2 }, shadowOpacity: 0.25, shadowRadius: 10 },
  handle: { width: 40, height: 5, backgroundColor: '#ccc', alignSelf: 'center', borderRadius: 3, marginBottom: 20 },
  sheetHeader: { flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center', marginBottom: 20 },
  sheetTitleContainer: { flex: 1 },
  sheetTitle: { fontSize: 22, fontWeight: 'bold', color: '#000' },
  tripDistance: { fontSize: 14, color: '#666', marginTop: 4 },
  backButton: { width: 36, height: 36, borderRadius: 18, backgroundColor: '#f5f5f5', justifyContent: 'center', alignItems: 'center' },
  backButtonText: { fontSize: 20, color: '#000' },
  driverList: { maxHeight: 250, marginBottom: 10 },
  driverCard: { flexDirection: 'row', alignItems: 'center', backgroundColor: '#f5f5f5', padding: 15, borderRadius: 8, marginBottom: 10 },
  driverCardSelected: { backgroundColor: '#e3f2fd', borderWidth: 2, borderColor: '#2196f3' },
  driverCardEmoji: { fontSize: 30, marginRight: 15 },
  driverCardInfo: { flex: 1 },
  driverCardType: { fontSize: 18, fontWeight: 'bold', color: '#000' },
  driverCardTime: { fontSize: 14, color: '#666', marginTop: 2 },
  driverCardPrice: { fontSize: 20, fontWeight: 'bold', color: '#000' },
  confirmButton: { backgroundColor: '#000', padding: 18, borderRadius: 8, alignItems: 'center', marginTop: 15 },
  confirmButtonText: { color: '#fff', fontSize: 18, fontWeight: 'bold' },
  driverMarker: { width: 40, height: 40, backgroundColor: '#fff', borderRadius: 20, justifyContent: 'center', alignItems: 'center', borderWidth: 2, borderColor: '#000', elevation: 5 },
  driverEmoji: { fontSize: 22 },
  modalContainer: { flex: 1, backgroundColor: 'rgba(0,0,0,0.5)', justifyContent: 'flex-end' },
  modalContent: { backgroundColor: '#fff', borderTopLeftRadius: 20, borderTopRightRadius: 20, padding: 20, maxHeight: '70%' },
  modalTitle: { fontSize: 22, fontWeight: 'bold', marginBottom: 20, color: '#000' },
  modalItem: { padding: 18, borderBottomWidth: 1, borderBottomColor: '#eee' },
  modalItemText: { fontSize: 16, color: '#000' },
  modalClose: { backgroundColor: '#f5f5f5', padding: 15, borderRadius: 8, alignItems: 'center', marginTop: 15 },
  modalCloseText: { fontSize: 16, color: '#000', fontWeight: 'bold' },
  menuModal: { flex: 1, backgroundColor: 'rgba(0,0,0,0.5)' },
  menuContent: { width: '75%', height: '100%', backgroundColor: '#fff', padding: 20 },
  menuHeader: { marginBottom: 30 },
  menuClose: { fontSize: 28, color: '#000' },
  menuItem: { flexDirection: 'row', alignItems: 'center', paddingVertical: 18 },
  menuItemIcon: { fontSize: 24, marginRight: 20, width: 30 },
  menuItemText: { fontSize: 18, color: '#000' },
});
