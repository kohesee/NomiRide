import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Home from './screens/Home';
import BookingConfirmation from './screens/BookingConfirmation';
import RideDetails from './screens/RideDetails';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={Home} />
        <Stack.Screen name="BookingConfirmation" component={BookingConfirmation} />
        <Stack.Screen name="RideDetails" component={RideDetails} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
