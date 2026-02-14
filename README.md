Multi-Service Banking System (Java 8 & Spring Boot)
This project demonstrates a microservices architecture using Spring Boot and a shared H2 Database. It consists of two independent services that coordinate to manage customer bank accounts.

System Architecture
The system follows a Shared Database Pattern with a dedicated host:

- Account Creation Service (Port 8081): The "Host" service. It initializes the H2 TCP Server and handles all "Write" operations (Creating customers and accounts).

- Customer Inquiry Service (Port 8082): The "Client" service. It connects to the established TCP Server to provide read-only access to customer data.

Tech Stack
Java: 1.8 (JDK 8)

Framework: Spring Boot 2.6.15

Database: H2 (File-based with TCP Server mode)

Testing: JUnit 4 & Mockito

Build Tool: Maven

Getting Started
1. Installation
Clone the repository to your local machine:

- git clone https://github.com/your-username/banking-microservices-system.git
- cd banking-microservices

2. Running the Services (Important Order!)
Because the Account Creation Service hosts the database server, it must be started first.

Start Account Creation Service:

- Navigate to /account-creation-service

- Run mvn spring-boot:run

- Wait for the log: H2 TCP server started on port 9092

Start Customer Inquiry Service:

- Navigate to /customer-inquiry-service

- Run mvn spring-boot:run

API Documentation
- A. Account Creation (Port 8081)
Endpoint: POST /api/v1/account

Sample Payload:

JSON
{
    "customerName": "John Doe",
    "customerMobile": "09123456789",
    "customerEmail": "john.doe@gmail.com",
    "address1": "123 Spring St",
    "address2": "Suite 4",
    "accountType": "S"
}

Logic: If the email exists, it adds a new account to the existing customer. If not, it creates a new customer profile.

Validation: Supports 'S' (Savings) and 'C' (Checking). Invalid types return a 400 Error.

- B. Customer Inquiry (Port 8082)
Endpoint: GET /api/v1/customer/{customerNumber}

- Success Response (302 Found):

JSON
{
    "customerNumber": 1,
    "customerName": "John Doe",
    "accounts": [
        { "accountNumber": 1, "accountType": "Savings", "availableBalance": 0.0 }
    ],
    "transactionStatusCode": 302,
    "transactionStatusDescription": "Customer Account found"
}

- Failed Response (401)

JSON
{
    "transactionStatusCode": 401,
    "transactionStatusDescription": "Customer not found"
}


Database Management
You can view the data in real-time via the H2 Console:

- URL: http://localhost:8081/h2-console

- JDBC URL: jdbc:h2:tcp://localhost:9092/~/accountdb

- Credentials: sa / password

Testing
Unit tests are implemented using JUnit 4 and Mockito. To run tests for both services:

- mvn test

The tests cover:

- Service layer "Find or Create" logic.

- Validation for account types.

- Error handling for non-existent customers.
