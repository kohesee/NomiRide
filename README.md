# Minimal Ride-Sharing - 5 Days, OOP Split Across 5 People

## **Tech Stack**
- **Backend**: Java Spring Boot
- **Frontend**: 1 React Native App (Expo)
- **Database**: None (ArrayList/HashMap in memory)

---

## **Person 1: ENCAPSULATION - Part 1 (Day 1-3)**

### **Responsibility**
Rider & Ride models with encapsulation

### **Deliver**
- **Rider Class**:
  ```
  private String id, name, phone
  private double walletBalance
  public getters/setters
  public boolean deductFromWallet(amount) // with validation
  public void addToWallet(amount)
  private boolean hasValidBalance(amount) // private helper
  ```

- **Ride Class**:
  ```
  private String id, riderId, driverId
  private double distance, fare
  private RideStatus status
  private LocalDateTime timestamp
  public getters/setters
  public void setFare(double fare) // with validation
  public void markCompleted()
  public boolean canComplete() // validation logic
  ```

- **RideStatus Enum**: PENDING, IN_PROGRESS, COMPLETED, CANCELLED

### **OOP Focus**:
- Private fields
- Public methods for controlled access
- Internal validation hidden from outside

---

## **Person 2: ENCAPSULATION - Part 2 (Day 1-3)**

### **Responsibility**
Driver & Location models + data storage

### **Deliver**
- **Driver Class**:
  ```
  private String id, name, vehicleType
  private DriverStatus status
  private Location location
  public getters/setters
  public void markBusy()
  public void markAvailable()
  public boolean isAvailable()
  ```

- **Location Class**:
  ```
  private double latitude, longitude
  private String address
  public getters/setters
  public double distanceTo(Location other) // calculation hidden
  ```

- **DataStore Class**:
  ```
  private static ArrayList<Rider> riders
  private static ArrayList<Driver> drivers
  private static ArrayList<Ride> rides
  public static methods to add/get/update
  ```

- **DriverStatus Enum**: AVAILABLE, BUSY, OFFLINE

- **DataSeeder**: Create 5 drivers, 3 riders on startup

### **OOP Focus**:
- Private fields with public methods
- Encapsulated data structures
- Controlled data access

---

## **Person 3: INHERITANCE + POLYMORPHISM - Part 1 (Day 2-4)**

### **Responsibility**
Vehicle hierarchy + pricing strategies

### **Deliver**

### **Inheritance**:
- **Vehicle (abstract)**:
  ```
  protected String type
  protected double baseFareMultiplier
  public abstract double getMultiplier()
  public abstract int getCapacity()
  ```

- **Car extends Vehicle**: multiplier = 1.0, capacity = 4
- **Bike extends Vehicle**: multiplier = 0.6, capacity = 1
- **Auto extends Vehicle**: multiplier = 0.8, capacity = 3

- **VehicleFactory**:
  ```
  public static Vehicle createVehicle(VehicleType type)
  ```

### **Polymorphism**:
- **PricingStrategy (interface)**:
  ```
  double calculateFare(double distance, Vehicle vehicle)
  ```

- **FlatPricing implements PricingStrategy**: base 5 + distance * 2 * multiplier
- **SurgePricing implements PricingStrategy**: base 5 + distance * 3 * multiplier

### **OOP Focus**:
- Parent-child inheritance
- Method overriding
- Interface for multiple implementations
- Runtime behavior selection

---

## **Person 4: POLYMORPHISM + ABSTRACTION + Mobile Part 1 (Day 2-4)**

### **Responsibility**
Service layer + REST API + Mobile screens 1-3

### **Backend**:
- **RideService**:
  ```
  createRide(riderId, distance, vehicleType, useSurge)
    - Find available driver (random)
    - Create vehicle using VehicleFactory
    - Select PricingStrategy based on useSurge
    - Calculate fare using strategy.calculateFare()
    - Create Ride, store in DataStore
    
  completeRide(rideId)
    - Get ride from DataStore
    - Deduct from rider wallet
    - Mark driver available
    - Mark ride completed
  
  getRideHistory(riderId)
  getRide(rideId)
  getAvailableDrivers()
  ```

