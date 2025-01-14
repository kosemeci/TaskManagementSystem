# Task Management System with JWT Authentication and PostgreSQL Integration

## Overview
The **Task Management System** (Task Manager) is a robust and user-friendly application designed to streamline task management. Developed using **Spring Boot**, the system provides key features like task creation, updates, task completion tracking, and deletion, all supported by a secure **PostgreSQL** database. The application follows modern software engineering practices, providing high scalability and performance. Additionally, **JWT authentication** has been integrated to ensure secure access and manage user authorization effectively.

## Key Features
- **Database Integration:** Built with **PostgreSQL** for secure and efficient data storage.
- **Validation and Exception Handling:** Comprehensive validation rules and custom exception handling.
- **Bidirectional Relationships:** One-to-Many relationships between entities like **Users** and **Tasks**.
- **DTOs for Clean Architecture:** Decoupling of domain model from API responses using **DTOs**.
- **Error Logging and Messages:** Detailed logging of errors and meaningful error messages.
- **JWT Authentication:** Secure user authentication using **JSON Web Tokens**.

## Security Implementation
JWT is integrated for secure authentication and authorization in this application. Here's how it works:
1. **JWT Token Generation** upon successful login.
2. **Token Validation** for all subsequent requests to protected endpoints.
3. **Role-Based Access Control (RBAC)** to restrict access based on user roles.

## Technical Details
- **Backend Framework:** Spring Boot
- **Database:** PostgreSQL
- **Validation:** Spring Boot validation annotations (`@NotNull`, `@Size`, etc.)
- **Error Handling:** Custom exception classes and global exception handlers with `@ControllerAdvice`
- **JWT Authentication:** JWT used for secure user authentication.
- **DTOs:** Data Transfer Objects for communication between API and domain model.
- **Relationships:** One-to-Many bidirectional relationships with `@OneToMany` and `@ManyToOne`.

## Versioning Information
- **Java Version:** 23
- **Spring Boot Version:** 3.4.1
- **PostgreSQL:** Compatible with the latest PostgreSQL database.
- **JWT Version:** 0.11.5

## How to Run
1. Clone this repository.
2. Set up **PostgreSQL** database.
3. Configure application properties with database credentials.
4. Run the application with the `spring-boot:run` Maven command.

---
