# NomiRide: Nominal Ride-Sharing

NomiRide is a minimal ride-sharing application built as **"a boilerplate for the next generation of Mobility as a Service apps"**. It demonstrates the four core pillars of Object Oriented Programming in a real world scenario. The project consists of a Java Spring Boot backend and a React Native mobile application.

---

## 🚀 Tech Stack

- **Backend**: Java 17, Spring Boot 3.x, Maven
- **Frontend**: React Native (Expo), React Navigation, Axios
- **Database**: In-memory (ArrayList/HashMap) for simplicity

---

## 🏛️ OOP Concepts Demonstrated

### 1. Encapsulation
Data is protected by making fields private and providing controlled access through public methods.
- **Example**: `Rider` and `Driver` classes hide internal state (like wallet balance or availability) and use validation logic within setters and helper methods.
- **File**: `backend/src/main/java/com/rideshare/models/`

### 2. Inheritance
A clear hierarchy is used to reuse code and define specific behaviors for different vehicle types.
- **Example**: An abstract `Vehicle` class is extended by `Car`, `Bike`, and `Auto`, each defining its own passenger capacity and fare multiplier.
- **File**: `backend/src/main/java/com/rideshare/vehicles/`

### 3. Polymorphism
Dynamic behavior is achieved through interfaces and method overriding, allowing the system to switch logic at runtime.
- **Example**: The `PricingStrategy` interface has multiple implementations (`FlatPricing`, `SurgePricing`). The `RideService` calculates fares polymorphically based on the selected strategy.
- **File**: `backend/src/main/java/com/rideshare/strategies/`

### 4. Abstraction
Implementation details are hidden from the outer layers (like controllers) using interfaces, promoting loose coupling.
- **Example**: `IRideManagement` interface defines the contract for ride operations, while `RideManagementImpl` provides the implementation. The controller only interacts with the interface.
- **File**: `backend/src/main/java/com/rideshare/services/`

---

## 📁 Project Structure

```text
NomiRide/
├── backend/                # Spring Boot REST API
│   ├── src/main/java/.../
│   │   ├── models/         # Encapsulation focus
│   │   ├── vehicles/       # Inheritance focus
│   │   ├── strategies/     # Polymorphism focus
│   │   └── services/       # Abstraction focus
│   └── pom.xml
└── mobile-app/             # React Native (Expo) App
    ├── screens/            # Home, Booking, Details, History, Profile
    ├── services/           # API integration (Axios)
    └── App.js              # Navigation setup
```

---

## ⚙️ Getting Started

### Backend (Spring Boot)
1. Navigate to the `backend` directory.
2. Run the application using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
3. The API will be available at `http://localhost:8080`.
4. *Note: `DataSeeder` automatically populates the in-memory store with 5 drivers and 3 riders on startup.*

### Mobile App (React Native)
1. Navigate to the `mobile-app` directory.
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the Expo development server:
   ```bash
   npx expo start
   ```
4. Open the app using the Expo Go app (on a physical device) or an emulator.
5. *Note: Ensure the `API_BASE` in `mobile-app/services/api.js` points to your machine's IP address if testing on a physical device.*

---

## 🛠️ Core Features
- **Estimate Fare**: Calculate costs dynamically based on distance, vehicle type, and surge pricing.
- **Book a Ride**: Automatically matches with an available driver from the in-memory pool.
- **Ride Lifecycle**: Track rides from `PENDING` to `COMPLETED`.
- **Wallet Management**: Simulated wallet deductions and top-ups for riders.

---

## 👥 Contributors
Developed as a collaborative project to showcase OOP excellence in a team environment.