- **RideController (REST API)**:
  ```
  POST /api/rides
  GET /api/rides/{id}
  PATCH /api/rides/{id}/complete
  GET /api/rides/history/{riderId}
  GET /api/drivers/available
  ```

### **Mobile App (Screens 1-3)**:
- **Setup**: Expo project, React Navigation, axios setup

- **Screen 1 - Home**:
  - Input: Distance (km)
  - Select: Vehicle type (3 buttons: Car/Bike/Auto)
  - Toggle: Surge pricing
  - Button: "Calculate Fare" → shows estimate
  - Button: "Book Ride"

- **Screen 2 - Booking Confirmation**:
  - Shows: Driver name, Vehicle, Fare, Status
  - Button: "Complete Ride"

- **Screen 3 - Ride Details**:
  - Full ride info display
  - Status indicator

### **OOP Focus**:
- Polymorphism: RideService uses PricingStrategy interface
- Same method name, different behavior at runtime

---

## **Person 5: ABSTRACTION + Mobile Part 2 (Day 3-5)**

### **Responsibility**
Abstract service layer + Mobile screens 4-5 + integration

### **Backend**:
- **IRideManagement (interface)**:
  ```
  Ride requestRide(String riderId, double distance, VehicleType type, boolean surge)
  Ride completeRide(String rideId)
  List<Ride> getUserRides(String riderId)
  Rider getRider(String riderId)
  ```

- **RideManagementImpl (implements IRideManagement)**:
  ```
  @Autowired RideService rideService
  // Delegates to RideService
  // Hides implementation details from controller
  ```

- **Update RideController**: Use IRideManagement interface instead of RideService directly

### **Mobile App (Screens 4-5)**:

- **Screen 4 - Ride History**:
  - FlatList of all rides
  - Shows: Date, Driver, Vehicle, Fare, Status
  - Tap to view details (navigate to screen 3)
  - Pull to refresh

- **Screen 5 - Profile**:
  - Display: Name, Wallet balance
  - Button: "Add ₹100" → updates wallet via API
  - Info: Vehicle pricing info (static)

- **API Service (`api.js`)**:
  ```javascript
  createRide(data)
  getRide(id)
  completeRide(id)
  getRideHistory(riderId)
  ```

- **Shared Components**:
  - VehicleCard
  - RideCard
  - StatusBadge

### **OOP Focus**:
- Abstraction: Controller uses interface, not concrete implementation
- Implementation details hidden
- Can swap implementations without changing controller

---

## **Daily Plan**

### **Day 1**
- **Person 1**: Rider + Ride classes
- **Person 2**: Driver + Location classes + DataStore
- **Person 3**: Vehicle hierarchy (abstract + 3 subclasses)
- **Person 4**: React Native project setup
- **Person 5**: Help Person 4 with setup

