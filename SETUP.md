# NomiRide Setup Guide

## Prerequisites

- Java 17 or higher
- Maven
- Node.js (v16 or higher)
- npm
- Expo CLI (`npm install -g expo-cli`)

## Backend Setup (Spring Boot)

1. Navigate to the backend directory:
```bash
cd backend
```

2. Build the project:
```bash
mvn clean install
```

3. Run the Spring Boot application:
```bash
mvn spring-boot:run
```

The backend server will start on `http://localhost:8080`

### API Endpoints

- `POST /api/rides` - Create a new ride
- `GET /api/rides/{id}` - Get ride by ID
- `PATCH /api/rides/{id}/complete` - Complete a ride
- `GET /api/rides/history/{riderId}` - Get ride history for a rider
- `GET /api/drivers/available` - Get available drivers
- `GET /api/riders/{riderId}` - Get rider information
- `PATCH /api/riders/{riderId}/wallet` - Add money to wallet

## Mobile App Setup (React Native with Expo)

1. Navigate to the mobile app directory:
```bash
cd mobile-app
```

2. Install dependencies (already done):
```bash
npm install
```

3. Start the Expo development server:
```bash
npm start
```

Or use specific commands:
- `npm run android` - Run on Android emulator
- `npm run ios` - Run on iOS simulator (Mac only)

### Important: API Base URL

The API base URL is configured in `mobile-app/services/api.js`:

```javascript
const API_BASE = 'http://10.0.2.2:8080/api';
```

- For Android Emulator: Use `http://10.0.2.2:8080/api`
- For iOS Simulator: Use `http://localhost:8080/api`
- For Physical Device: Use your computer's IP address (e.g., `http://192.168.1.100:8080/api`)

To find your IP address:
- Windows: `ipconfig` (look for IPv4 Address)
- Mac/Linux: `ifconfig` or `ip addr`

## Running the Complete Application

1. **Start Backend** (Terminal 1):
```bash
cd backend
mvn spring-boot:run
```

2. **Start Mobile App** (Terminal 2):
```bash
cd mobile-app
npm start
```

3. **Open in Emulator/Simulator:**
- Press `a` for Android
- Press `i` for iOS (Mac only)
- Scan QR code with Expo Go app on physical device

## Testing the Application

### Initial Data
The application seeds the following data on startup:
- 5 Drivers (IDs: 1-5)
- 3 Riders (IDs: 1-3, with ₹500 initial balance each)

### Test Flow
1. Open the Home screen
2. Enter distance (e.g., 10 km)
3. Select vehicle type (Car/Bike/Auto)
4. Toggle surge pricing if needed
5. Click "Calculate Fare" to see estimate
6. Click "Book Ride" to create a ride
7. On Booking Confirmation, click "Complete Ride"
8. View ride details
9. Navigate to "View History" to see all past rides
10. Navigate to "My Profile" to view wallet balance and add money

### Default Rider
The app uses Rider ID: 1 by default for all operations.

## Project Structure

### Backend (Person 5 Implementation)
- `services/IRideManagement.java` - Abstraction interface for ride management
- `services/RideManagementImpl.java` - Implementation hiding details from controller
- `controllers/RideController.java` - Updated to use interface instead of concrete service

### Mobile App (Person 5 Implementation)
- `screens/History.js` - Ride history screen with pull-to-refresh
- `screens/Profile.js` - Profile screen with wallet management
- `components/RideCard.js` - Reusable ride card component
- `components/StatusBadge.js` - Status badge component
- `components/VehicleCard.js` - Vehicle information card
- `services/api.js` - API service with all endpoints
- `App.js` - Updated navigation with all 5 screens

## OOP Concepts Demonstrated

### Person 5: Abstraction
- **IRideManagement Interface**: Defines contract for ride management operations
- **RideManagementImpl**: Hides implementation details from RideController
- **Controller uses interface**: RideController depends on interface, not concrete implementation
- **Benefits**: Can swap implementations without changing controller code

## Troubleshooting

### Backend Issues
- **Port 8080 already in use**: Stop other processes using port 8080 or change the port in `application.properties`
- **Maven build fails**: Run `mvn clean install` to rebuild

### Mobile App Issues
- **Cannot connect to backend**: Check API_BASE URL in `services/api.js`
- **Expo errors**: Clear cache with `expo start -c`
- **Dependencies issues**: Delete `node_modules` and run `npm install` again

## Demo Checklist

### Backend (Person 5)
1. Show IRideManagement interface
2. Show RideManagementImpl hiding service details
3. Show RideController using interface (not concrete class)
4. Explain how implementation can be swapped without affecting controller

### Mobile App (Person 5)
1. Navigate to History screen and show ride list
2. Pull to refresh to update data
3. Tap on a ride to view details
4. Navigate to Profile screen
5. Show wallet balance
6. Click "Add ₹100" button
7. Verify balance updated
8. Show vehicle pricing information

## Technologies Used

- **Backend**: Spring Boot, Java
- **Mobile**: React Native, Expo
- **Navigation**: React Navigation
- **HTTP Client**: Axios
- **Build Tool**: Maven
- **Package Manager**: npm
