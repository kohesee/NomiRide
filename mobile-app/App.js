import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Home from './screens/Home';
import BookingConfirmation from './screens/BookingConfirmation';
import RideDetails from './screens/RideDetails';
import History from './screens/History';
import Profile from './screens/Profile';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={Home} options={{ title: 'Book a Ride' }} />
        <Stack.Screen name="BookingConfirmation" component={BookingConfirmation} options={{ title: 'Booking Confirmed' }} />
        <Stack.Screen name="RideDetails" component={RideDetails} options={{ title: 'Ride Details' }} />
        <Stack.Screen name="History" component={History} options={{ title: 'Ride History' }} />
        <Stack.Screen name="Profile" component={Profile} options={{ title: 'My Profile' }} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
