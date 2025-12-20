import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Auth from './screens/Auth';
import MainMap from './screens/MainMap';
import BookingConfirmation from './screens/BookingConfirmation';
import RideDetails from './screens/RideDetails';
import History from './screens/History';
import Profile from './screens/Profile';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Auth">
        <Stack.Screen name="Auth" component={Auth} options={{ headerShown: false }} />
        <Stack.Screen name="MainMap" component={MainMap} options={{ headerShown: false }} />
        <Stack.Screen name="BookingConfirmation" component={BookingConfirmation} options={{ title: 'Booking Confirmed' }} />
        <Stack.Screen name="RideDetails" component={RideDetails} options={{ title: 'Ride Details' }} />
        <Stack.Screen name="History" component={History} options={{ title: 'Your Trips' }} />
        <Stack.Screen name="Profile" component={Profile} options={{ title: 'Profile' }} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
