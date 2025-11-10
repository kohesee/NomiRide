# Quick Start Guide

## 1. Start Backend

```bash
cd backend
.\mvnw.cmd spring-boot:run
```

Backend will be available at: `http://localhost:8080`

## 2. Start Mobile App

```bash
cd mobile-app
npm start
```

Then press:
- `a` for Android emulator
- `i` for iOS simulator (Mac only)
- Scan QR with Expo Go app on physical device

## 3. Test the App

### Default User
The app uses **Rider ID: 1** (Name: "Alice", Initial Balance: ₹500)

### Test Flow:

1. **Home Screen**
   - Enter distance: 10
   - Select vehicle: Car
   - Toggle surge: OFF
   - Click "Calculate Fare" → Shows ₹25
   - Click "Book Ride"

2. **Booking Confirmation**
   - See ride details
   - Click "Complete Ride"

3. **Ride Details**
   - View full ride information
   - Note the status change to COMPLETED

4. **History Screen** (From Home)
   - Click "View History" button
   - See all past rides
   - Pull down to refresh
   - Tap any ride to see details

5. **Profile Screen** (From Home)
   - Click "My Profile" button
   - View wallet balance (should be ₹475 after completing ₹25 ride)
   - Click "Add ₹100" button
   - Balance updates to ₹575
   - View vehicle pricing information

## API Endpoints

### Rides
- `POST /api/rides` - Create ride
- `GET /api/rides/{id}` - Get ride
- `PATCH /api/rides/{id}/complete` - Complete ride
- `GET /api/rides/history/{riderId}` - Get history

### Riders
- `GET /api/riders/{riderId}` - Get rider info
- `PATCH /api/riders/{riderId}/wallet` - Add to wallet

### Drivers
- `GET /api/drivers/available` - Get available drivers

## Demo Script for Person 5

### Backend Demo (2 minutes)

1. **Show IRideManagement.java**
   ```java
   public interface IRideManagement {
       Ride requestRide(...);
       Ride completeRide(...);
       // ... other methods
   }
   ```
   Say: "This is the **abstraction** interface - it defines WHAT operations are available without revealing HOW they work."

2. **Show RideManagementImpl.java**
   ```java
   @Service
   public class RideManagementImpl implements IRideManagement {
       @Autowired
       private RideService rideService;
       // Delegates to RideService
   }
   ```
   Say: "This implementation hides the details - it uses RideService internally, but nobody outside needs to know that."

3. **Show RideController.java**
   ```java
   @Autowired
   private IRideManagement rideManagement;  // Uses INTERFACE
   ```
   Say: "The controller uses the **interface**, not the concrete class. This is **abstraction** - we can swap implementations without changing controller code."

### Mobile Demo (3 minutes)

1. **Open App on Home Screen**
   - Say: "I completed screens 4 and 5, plus shared components"

2. **Navigate to History**
   - Click "View History"
   - Say: "This shows all rides with pull-to-refresh"
   - Pull down to refresh
   - Tap a ride to show details

3. **Navigate to Profile**
   - Go back to Home
   - Click "My Profile"
   - Say: "Shows rider info and wallet balance"
   - Click "Add ₹100"
   - Say: "Money added via API call to backend"
   - Show updated balance

4. **Show Components**
   - Scroll down in Profile
   - Say: "I created reusable components: RideCard, StatusBadge, and VehicleCard"
   - Point to vehicle pricing cards

## Person 5 Responsibilities Completed

✅ Backend:
- IRideManagement interface
- RideManagementImpl implementation
- Updated RideController to use interface
- Added rider endpoints

✅ Mobile:
- History screen with FlatList and refresh
- Profile screen with wallet management
- RideCard component
- StatusBadge component
- VehicleCard component
- Enhanced api.js with new endpoints
- Updated App.js navigation
- Improved styling on all screens

✅ OOP Concept: **ABSTRACTION**
- Controller uses interface, not concrete class
- Implementation details hidden
- Easy to swap implementations

## Troubleshooting

### Backend won't start
- Check if port 8080 is free
- Run `.\mvnw.cmd clean compile` first

### Mobile app can't connect
- Check API_BASE in `services/api.js`
- For Android emulator: `http://10.0.2.2:8080/api`
- For iOS simulator: `http://localhost:8080/api`
- For physical device: Use your computer's IP

### Dependencies missing
```bash
cd mobile-app
rm -rf node_modules
npm install
```

## All 5 People's Work

1. **Person 1**: Rider + Ride models (Encapsulation)
2. **Person 2**: Driver + Location + DataStore (Encapsulation)
3. **Person 3**: Vehicle hierarchy + PricingStrategy (Inheritance + Polymorphism)
4. **Person 4**: RideService + RideController + Home/Booking/Details screens (Polymorphism)
5. **Person 5**: IRideManagement + RideManagementImpl + History/Profile screens + Components (Abstraction) ✅

## Success Criteria

✅ Backend compiles without errors
✅ All REST endpoints work
✅ Mobile app runs on emulator
✅ All 5 screens functional
✅ Navigation works correctly
✅ API calls succeed
✅ OOP concepts properly demonstrated
