# ignite 

A Spring Boot application for workout clubs/gyms. 

## Architecture

### Controllers (REST API Layer)
- `ClassController`: Manages studio class operations
- `BookingController`: Handles booking operations

### Services (Business Logic Layer)
- `IClassService` & Implementation: Manages class creation and availability
- `IBookingService` & Implementation: Handles booking logic and validation

### Models (Domain Layer)
- `StudioClass`: Represents a class series with dates and capacity
- `ClassBooking`: Represents a member's booking
- `BookingStatus`: Enum for booking states

## API Endpoints

### Classes API (`/classes`)

#### Create Class
- **POST** `/classes`
- Creates a new class series
- Request body:
```json
{
    "className": "Pilates",
    "startDate": "2024-12-01",
    "endDate": "2024-12-20",
    "capacity": 10
}
```

#### Get All Classes
- **GET** `/classes`
- Returns all available classes

### Bookings API (`/bookings`)

#### Book Class
- **POST** `/bookings`
- Books a class for a member
- Request body:
```json
{
    "memberName": "John Doe",
    "className": "Pilates",
    "bookingDate": "2024-12-05"
}
```

#### Get Bookings
- **GET** `/bookings/{className}/{date}`
- Returns all bookings for a specific class and date

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Build and Run

### Build
```bash
mvn clean package
```

### Run Application
```bash
mvn spring-boot:run
```

## Development Setup

1. Clone the repository:
```bash
git clone  https://github.com/Navneet-Kumar517/glofox.git
```

2. Navigate to project directory:
```bash
cd glofox
```

3. Install dependencies:
```bash
mvn install
```

## Testing

### Curl Examples

1. Create a class:
```bash
curl -X POST http://localhost:8080/classes \
     -H "Content-Type: application/json" \
     -d '{
         "className": "Pilates",
         "startDate": "2024-12-01",
         "endDate": "2024-12-20",
         "capacity": 10
     }'
```

2. Book a class:
```bash
curl -X POST http://localhost:8080/bookings \
     -H "Content-Type: application/json" \
     -d '{
         "memberName": "John Doe",
         "className": "Pilates",
         "bookingDate": "2024-12-05"
     }'
```

## Error Handling

The API returns appropriate HTTP status codes:
- 200: Success
- 400: Bad Request (invalid input)
- 500: Internal Server Error

Error responses include a message explaining the issue.
