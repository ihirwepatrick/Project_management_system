﻿# Project_management_system
Here’s a suggestion for a **stunning README** for your project, which you can use for your GitHub repository. Feel free to modify it further according to your specific requirements.

---

# Project Management System

A robust and scalable **Project Management System** built with **Java** and **PostgreSQL**. This system helps manage projects, their associated resources, students, project managers, and tracks the project's implementation from start to finish. It leverages **JDBC** for database connections and **dotenv** for configuration management.

## Features

- **Project Tracking**: Add, update, and track projects throughout their lifecycle.
- **Project Manager Management**: Assign project managers and track their associated projects.
- **Student Involvement**: Assign students to projects and monitor their contributions.
- **Resource Management**: Keep track of project resources (both human and material).
- **Database Connectivity**: Secure and efficient database management using PostgreSQL and JDBC.
- **Environment Configuration**: Utilize `.env` file for sensitive information like database credentials.

## Technologies Used

- **Backend**: Java
- **Database**: PostgreSQL
- **JDBC**: For database connection
- **Dotenv**: For managing environment variables
- **Kotlin (for dotenv)**: Dependency for environment variable handling
- **Maven/Gradle**: Dependency management and build tool

## Setup and Installation

### Prerequisites

Before you begin, make sure you have the following installed:

- **Java 17+**
- **PostgreSQL**
- **Maven** or **Gradle**
- **IntelliJ IDEA** (or any other IDE of your choice)
- **Dotenv-Java library** (`.env` file handling)

### Clone the Repository

```bash
git clone https://github.com/yourusername/project-management-system.git
cd project-management-system
```

### Setup the Database

1. Create a PostgreSQL database:
   ```bash
   CREATE DATABASE project_tracking;
   ```

2. Import the SQL schema from `schema.sql` into your PostgreSQL database.

3. Modify the `.env` file with your PostgreSQL credentials:
   ```env
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=project_management
   DB_USER=your_username
   DB_PASSWORD=your_password
   ```

### Build and Run

If you’re using Maven:
```bash
mvn clean install
mvn exec:java
```

If you’re using Gradle:
```bash
gradle build
gradle run
```

### Running the Application

The application will start and connect to the PostgreSQL database using the credentials from the `.env` file. You can then interact with the system through the **MainApp.java** class for testing project CRUD operations.

### Access the Project in the Database

You can view and manage the data in the database via **pgAdmin** or any other PostgreSQL client.

## Database Schema

The project involves the following tables:

- **projects**: Tracks project information like name, start date, end date, status, and description.
- **project_managers**: Stores project manager information.
- **students**: Keeps track of students involved in various projects.
- **resources**: Lists all resources required for a project.

### Example of Creating a Project

```java
String sql = "INSERT INTO projects (project_name, start_date, end_date, status, description) VALUES (?, ?, ?, ?, ?)";
PreparedStatement statement = connection.prepareStatement(sql);
statement.setString(1, "Project A");
statement.setString(2, "2024-01-01");
statement.setString(3, "2024-12-31");
statement.setString(4, "In Progress");
statement.setString(5, "Description of Project A");
statement.executeUpdate();
```

## Contributing

Contributions are welcome! If you want to improve this project, feel free to fork the repository and submit pull requests.

### How to Contribute

1. Fork the repository
2. Create a feature branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature-branch`)
5. Create a new pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---


