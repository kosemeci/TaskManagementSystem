Task Management System (Task Manager) is a robust and user-friendly application designed to manage tasks efficiently. With features like task creation, updating, marking as completed, and deletion, this system ensures seamless task tracking. Developed using Spring Boot, the system incorporates modern software engineering practices for optimal performance and scalability.

Key Features
Database Integration:
Built with PostgreSQL, ensuring secure and efficient data storage.

Validation and Exception Handling:
Implements comprehensive validation rules and exception handling to maintain data integrity and provide detailed error messages.

Bidirectional Relationships:
Supports One-to-Many relationships between entities (e.g., Users and Tasks), ensuring efficient data mapping and navigation.

DTOs for Clean Architecture:
Data Transfer Objects (DTOs) are used to decouple the domain model from API responses, ensuring clear and efficient communication.

Error Logging and Messages:
Handles errors gracefully by logging exceptions and returning meaningful error messages to the client.

Technical Details
Backend Framework: Spring Boot
Database: PostgreSQL
Validation: Powered by Spring Boot's validation annotations (@NotNull, @Size, etc.)
Error Handling: Custom exception classes and global exception handlers using @ControllerAdvice.
Data Mapping: DTOs for API requests and responses.
Relationships: One-to-Many bidirectional relationships between entities, managed with @OneToMany and @ManyToOne.
