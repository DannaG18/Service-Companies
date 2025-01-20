# Service Companies Management System

## Overview
The **Service Companies Management System** is a secure, database-driven web application designed to manage company-related data such as branches, services, and associated metadata. Built with Spring Boot, the project features RESTful APIs for seamless interaction, robust security, and a clean architecture for maintainability.

---

## Features
- **Company Management**: Full CRUD (Create, Read, Update, Delete) operations for companies, branches, and services.
- **Authentication & Authorization**: Role-based access control using Spring Security.
- **Database Integration**: Utilizes MySQL with JPA/Hibernate for efficient and scalable data management.
- **Transactional Operations**: Ensures consistency with `@Transactional` annotations.
- **Developer Productivity**: Includes Spring DevTools for live reload during development.

---

## Technologies Used
- **Backend Framework**: Spring Boot (Web, Data JPA, Security)
- **Programming Language**: Java (Java 17)
- **Database**: MySQL
- **Dependency Management**: Maven
- **ORM**: Hibernate (via Spring Data JPA)
- **Utilities**: Lombok (to reduce boilerplate code)

---

## Project Structure
```plaintext
src
├── main
│   ├── java
│   │   └── com.sc.servicecompanies
│   │       ├── application
│   │       │   └── services    # Service layer for business logic
│   │       ├── domain
│   │       │   └── entities    # Entity definitions (e.g., Company, Branch)
│   │       ├── infrastructure
│   │       │   ├── controllers # REST API controllers
│   │       │   ├── repositories # JPA repositories for database access
│   │       └── ServicecompaniesApplication.java # Main entry point
│   └── resources
│       ├── application.properties  # Configuration file
│       └── data.sql  # Sample database initialization script
├── test
    └── java
        └── com.sc.servicecompanies  # Unit and integration tests
```

---

## Prerequisites
1. **Java 17**: Ensure you have JDK 17 installed.
2. **MySQL**: Set up a MySQL instance and create a database for the application.
3. **Maven**: Install Maven for dependency management.

---

## Installation
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd service-companies
   ```
2. Configure the database:
   - Update the `application.properties` file in `src/main/resources` with your MySQL credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/servicecompanies
     spring.datasource.username=<your-username>
     spring.datasource.password=<your-password>
     spring.jpa.hibernate.ddl-auto=update
     ```
3. Build the project:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---

## Usage
### Endpoints
The following endpoints are available in the application:

#### Companies
- `GET /api/companies`: Retrieve all companies.
- `GET /api/companies/{id}`: Retrieve a specific company by ID.
- `POST /api/companies`: Create a new company.
- `PUT /api/companies/{id}`: Update an existing company.
- `DELETE /api/companies/{id}`: Delete a company by ID.

#### Authentication
- `POST /api/auth/login`: Authenticate a user and retrieve a token.
- `POST /api/auth/register`: Register a new user.

#### Other Entities
Similar CRUD endpoints are available for branches, services, and approval statuses.

### Example Request (cURL)
```bash
curl -X POST \
  http://localhost:8080/api/companies \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Example Company",
    "address": "123 Business St",
    "city": "Metropolis",
    "country": "Exampleland"
  }'
```

---

## Testing
1. Run the test suite:
   ```bash
   mvn test
   ```
2. Tests are located under `src/test/java/com/sc/servicecompanies` and include unit and integration tests.

---

## Future Enhancements
- Add support for multi-tenancy.
- Implement advanced reporting and analytics features.
- Integrate with external APIs for real-time data synchronization.

---

## License
This project is licensed under the MIT License. See `LICENSE` for details.

---

## Contributors
- Danna Alvarez - Project Lead
- Jhon Jairo Marin - Additional Roles

Feel free to contribute by submitting pull requests or reporting issues!