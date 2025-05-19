# API ENDPOINTS

## Students Endpoint
### GET All Students
#### `GET` | `http://localhost:8080/students/all`

_No body for this request._

---

### FIND Student By Name
#### `GET` | `http://localhost:8080/students/{id}`

_No body for this request._

---

### CREATE A new Student
#### `POST` | `http://localhost:8080/students/new`

**Body Type:** `application/json`

**Example Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com"
}
```

---

### DELETE A Student
#### `DELETE` | `http://localhost:8080/students/{id}-delete`

_No body for this request._

---

### ADD Student Password
#### `PUT` | `http://localhost:8080/students/{id}-add-password`

**Body Type:** `application/json`

**Example Request Body:**
```json
{
  "password": "MySecureP@ssw0rd",
  "confirmPassword": "MySecureP@ssw0rd"
}
```

---

### RESET Student Password
#### `PUT` | `http://localhost:8080/students/{id}-reset-password`

**Body Type:** `application/json`

**Example Request Body:**
```json
{
  "oldPassword": "wjeipw",
  "newPassword": "rufus",
  "confirmNewPassword": "rufus"
}
```

## Course Endpoint

---

### GET All Courses
#### `GET` | `http://localhost:8080/course/all`

_No body for this request._

---

### GET Course By Id
#### `GET` | `http://localhost:8080/course/{id}`

_No body for this request._

---

### CREATE A New Course
#### `POST` | `http://localhost:8080/course/new`

**Body Type:** `application/json`

**Example Request Body:**
```json
{
  "courseName": "CSC",
  "capacity":2,
  "level": 200
}
```

---

### DELETE A Course
#### `DELETE` | `http://localhost:8080/course/{id}-delete`

_No body for this request._

---

### ENROLL A Course
#### `POST` | `http://localhost:8080/students/4-add-course?courseName=CSC201`

_No body for this request._

---