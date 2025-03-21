# ClothifyStore - POS System for Fashion Shops

## Overview

This is a Point of Sale (POS) application designed specifically for fashion shops. Built using JavaFX, it provides an intuitive graphical user interface (GUI) that simplifies sales transactions, inventory management, and order tracking. The system follows the Layered Architecture, ensuring a clean separation of concerns and maintainability.

## Enhancements & Design Patterns
* **Layered Architecture** - increased scalability, maintainability and modularity
* **Factory Design Pattern** - de-coupling of layers
* **Strategy Design Pattern** - flexible business logic implementation
* **Dependency Injection** - using Google Guice library
* **Hibernate** - ORM tool for database management
* **Jasper Reports** - generating reports

## Features

User-Friendly JavaFX GUI for efficient sales and inventory management.

Maven-Based Build System for easy dependency management.

Hibernate ORM for database interactions instead of native queries.

Singleton Design Pattern for managing database connections efficiently.

Secure Data Handling using Jasypt for encryption.

Integrated with MySQL for robust data storage.

JasperReports for generating sales and inventory reports.

Apache POI for handling Excel-based data operations.

Technologies Used

JavaFX (GUI framework)

Maven (build tool)

MySQL (database management)

Hibernate (ORM framework)

Lombok (to reduce boilerplate code)

Jasypt (for data encryption)

JFoenix (for modern UI components)

Google Guice (for dependency injection)

ModelMapper (for object mapping)

JasperReports (for generating reports)

Apache POI (for Excel file handling)

Log4j (for logging and debugging)

Dependencies

The project uses the following dependencies in pom.xml:

<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>19.0.2</version>
</dependency>

<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-fxml</artifactId>
    <version>19.0.2</version>
</dependency>

<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.36</version>
    <scope>provided</scope>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>9.1.0</version>
</dependency>

<dependency>
    <groupId>com.jfoenix</groupId>
    <artifactId>jfoenix</artifactId>
    <version>9.0.1</version>
</dependency>

<dependency>
    <groupId>org.jasypt</groupId>
    <artifactId>jasypt</artifactId>
    <version>1.9.3</version>
</dependency>

<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>3.2.2</version>
</dependency>

<dependency>
    <groupId>com.google.inject</groupId>
    <artifactId>guice</artifactId>
    <version>7.0.0</version>
</dependency>

<dependency>
    <groupId>org.hibernate.orm</groupId>
    <artifactId>hibernate-core</artifactId>
    <version>6.6.4.Final</version>
</dependency>

<dependency>
    <groupId>net.sf.jasperreports</groupId>
    <artifactId>jasperreports</artifactId>
    <version>7.0.1</version>
</dependency>

<dependency>
    <groupId>net.sf.jasperreports</groupId>
    <artifactId>jasperreports-fonts</artifactId>
    <version>7.0.1</version>
</dependency>

<dependency>
    <groupId>net.sf.jasperreports</groupId>
    <artifactId>jasperreports-pdf</artifactId>
    <version>7.0.1</version>
</dependency>

<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.4.0</version>
</dependency>

<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.24.3</version>
</dependency>

Project Structure

ClothifyStore/
│── src/main/java/
│   ├── model/           # Business logic & data models
│   ├── view/            # JavaFX UI components
│   ├── controller/      # Application controllers
│   ├── service/         # Business logic services
│   ├── repository/      # Hibernate database operations
│   ├── factory/         # Factory pattern implementation
│   ├── strategy/        # Strategy pattern implementation
│   ├── database/        # Singleton DB connection class
│── src/main/resources/  # FXML files, styles, assets
│── pom.xml              # Maven dependencies
│── README.md            # Project documentation

Getting Started

Prerequisites

JDK 17+ (Ensure JavaFX support)

Apache Maven (For dependency management)

MySQL Server (Database backend)

Installation

Clone the repository:

git clone https://github.com/yourusername/clothifystore.git
cd clothifystore

Build the project:

mvn clean install

Run the application:

mvn javafx:run

Contribution

Contributions are welcome! Feel free to fork the project and submit a pull request.

License

This project is licensed under the MIT License.

This project is an excellent opportunity to demonstrate skills in JavaFX, Maven, Hibernate, Design Patterns, Dependency Injection, and JasperReports, making it a great portfolio addition!
