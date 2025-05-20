# API ENDPOINTS

## Students Endpoint
### GET All Students
#### `GET` | `http://localhost:8080/students/all`

_No body for this request._

---

### FIND Student By Id
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

### ENROLL Course
#### `POST` | `http://localhost:8080/students/{id}-add-course`

_No body for this request._

---

### DROP A Course
#### `DELETE` | `http://localhost:8080/students/{id}-drop-course`

_No body for this request._

---

### GET All Enrolled Courses
#### `GET` | `http://localhost:8080/students/{id}-all-enrolled-courses`

_No body for this request._

---

### GET All Expected Courses
#### `GET` | `http://localhost:8080/students/{id}-all-expected-courses`

_No body for this request._

## Teacher Endpoint

---

### GET All Teachers
#### `GET` | `http://localhost:8080/teacher/all`

_No body for this request._

---

### GET Teacher By Id
#### `GET` | `http://localhost:8080/teacher/{id}`

_No body for this request._

---

### ADD Teacher Password
#### `PUT` | `http://localhost:8080/teacher/{id}-add-password`

**Body Type:** `application/json`

**Example Request Body:**
```json
{
  "password": "MySecureP@ssw0rd",
  "confirmPassword": "MySecureP@ssw0rd"
}
```

---

### CREATE A New Teacher
#### `POST` | `http://localhost:8080/teacher/new`

**Body Type:** `application/json`

**Example Request Body:**
```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com"
}
```

---

### DELETE A Teacher
#### `POST` | `http://localhost:8080/teacher/{id}-delete`

_No body for this request._

---

### RESET Teacher Password
#### `PUT` | `http://localhost:8080/teacher/{id}-reset-password`

**Body Type:** `application/json`

**Example Request Body:**
```json
{
  "oldPassword": "wjeipw",
  "newPassword": "rufus",
  "confirmNewPassword": "rufus"
}
```

---

### CREATE A Course
#### `POST` | `http://localhost:8080/teacher/{id}-course-new`

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

### DROP A Course
#### `DELETE` | `http://localhost:8080/teacher/{id}-course-delete/{courseId}`

_No body for this request._

---

### ADD All Expected Students
#### `POST` | `http://localhost:8080/teacher/{id}-course-add-expected-students/{courseId}`

_No body for this request._

---
