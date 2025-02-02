# Task Management System with JWT Authentication and PostgreSQL Integration

## Overview
The **Task Management System** (Task Manager) is a robust and user-friendly application designed to streamline task management. Developed using **Spring Boot**, the system provides key features like task creation, updates, task completion tracking, and deletion, all supported by a secure **PostgreSQL** database. The application follows modern software engineering practices, providing high scalability and performance. Additionally, **JWT authentication** has been integrated to ensure secure access and manage user authorization effectively.

## Key Features
✅ Database Integration: Built with **PostgreSQL** for secure and efficient data storage.
✅ Validation and Exception Handling: Comprehensive validation rules and custom exception handling.
✅ Bidirectional Relationships: One-to-Many relationships between entities like **Users** and **Tasks**.
✅ DTOs for Clean Architecture: Decoupling of domain model from API responses using **DTOs**.
✅ Error Logging and Messages: Detailed logging of errors and meaningful error messages.
✅ JWT Authentication: Secure user authentication using **JSON Web Tokens**.
✅ SMTP Email Service: Sends welcome emails upon user registration.

## Security Implementation
JWT is integrated for secure authentication and authorization in this application. Here's how it works:
1. **JWT Token Generation** upon successful login.
2. **Token Validation** for all subsequent requests to protected endpoints.
3. **Role-Based Access Control (RBAC)** to restrict access based on user roles.

## Technical Details
🔹 Backend Framework: Spring Boot
🔹 Database: PostgreSQL
🔹 Validation: Spring Boot validation annotations (`@NotNull`, `@Size`, etc.)
🔹 Error Handling: Custom exception classes and global exception handlers with `@ControllerAdvice`
🔹 JWT Authentication: JWT used for secure user authentication.
🔹 DTOs: Data Transfer Objects for communication between API and domain model.
🔹 Relationships: One-to-Many bidirectional relationships with `@OneToMany` and `@ManyToOne`.
🔹 SMTP Email Service: Integrated for sending welcome emails.

## Versioning Information
- **Java Version:** 23
- **Spring Boot Version:** 3.4.1
- **PostgreSQL:** Compatible with the latest PostgreSQL database.
- **JWT Version:** 0.11.5

  SMTP Email Service
This application integrates an SMTP email service to send a welcome email when a user registers. The service is configured to use Gmail’s SMTP server, but it can be adapted to any other email provider.

How Email Sending Works?
1️⃣ When a new user registers, an event is triggered.
2️⃣ The MailService class generates a welcome email.
3️⃣ The email is sent via Spring Boot’s JavaMailSender.

SMTP Configuration in application.properties
To enable email sending, configure the following properties in application.properties:

```properties
# SMTP Configuration
spring.mail.host=smtp.gmail.com 
spring.mail.port=587 
spring.mail.username=your-email@gmail.com 
spring.mail.password=your-app-password 
spring.mail.properties.mail.smtp.auth=true 
spring.mail.properties.mail.smtp.starttls.enable=true 
⚠️ Security Tip: Do not store sensitive credentials in application.properties. Use environment variables instead.
```
@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendWelcomeEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to Task Manager!");
        message.setText("Hello, welcome to our Task Management System. Enjoy managing your tasks efficiently!");

        javaMailSender.send(message);
    }
}


## How to Run
1. Clone this repository.
2. Set up **PostgreSQL** database.
3. Configure application properties with database credentials.
4. Run the application with the `spring-boot:run` Maven command.

## FEATURES
 - **USER REGISTRATION AND LOGIN**
     Users can register and log in to the system securely.
     Authentication is handled using JWT (JSON Web Token) for secure session management.
 - **TASK MANAGEMENT**
  View all tasks with ease.
    Access detailed information about specific tasks.
    Select and manage tasks assigned to users.
    Task Status Updates: Tasks can be updated with statuses such as In Progress, Completed, etc.
    Task Priority Levels: Tasks are categorized as Low, Medium, or High.
    Start and End Dates: Tasks include start and end dates for better time tracking.
 - **USER MANAGEMENT**
    View all users in the system.
    Assign roles to users (e.g., admin, user).
    Role-Based Permissions: Only admins can assign or update roles.
 - **PROJECT MANAGEMENT**
    View all projects in the system.
    Access detailed information about specific projects.
 - **SECURITY AND AUTHORIZATION**
    Role-Based Access Control: Ensures restricted access to certain features depending on user roles.
    Secure Authentication: Token-based authentication implemented using JWT.

---
