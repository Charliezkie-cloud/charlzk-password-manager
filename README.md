# Password Manager (Java Desktop Application)

A simple offline password manager built with Java. This application stores user credentials locally using SQLite and follows an MVC-based structure for clarity and maintainability.

The project focuses on building a solid foundation for a secure desktop password manager while keeping the system easy to extend.

---

## Overview

This application allows multiple users to manage their credentials on a single device. All data is stored locally, so no internet connection is required.

Security is considered in the design, but the project is still in development and not yet ready for storing highly sensitive data.

---

## Features

* Multi-user support on a single device
* Local database using SQLite
* Passwords stored as hashes, not plain text
* No password recovery by design
* Session is cleared when the application is closed
* Structured using MVC principles

---

## Tech Stack

* Java
* Swing (JFrame)
* SQLite (sqlite-jdbc)
* Maven
* MVC architecture

---

## Project Structure

```text id="k1qygn"
src/
├── Components/    # Reusable UI components
├── Controllers/   # Application logic and flow control
├── DAO/           # Database access operations
├── Database/      # Database connection and initialization
├── Events/        # Event handling and listeners
├── Models/        # Data models
├── Services/      # Business logic layer
├── Views/         # UI screens and layouts
└── Main.java      # Application entry point
```

---

## Setup

1. Clone the repository

```bash id="z6m1a0"
git clone https://github.com/your-username/password-manager.git
cd password-manager
```

2. Build the project

```bash id="ymi6fp"
mvn clean install
```

3. Run the application

```bash id="op7crn"
mvn exec:java
```

---

## Database

The application uses a local SQLite database file that is created on first run.

Planned tables include:

* Users
* Credentials

---

## Contributing

Contributions are welcome. Fork the repository, create a branch, and open a pull request.

---

## License

This project is licensed under the MIT License.

---

## Author

Charles Henry M. Tinoy Jr.
