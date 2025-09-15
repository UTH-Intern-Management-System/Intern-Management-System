# Interview Schedule Service API Documentation

## Overview
This service provides REST APIs for managing interview schedules in the Intern Management System. It allows coordinators to create, read, update, and delete interview schedules, as well as filter and search through them.

## Base URL
```
http://localhost:8083/api/v1/interview-schedules
```

## API Endpoints

### 1. Create Interview Schedule
**POST** `/api/v1/interview-schedules`

Creates a new interview schedule.

**Request Body:**
```json
{
  "candidateName": "John Doe",
  "candidateEmail": "john.doe@example.com",
  "position": "Software Engineer",
  "department": "Engineering",
  "interviewType": "TECHNICAL",
  "scheduledDate": "2024-01-15",
  "scheduledTime": "14:00",
  "duration": 60,
  "location": "Conference Room A",
  "interviewerIds": [1, 2],
  "notes": "Technical interview for backend position",
  "createdBy": 1
}
```

**Response:**
```json
{
  "success": true,
  "message": "Interview schedule created successfully",
  "data": {
    "id": 1,
    "candidateName": "John Doe",
    "candidateEmail": "john.doe@example.com",
    "position": "Software Engineer",
    "department": "Engineering",
    "interviewType": "TECHNICAL",
    "scheduledDate": "2024-01-15",
    "scheduledTime": "14:00",
    "duration": 60,
    "location": "Conference Room A",
    "interviewerIds": [1, 2],
    "status": "SCHEDULED",
    "notes": "Technical interview for backend position",
    "createdBy": 1,
    "createdAt": "2024-01-14T10:00:00",
    "updatedAt": "2024-01-14T10:00:00"
  },
  "timestamp": "2024-01-14T10:00:00"
}
```

### 2. Get Interview Schedule by ID
**GET** `/api/v1/interview-schedules/{id}`

Retrieves a specific interview schedule by its ID.

**Response:**
```json
{
  "success": true,
  "message": "Interview schedule retrieved successfully",
  "data": {
    "id": 1,
    "candidateName": "John Doe",
    "candidateEmail": "john.doe@example.com",
    "position": "Software Engineer",
    "department": "Engineering",
    "interviewType": "TECHNICAL",
    "scheduledDate": "2024-01-15",
    "scheduledTime": "14:00",
    "duration": 60,
    "location": "Conference Room A",
    "interviewerIds": [1, 2],
    "status": "SCHEDULED",
    "notes": "Technical interview for backend position",
    "createdBy": 1,
    "createdAt": "2024-01-14T10:00:00",
    "updatedAt": "2024-01-14T10:00:00"
  },
  "timestamp": "2024-01-14T10:00:00"
}
```

### 3. Update Interview Schedule
**PUT** `/api/v1/interview-schedules/{id}`

Updates an existing interview schedule.

**Request Body:**
```json
{
  "candidateName": "John Doe Updated",
  "status": "COMPLETED",
  "notes": "Interview completed successfully"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Interview schedule updated successfully",
  "data": {
    "id": 1,
    "candidateName": "John Doe Updated",
    "candidateEmail": "john.doe@example.com",
    "position": "Software Engineer",
    "department": "Engineering",
    "interviewType": "TECHNICAL",
    "scheduledDate": "2024-01-15",
    "scheduledTime": "14:00",
    "duration": 60,
    "location": "Conference Room A",
    "interviewerIds": [1, 2],
    "status": "COMPLETED",
    "notes": "Interview completed successfully",
    "createdBy": 1,
    "createdAt": "2024-01-14T10:00:00",
    "updatedAt": "2024-01-14T11:00:00"
  },
  "timestamp": "2024-01-14T11:00:00"
}
```

### 4. Delete Interview Schedule
**DELETE** `/api/v1/interview-schedules/{id}`

Deletes an interview schedule.

**Response:**
```json
{
  "success": true,
  "message": "Interview schedule deleted successfully",
  "data": null,
  "timestamp": "2024-01-14T11:00:00"
}
```

### 5. Get All Interview Schedules (with filters)
**GET** `/api/v1/interview-schedules`

Retrieves all interview schedules with optional filtering and pagination.

**Query Parameters:**
- `candidateName` (optional): Filter by candidate name
- `position` (optional): Filter by position
- `department` (optional): Filter by department
- `interviewType` (optional): Filter by interview type (TECHNICAL, BEHAVIORAL, PORTFOLIO_REVIEW, HR, FINAL)
- `status` (optional): Filter by status (SCHEDULED, PENDING, COMPLETED, CANCELLED, RESCHEDULED)
- `startDate` (optional): Filter by start date (YYYY-MM-DD)
- `endDate` (optional): Filter by end date (YYYY-MM-DD)
- `interviewerId` (optional): Filter by interviewer ID
- `createdBy` (optional): Filter by creator ID
- `sortBy` (optional): Sort field (default: scheduledDate)
- `sortDirection` (optional): Sort direction (ASC/DESC, default: ASC)
- `page` (optional): Page number (default: 0)
- `size` (optional): Page size (default: 10)