### **Day 2**
- **Person 1**: Add validations, test encapsulation
- **Person 2**: DataSeeder with sample data
- **Person 3**: PricingStrategy interface + implementations + VehicleFactory
- **Person 4**: RideService (using Person 3's strategies)
- **Person 5**: IRideManagement interface

### **Day 3**
- **Person 1 & 2**: Help integrate models with services
- **Person 3**: Share code with Person 4
- **Person 4**: RideController + Home screen
- **Person 5**: RideManagementImpl + Profile screen

### **Day 4**
- **Person 1 & 2**: Bug fixes if needed
- **Person 3**: Help Person 4 with any issues
- **Person 4**: Screens 2-3, API integration
- **Person 5**: History screen, complete api.js

### **Day 5**
- **Person 4**: Polish screens 1-3, test flows
- **Person 5**: Polish screens 4-5, full integration
- **All**: End-to-end testing together (1 hour)
- **All**: Practice demo presentation (30 min)

---

## **File Structure**

```
ride-share/
├── backend/
│   └── src/main/java/com/rideshare/
│       ├── models/
│       │   ├── Rider.java           (Person 1)
│       │   ├── Ride.java            (Person 1)
│       │   ├── Driver.java          (Person 2)
│       │   └── Location.java        (Person 2)
│       ├── enums/
│       │   ├── RideStatus.java      (Person 1)
│       │   ├── DriverStatus.java    (Person 2)
│       │   └── VehicleType.java     (Person 3)
│       ├── vehicles/
│       │   ├── Vehicle.java         (Person 3)
│       │   ├── Car.java             (Person 3)
│       │   ├── Bike.java            (Person 3)
│       │   ├── Auto.java            (Person 3)
│       │   └── VehicleFactory.java  (Person 3)
│       ├── strategies/
│       │   ├── PricingStrategy.java (Person 3)
│       │   ├── FlatPricing.java     (Person 3)
│       │   └── SurgePricing.java    (Person 3)
│       ├── services/
│       │   ├── RideService.java     (Person 4)
│       │   ├── IRideManagement.java (Person 5)
│       │   └── RideManagementImpl.java (Person 5)
│       ├── controllers/
│       │   └── RideController.java  (Person 4 & 5)
│       └── utils/
│           ├── DataStore.java       (Person 2)
│           └── DataSeeder.java      (Person 2)
│
└── mobile-app/
    ├── screens/
    │   ├── Home.js                  (Person 4)
    │   ├── BookingConfirmation.js   (Person 4)
    │   ├── RideDetails.js           (Person 4)
    │   ├── History.js               (Person 5)
    │   └── Profile.js               (Person 5)
    ├── components/                  (Person 5)
    │   ├── VehicleCard.js
    │   ├── RideCard.js
    │   └── StatusBadge.js
    ├── services/
    │   └── api.js                   (Person 5)
    └── App.js                       (Person 4)
```

---

## **OOP Distribution**

### **Encapsulation**: Person 1 (50%) + Person 2 (50%)
- Person 1: Rider + Ride with private fields & validations
- Person 2: Driver + Location + DataStore with controlled access

### **Inheritance**: Person 3 (100%)
- Vehicle → Car/Bike/Auto hierarchy
- VehicleFactory

### **Polymorphism**: Person 3 (50%) + Person 4 (50%)
- Person 3: PricingStrategy interface + implementations
- Person 4: Uses strategies in RideService (demonstrates runtime polymorphism)

### **Abstraction**: Person 5 (100%)
- IRideManagement interface
- RideManagementImpl hides details from controller

---

## **Demo Script (5 minutes)**

**Minute 1** - Person 1:
- Show Rider.java with private walletBalance
- Show deductFromWallet() method with validation
- "**Encapsulation** - data is protected"

**Minute 2** - Person 2:
- Show Driver.java with private status
- Show markBusy() and markAvailable() methods
- "**Encapsulation** - controlled state changes"

**Minute 3** - Person 3:
- Show Vehicle hierarchy: Vehicle → Car/Bike/Auto
- Show PricingStrategy interface with two implementations
- "**Inheritance** reuses code, **Polymorphism** changes behavior"

**Minute 4** - Person 4:
- Open Postman, create ride with flat pricing
- Create ride with surge pricing (same distance, different fare)
- Open mobile app, book a ride
- "**Polymorphism** in action - same method, different results"

**Minute 5** - Person 5:
- Show IRideManagement interface
- Show RideController using interface (not concrete class)
- Complete ride in mobile app
- Show history screen with past rides
- "**Abstraction** - controller doesn't know implementation details"

---

## **Work Distribution** (Actual Hours)

**Person 1**: ~10 hours (2 models + validations)
**Person 2**: ~12 hours (2 models + DataStore + seeding)
**Person 3**: ~15 hours (inheritance + polymorphism)
**Person 4**: ~16 hours (service + API + 3 screens)
**Person 5**: ~12 hours (abstraction + 2 screens + integration)

---

**Perfect: 4 OOP concepts split across 5 people, 2 on mobile, no documentation burden!** ✅