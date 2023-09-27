# evelynwambui-ims-app

# Inventory Management System

## Project Description

This repository showcases the development of a robust and secure web-based Inventory Management System, designed to manage inventory for an e-commerce platform. The project encompasses various critical areas of software development, including database management, transaction handling, message queuing, data structures, CRUD operations, security, authentication, and authorization using JWT, and message brokering.

## Project Scope

The Inventory Management System is built using either Spring Boot (Java) or Django (Python) and includes the following key features:

1. **Database Management:**
   - Design and creation of a relational database schema for product information, user data, and transaction history.
   - Implementation of proper indexing and optimization of database queries for efficient data retrieval.
   - Option to use MySQL or PostgreSQL for the database, with justifications for alternative relational databases.

2. **Transaction Handling:**
   - Development of transactional logic to ensure data consistency during various inventory-related operations.

3. **Message Queueing:**
   - Setup of Apache Kafka and creation of topics for managing asynchronous messages.
   - Real-time inventory tracking of all products using Kafka.
   - Logging of all API calls to maintain an audit trail.

4. **CRUD Operations:**
   - Creation of APIs for CRUD (Create, Read, Update, Delete) operations for managing products and users.
   - Implementation of validation and error handling for all APIs.
   - APIs for searching and filtering products based on various attributes.
   - Soft deletes for delete operations, marking data as deleted but not removing it from the database.
   - Bulk-upload capability allowing users to upload a CSV file containing product data asynchronously using Kafka.

5. **Security:**
   - Implementation of security best practices to protect sensitive data.
   - Encryption for sensitive data at rest and in transit, such as user password encryption using a chosen encryption algorithm.

6. **Authentication and Authorization:**
   - Implementation of JWT-based authentication and authorization to secure API endpoints.
   - Differentiation between super-admin and regular user roles with varying access levels using claims.
   - Provision of endpoints accessible only to super-admin for blacklisting users and viewing the audit trail.

## Other Components

1. **Dockerization:** Containerization of the application using Docker and Docker Compose for running various service containers.

2. **Unit and Integration Tests:** Implementation of appropriate unit and integration tests for the API, along with testing coverage reporting.

3. **Documentation:** Provision of a valid OpenAPI spec version 3.0.3 document.

<br><br>

# Project Design
 - Framework - Java Springboot
 - Security - Spring 6.0 with jwt and user authentication
 - Multi-user user management and roles.
 - Unit Testing - Junit Platform and Mockito
 - Logging - Sl4j and Logger with custom configuration
 - Database - MySQL
 - Documentation - OpenApi Swagger and Javadoc