**Example:**
```
GET /api/v1/interview-schedules?department=Engineering&status=SCHEDULED&page=0&size=10
```

**Response:**
```json
{
  "success": true,
  "message": "Interview schedules retrieved successfully",
  "data": {
    "content": [
      {
        "id": 1,
        "candidateName": "John Doe",
        "candidateEmail": "john.doe@example.com",
        "position": "Software Engineer",
        "department": "Engineering",
        "interviewType": "TECHNICAL",
        "scheduledDate": "2024-01-15",
        "scheduledTime": "14:00",
        "duration": 60,
        "location": "Conference Room A",
        "interviewerIds": [1, 2],
        "status": "SCHEDULED",
        "notes": "Technical interview for backend position",
        "createdBy": 1,
        "createdAt": "2024-01-14T10:00:00",
        "updatedAt": "2024-01-14T10:00:00"
      }
    ],
    "pageable": {
      "sort": {
        "sorted": true,
        "unsorted": false
      },
      "pageNumber": 0,
      "pageSize": 10,
      "offset": 0,
      "paged": true,
      "unpaged": false
    },
    "totalElements": 1,
    "totalPages": 1,
    "last": true,
    "first": true,
    "numberOfElements": 1,
    "size": 10,
    "number": 0,
    "empty": false
  },
  "timestamp": "2024-01-14T10:00:00"
}
```

### 6. Get Upcoming Interviews
**GET** `/api/v1/interview-schedules/upcoming`

Retrieves all upcoming interviews (scheduled for today or later).

**Response:**
```json
{
  "success": true,
  "message": "Upcoming interviews retrieved successfully",
  "data": [
    {
      "id": 1,
      "candidateName": "John Doe",
      "candidateEmail": "john.doe@example.com",
      "position": "Software Engineer",
      "department": "Engineering",
      "interviewType": "TECHNICAL",
      "scheduledDate": "2024-01-15",
      "scheduledTime": "14:00",
      "duration": 60,
      "location": "Conference Room A",
      "interviewerIds": [1, 2],
      "status": "SCHEDULED",
      "notes": "Technical interview for backend position",
      "createdBy": 1,
      "createdAt": "2024-01-14T10:00:00",
      "updatedAt": "2024-01-14T10:00:00"
    }
  ],
  "timestamp": "2024-01-14T10:00:00"
}
```

### 7. Get Today's Interviews
**GET** `/api/v1/interview-schedules/today`

Retrieves all interviews scheduled for today.

**Response:**
```json
{
  "success": true,
  "message": "Today's interviews retrieved successfully",
  "data": [
    {
      "id": 1,
      "candidateName": "John Doe",
      "candidateEmail": "john.doe@example.com",
      "position": "Software Engineer",
      "department": "Engineering",
      "interviewType": "TECHNICAL",
      "scheduledDate": "2024-01-14",
      "scheduledTime": "14:00",
      "duration": 60,
      "location": "Conference Room A",
      "interviewerIds": [1, 2],
      "status": "SCHEDULED",
      "notes": "Technical interview for backend position",
      "createdBy": 1,
      "createdAt": "2024-01-14T10:00:00",
      "updatedAt": "2024-01-14T10:00:00"
    }
  ],
  "timestamp": "2024-01-14T10:00:00"
}
```

### 8. Get Interviews by Status
**GET** `/api/v1/interview-schedules/status/{status}`

Retrieves all interviews with a specific status.

**Path Parameters:**
- `status`: Interview status (SCHEDULED, PENDING, COMPLETED, CANCELLED, RESCHEDULED)

**Response:**
```json
{
  "success": true,
  "message": "Interviews retrieved successfully",
  "data": [
    {
      "id": 1,
      "candidateName": "John Doe",
      "candidateEmail": "john.doe@example.com",
      "position": "Software Engineer",
      "department": "Engineering",
      "interviewType": "TECHNICAL",
      "scheduledDate": "2024-01-15",
      "scheduledTime": "14:00",
      "duration": 60,
      "location": "Conference Room A",
      "interviewerIds": [1, 2],
      "status": "SCHEDULED",
      "notes": "Technical interview for backend position",
      "createdBy": 1,
      "createdAt": "2024-01-14T10:00:00",
      "updatedAt": "2024-01-14T10:00:00"
    }
  ],
  "timestamp": "2024-01-14T10:00:00"
}
```

### 9. Get Interviews by Department
**GET** `/api/v1/interview-schedules/department/{department}`

Retrieves all interviews for a specific department.

**Path Parameters:**
- `department`: Department name

**Response:**
```json
{
  "success": true,
  "message": "Interviews retrieved successfully",
  "data": [
    {
      "id": 1,
      "candidateName": "John Doe",
      "candidateEmail": "john.doe@example.com",
      "position": "Software Engineer",
      "department": "Engineering",
      "interviewType": "TECHNICAL",
      "scheduledDate": "2024-01-15",
      "scheduledTime": "14:00",
      "duration": 60,
      "location": "Conference Room A",
      "interviewerIds": [1, 2],
      "status": "SCHEDULED",
      "notes": "Technical interview for backend position",
      "createdBy": 1,
      "createdAt": "2024-01-14T10:00:00",
      "updatedAt": "2024-01-14T10:00:00"
    }
  ],
  "timestamp": "2024-01-14T10:00:00"
}
```

