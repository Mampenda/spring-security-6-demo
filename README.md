# Spring Security 6 Demo

This project is a demonstration of how to use Spring Security 6 with JWT (JSON Web Tokens) for authentication and authorization in a Spring Boot application. It includes user registration, login, and role-based access control.

## Features

- User registration
- User login
- JWT-based authentication
- Role-based access control (ADMIN, CLIENT)
- Stateless session management

## Technologies Used

- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- JPA (Java Persistence API)
- H2 Database (in-memory)
- Lombok

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/spring-security-6-demo.git
   cd spring-security-6-demo
   ```

2. Build the project using Maven:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

4. The application will start on `http://localhost:8000`.

### API Endpoints

#### User Registration

- **URL:** `/auth/register`
- **Method:** `POST`
- **Request Body:**

  ```json
  {
    "username": "Alice",
    "password": "password"
  }
  ```

- **Response:**

  ```json
  {
    "userId": 1,
    "username": "Alice",
    "password": "$2a$10$...",
    "version": 0,
    "authorities": [
      {
        "roleId": 2,
        "authority": "CLIENT"
      }
    ],
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true,
    "enabled": true
  }
  ```

#### User Login

- **URL:** `/auth/login`
- **Method:** `POST`
- **Request Body:**

  ```json
  {
    "username": "Alice",
    "password": "password"
  }
  ```

- **Response:**

  ```json
  {
    "user": {
      "userId": 1,
      "username": "Alice",
      "authorities": [
        {
          "roleId": 2,
          "authority": "CLIENT"
        }
      ]
    },
    "jwt": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
  ```

### Accessing Protected Endpoints

- **URL:** `/admin/**`
- **Role Required:** `ADMIN`

- **URL:** `/user/**`
- **Roles Required:** `ADMIN`, `CLIENT`

### Configuration

The application uses an in-memory H2 database for demonstration purposes. You can access the H2 console at `http://localhost:8000/h2-console` with the following credentials:

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** `password`

### References

- [Spring Security Login System Repository](https://github.com/unknownkoder/spring-security-login-system/tree/main)
- [YouTube Video: Spring Security Login System](https://www.youtube.com/watch?v=TeBt0Ike_Tk)

### License

This project is licensed under the MIT License. See the LICENSE file for details.
