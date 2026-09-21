# [Releases](https://github.com/Charliezkie-cloud/charlzk-password-manager/releases)

# Charlzk Password Manager

Charlzk Password Manager is a local-only, offline Windows desktop application for managing password entries. It is written in Java and uses a Swing interface with an MVC-oriented structure.

> **Development status:** This project is under active development and is not ready for highly sensitive data. Review the security notes below before using it with real credentials.

## Features

- Local user registration and sign-in
- Folder-based organization of password entries
- Create, update, delete, and search password entries
- Local SQLite database created on first run
- No cloud sync, network service, or password-recovery workflow

## Technology

- Java 25
- Java Swing with FlatLaf
- Maven
- SQLite via `sqlite-jdbc`
- Argon2 for user account password hashing

## Security and data storage

- All application data is stored locally; the application is designed to operate without network activity.
- User account passwords are stored as Argon2 hashes.
- Password-entry fields are currently stored in the local SQLite database.
- The database schema uses foreign keys and separates users, folders, and password entries. See [the database reference](references/DATABASE.md) for the current schema.
- SQLCipher encryption at rest is a **planned** capability. It is described as the intended database design, but it is not implemented in the current database connection code. Do not assume the present database file is encrypted.
- There is intentionally no master-password recovery mechanism.

## Requirements

- Windows
- JDK 25
- Maven 3.9 or later

## Build and run

1. Clone the repository.

   ```bash
   git clone https://github.com/Charliezkie-cloud/Charlzk-password-manager.git
   cd Charlzk-password-manager
   ```

2. Build the project.

   ```bash
   mvn clean package
   ```

3. Open the Maven project in an IDE configured with JDK 25 and run `org.Charlzk.Main`.

The application creates its local database on first run.

## Project structure

```text
src/main/java/org/Charlzk/
├── Components/   # Reusable Swing components and layouts
├── Controllers/  # Application flow and UI coordination
├── DAO/          # SQLite data-access objects
├── Database/     # Connection and schema initialization
├── Events/       # Swing listeners and event handlers
├── Models/       # Data models
├── Services/     # Application, authentication, password, and time services
├── Session/      # Current-session state
├── Views/        # Authenticated and unauthenticated screens
└── Main.java     # Application entry point
```

## Database model

The local database currently contains the following tables:

- `Users` — local account details and password hashes
- `Folders` — user-owned folders for organizing entries
- `PasswordEntries` — credential details associated with a user and, optionally, a folder

Deleting a folder leaves its entries in place as unfiled; deleting a user cascades to that user's folders and password entries. The schema reference is the source of truth: [references/DATABASE.md](references/DATABASE.md).

## Contributing

Contributions are welcome. Please keep changes scoped, preserve the MVC boundaries, and follow the repository guidance in [AGENTS.md](AGENTS.md).

## Legal

- [Privacy Policy](<PRIVACY POLICY.md>)
- [Terms of Use](<TERMS OF USE.md>)
- MIT License

## Author

Charles Henry M. Tinoy Jr.
