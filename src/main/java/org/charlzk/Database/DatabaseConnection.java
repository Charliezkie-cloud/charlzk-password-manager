package org.charlzk.Database;

import org.charlzk.Services.ApplicationService;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static final String DATABASE_URL = "database.db";
  private static Connection connection;

  public static Connection getConnection() throws SQLException, IOException {
    Path appDataDir = ApplicationService.ensureAppDataDir();
    Path dbFile = appDataDir.resolve(DATABASE_URL);

    if (connection == null || connection.isClosed()) {
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.toString());
      connection.createStatement().execute("PRAGMA foreign_keys = ON;");
    }

    return connection;
  }
}
