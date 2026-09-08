package org.charlzk.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
  public static void initialize() throws SQLException, IOException {
    try (Connection conn = DatabaseConnection.getConnection()) {
      if (isDatabaseInitialized()) return;

      Statement statement = conn.createStatement();

      // USERS TABLE
      statement.execute("""
        CREATE TABLE IF NOT EXISTS Users (
          user_id INTEGER PRIMARY KEY AUTOINCREMENT,
          email TEXT NOT NULL UNIQUE,
          username TEXT NOT NULL,
          password_hash TEXT NOT NULL,
          created_at INTEGER NOT NULL,
          updated_at INTEGER
        );
      """);

      // FOLDERS TABLE
      statement.execute("""
        CREATE TABLE IF NOT EXISTS Folders (
          folder_id INTEGER PRIMARY KEY AUTOINCREMENT,
          user_id INTEGER NOT NULL,
          name TEXT NOT NULL,
          created_at INTEGER NOT NULL,
          FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
        );
      """);

      // PASSWORDS TABLE
      statement.execute("""
        CREATE TABLE IF NOT EXISTS PasswordEntries (
          entry_id INTEGER PRIMARY KEY AUTOINCREMENT,
          folder_id INTEGER,
          user_id INTEGER NOT NULL,

          title TEXT NOT NULL,
          username TEXT,
          password TEXT NOT NULL,
          url TEXT,

          created_at INTEGER NOT NULL,
          updated_at INTEGER,

          FOREIGN KEY (folder_id) REFERENCES Folders(folder_id) ON DELETE SET NULL,
          FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
        );
      """);
    }
  }

  private static boolean isDatabaseInitialized() throws SQLException, IOException {
    String sql = "SELECT name FROM sqlite_master WHERE type = 'table' AND name = 'Users';";
    Connection conn = DatabaseConnection.getConnection();
    return conn.createStatement().executeQuery(sql).next();
  }
}
