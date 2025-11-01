# Uzima Health

A comprehensive health management system built with Spring Boot.

## Features

- **Patient Management**: CRUD operations for patient records including name, age, medical history, and contact information.
- **Appointment Scheduling**: Book, update, and cancel appointments with doctors.
- **Telemedicine Integration**: Mock video call scheduling and basic chat functionality for remote consultations.
- **User Authentication and Authorization**: Secure login using Spring Security with JWT tokens for different user roles (Admin, Doctor, Patient).
- **RESTful APIs**: Well-structured endpoints for all operations with proper error handling and validation.
- **Database**: JPA with H2 in-memory database for data persistence.
- **Sample Data**: Automatic population of sample users and patients on application startup.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## How to Run

1. Navigate to the project directory:
   ```
   cd /Users/user/Documents/spring-boot/uzima-health
   ```

2. Run the application using Maven:
   ```
   mvn spring-boot:run
   ```

3. The application will start on `http://localhost:8080`

4. Access the H2 database console at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:uzimahealthdb`, Username: `sa`, Password: empty)

## API Endpoints

### Authentication
- `POST /auth/register` - Register a new user
- `POST /auth/login` - Login and receive JWT token

### Patients
- `GET /patients` - Get all patients
- `GET /patients/{id}` - Get patient by ID
- `POST /patients` - Create a new patient
- `PUT /patients/{id}` - Update patient
- `DELETE /patients/{id}` - Delete patient

### Appointments
- `GET /appointments` - Get all appointments
- `GET /appointments/patient/{patientId}` - Get appointments for a patient
- `POST /appointments` - Create a new appointment
- `PUT /appointments/{id}` - Update appointment
- `DELETE /appointments/{id}` - Delete appointment

### Chat
- `GET /chat/{sender}/{receiver}` - Get chat messages between two users
- `POST /chat` - Send a chat message

### Video Calls
- `GET /videocalls` - Get all video calls
- `POST /videocalls` - Schedule a new video call
- `PUT /videocalls/{id}` - Update video call status

## Sample Data

On startup, the application loads sample data:
- Users: admin (ADMIN), doctor1 (DOCTOR), patient1 (PATIENT) - all with password "password"
- Patients: John Doe and Jane Smith

## Security

All endpoints except `/auth/**` require authentication. Include the JWT token in the Authorization header as `Bearer <token>`.

## Project Structure

```
src/main/java/com/uzimahealth/
├── UzimaHealthApplication.java
├── config/
│   └── DataLoader.java
├── controller/
│   ├── AuthController.java
│   ├── AppointmentController.java
│   ├── ChatController.java
│   ├── PatientController.java
│   └── VideoCallController.java
├── model/
│   ├── Appointment.java
│   ├── ChatMessage.java
│   ├── Patient.java
│   ├── User.java
│   └── VideoCall.java
├── repository/
│   ├── AppointmentRepository.java
│   ├── ChatMessageRepository.java
│   ├── PatientRepository.java
│   ├── UserRepository.java
│   └── VideoCallRepository.java
├── security/
│   ├── JwtRequestFilter.java
│   ├── JwtUtil.java
│   ├── SecurityConfig.java
│   └── UserDetailsServiceImpl.java
└── service/
    ├── AppointmentService.java
    ├── ChatService.java
    ├── PatientService.java
    ├── UserService.java
    └── VideoCallService.java
```

## Technologies Used

- Spring Boot 3.2.0
- Spring Security
- Spring Data JPA
- H2 Database
- JWT for authentication
- Maven for build management