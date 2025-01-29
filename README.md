# ignite 

A Spring Boot application for workout class creation and booking at clubs/gyms. 

## Architecture

### Controllers (REST API Layer)
- `WorkoutClassController`: Manages gyms class operations
- `BookingController`: Handles booking of the classes

### Services (Business Logic Layer)
- `WorkoutClassService`: Manages class creation and availability
- `BookingService`: Handles booking logic and validation

### Models (Domain Layer)
- `WorkoutClass`: Represents a class
- `Booking`: Represents a member's booking

## API Endpoints

### Classes API (`/api/classes`)

#### Create Class
- **POST** `/api/classes`
- Creates a new class series
- Request body:
```json
{
    "name": "Dance",
    "startDate": "2025-06-01",
    "endDate": "2025-06-20",
    "startTime": "14:00",
    "durationMinutes": 60,
    "capacity": 10
}
```

#### Get All Classes
- **GET** `api/classes`
- Returns all available classes

### Bookings API (`/api/bookings`)

#### Book Class
- **POST** `/api/bookings`
- Books a class for a member
- Request body:
```json
{
    "memberName": "Diwakar",
    "className": "Dance",
    "participationDate": "2025-02-05"
}
```

#### Get Bookings
- **GET** `/bookings//search/{memberName}/{startDate}/{endDate}`
- Returns all bookings for a specific member AND/OR date range

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
git clone https://github.com/diwakarDRK/ignite
```

2. Navigate to project directory:
```bash
cd ignite
```

3. Install dependencies:
```bash
mvn install
```

## Testing

### Curl Examples

1. Create a Workout Class:
```bash
curl --location 'localhost:8080/api/classes' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Dance",
    "startDate": "2025-06-01",
    "endDate": "2025-06-20",
    "startTime": "14:00",
    "durationMinutes": 60,
    "capacity": 10
}'
```

2. Book a workout class:
```bash
curl --location 'localhost:8080/api/bookings' \
--header 'Content-Type: application/json' \
--data '{
    "memberName": "Diwakar",
    "className": "Dance",
    "participationDate": "2025-02-05"
}'
```
3. Search booking with optional paramaters
   ```bash
   curl --location 'localhost:8080/api/bookings/search?memberName=Diwakar&startDate=null&endDate=null'
   ```

## Error Handling

The API returns appropriate HTTP status codes:
- 200: Success
- 400: Bad Request (invalid input)
- 500: Internal Server Error

Error responses include a message explaining the issue.
