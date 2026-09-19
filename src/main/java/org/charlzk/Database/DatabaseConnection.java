package org.charlzk.Database;

import org.charlzk.Services.ApplicationServices;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
  private static final String DATABASE_URL = "database.db";

  public static Connection getConnection() throws SQLException, IOException {
    Path appDataDir = ApplicationServices.ensureAppDataDir();
    Path dbFile = appDataDir.resolve(DATABASE_URL);
    Connection connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.toString());

    try (Statement statement = connection.createStatement()) {
      statement.execute("PRAGMA foreign_keys = ON;");
    }

    return connection;
  }
}
