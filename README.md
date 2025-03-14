# Security JWT app

---

## 🧩 Project Description
This app was made solely to figure out how to work with JSON Web Token and how to separate all requests/accounts by roles.

---

## 🧰 Tech stack used in this project
- **Java 21**
- **Spring Boot**
- **Hibernate**
- **H2** (Database just for example)
- **Lombok** (for reducing boilerplate code)
- **Maven** (Build tool) 

## 📂 Project Structure

```

├───src/main/java
│   └───com.moldavets.security_jwt 
│        ├───config # Configuration files (security, JWT)
│        ├───controller # REST controllers handling HTTP requests
│        ├───dto # Data Transfer Objects (DTOs)
│        ├───entity # Database entities (Entity classes)
│        ├───exception # Error handling and exception classes
│        ├───repository # Repositories (Spring Data JPA)
│        ├───service # Service layer (business logic)
│        └───util # Utility classes and helper methods
└───src/main/resources
    ├───db
    └───migration # Database migration files (Flyway/Liquibase)
```