### 10. Get Interviews by Interviewer
**GET** `/api/v1/interview-schedules/interviewer/{interviewerId}`

Retrieves all interviews for a specific interviewer.

**Path Parameters:**
- `interviewerId`: Interviewer ID

**Response:**
```json
{
  "success": true,
  "message": "Interviews retrieved successfully",
  "data": [
    {
      "id": 1,
      "candidateName": "John Doe",
      "candidateEmail": "john.doe@example.com",
      "position": "Software Engineer",
      "department": "Engineering",
      "interviewType": "TECHNICAL",
      "scheduledDate": "2024-01-15",
      "scheduledTime": "14:00",
      "duration": 60,
      "location": "Conference Room A",
      "interviewerIds": [1, 2],
      "status": "SCHEDULED",
      "notes": "Technical interview for backend position",
      "createdBy": 1,
      "createdAt": "2024-01-14T10:00:00",
      "updatedAt": "2024-01-14T10:00:00"
    }
  ],
  "timestamp": "2024-01-14T10:00:00"
}
```

### 11. Update Interview Status
**PATCH** `/api/v1/interview-schedules/{id}/status`

Updates the status of an interview.

**Path Parameters:**
- `id`: Interview ID

**Query Parameters:**
- `status`: New status (SCHEDULED, PENDING, COMPLETED, CANCELLED, RESCHEDULED)

**Example:**
```
PATCH /api/v1/interview-schedules/1/status?status=COMPLETED
```

**Response:**
```json
{
  "success": true,
  "message": "Interview status updated successfully",
  "data": {
    "id": 1,
    "candidateName": "John Doe",
    "candidateEmail": "john.doe@example.com",
    "position": "Software Engineer",
    "department": "Engineering",
    "interviewType": "TECHNICAL",
    "scheduledDate": "2024-01-15",
    "scheduledTime": "14:00",
    "duration": 60,
    "location": "Conference Room A",
    "interviewerIds": [1, 2],
    "status": "COMPLETED",
    "notes": "Technical interview for backend position",
    "createdBy": 1,
    "createdAt": "2024-01-14T10:00:00",
    "updatedAt": "2024-01-14T11:00:00"
  },
  "timestamp": "2024-01-14T11:00:00"
}
```

### 12. Get Interview Statistics
**GET** `/api/v1/interview-schedules/statistics`

Retrieves interview statistics.

**Response:**
```json
{
  "success": true,
  "message": "Statistics retrieved successfully",
  "data": {
    "totalInterviews": 50,
    "scheduledInterviews": 20,
    "completedInterviews": 25,
    "cancelledInterviews": 3,
    "pendingInterviews": 2
  },
  "timestamp": "2024-01-14T10:00:00"
}
```

## Data Models

### InterviewSchedule
- `id`: Long - Unique identifier
- `candidateName`: String - Name of the candidate
- `candidateEmail`: String - Email of the candidate
- `position`: String - Position being interviewed for
- `department`: String - Department
- `interviewType`: Enum - Type of interview (TECHNICAL, BEHAVIORAL, PORTFOLIO_REVIEW, HR, FINAL)
- `scheduledDate`: LocalDate - Date of the interview
- `scheduledTime`: LocalTime - Time of the interview
- `duration`: Integer - Duration in minutes
- `location`: String - Interview location
- `interviewerIds`: List<Long> - List of interviewer IDs
- `status`: Enum - Interview status (SCHEDULED, PENDING, COMPLETED, CANCELLED, RESCHEDULED)
- `notes`: String - Additional notes
- `createdBy`: Long - ID of the coordinator who created the interview
- `createdAt`: LocalDateTime - Creation timestamp
- `updatedAt`: LocalDateTime - Last update timestamp

## Error Responses

### 400 Bad Request
```json
{
  "success": false,
  "message": "Validation failed",
  "data": {
    "candidateName": "Candidate name is required",
    "candidateEmail": "Invalid email format"
  },
  "timestamp": "2024-01-14T10:00:00"
}
```

### 404 Not Found
```json
{
  "success": false,
  "message": "Data not found",
  "error": "Interview schedule not found with ID: 999",
  "timestamp": "2024-01-14T10:00:00"
}
```

### 500 Internal Server Error
```json
{
  "success": false,
  "message": "An unexpected error occurred",
  "error": "Internal server error",
  "timestamp": "2024-01-14T10:00:00"
}
```

## Running the Service

1. Make sure MySQL is running on localhost:3306
2. Create a database named `ims_schedule`
3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
4. The service will be available at `http://localhost:8083`

## Testing

Run the test suite:
```bash
mvn test
```

## Database Schema

The service will automatically create the following tables:
- `interview_schedules` - Main interview schedule table
- `interview_interviewers` - Junction table for interviewers
