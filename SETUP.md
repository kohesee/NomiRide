# NomiRide Setup Guide

## Prerequisites

- Java 17 or higher
- Maven
- Node.js (v16 or higher)
- npm
- Expo CLI (`npm install -g expo-cli`)

## Backend Setup in Spring Boot

1. Navigate to the backend directory:
```bash
cd backend
```

2. Build the project:
```bash
.\mvnw.cmd clean install
```

3. Run the Spring Boot application:
```bash
.\mvnw.cmd spring-boot:run
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

## Mobile App Setup in React Native with Expo

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
- For Physical Device: Use your computer's IP address (`http://192.168.1.100:8080/api`)

To find your IP address:
- Windows: `ipconfig` (look for IPv4 Address)
- Mac/Linux: `ifconfig` or `ip addr`

## Running the Complete Application

1. **Start Backend** (Terminal 1):
```bash
cd backend
.\mvnw.cmd spring-boot:run
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

### Test Flow
1. Login with any credentials
2. Select pickup location (e.g., Pune Station)
3. Select dropoff location (e.g., Hinjewadi IT Park)
4. Toggle surge pricing if needed
5. Click "Find Rides" to see available drivers
6. Select a vehicle from the bottom sheet
7. Click "Confirm Vehicle" to book
8. Click "Complete Ride" on confirmation screen
9. View completed ride details
10. Open menu (☰) → "My Trips" for ride history
11. Open menu (☰) → "Profile" for wallet management

### Default Rider
The app uses Rider ID: 1 by default for all operations.

## Technologies Used

- **Backend**: Spring Boot, Java
- **Mobile**: React Native, Expo
- **Navigation**: React Navigation
- **HTTP Client**: Axios
- **Build Tool**: Maven
- **Package Manager**: npm
