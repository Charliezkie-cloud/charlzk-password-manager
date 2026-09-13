package org.charlzk.DAO;

import org.charlzk.Database.DatabaseConnection;
import org.charlzk.Models.User;

import java.io.IOException;
import java.sql.*;

public class UserDAO {
  public boolean createUser(String username, String email, String passwordHash) throws SQLException, IOException {
    String sql = "INSERT INTO Users (username, email, password_hash, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, username);
      statement.setString(2, email);
      statement.setString(3, passwordHash);
      statement.setLong(4, System.currentTimeMillis());
      statement.setLong(5, System.currentTimeMillis());

      return statement.executeUpdate() > 0;
    }
  }

  public User getUserById(int userId) throws SQLException, IOException {
    String sql = "SELECT * FROM Users WHERE user_id = ? LIMIT 1";

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setInt(1, userId);
      try (ResultSet rs = statement.executeQuery()) {
        if (!rs.next()) return null;

        int userIdCol = rs.getInt("user_id");

        String username = rs.getString("username");
        String emailRow = rs.getString("email");
        String passwordHash = rs.getString("password_hash");

        long createdAt = rs.getLong("created_at");
        long updatedAt = rs.getLong("updated_at");

        return new User(userIdCol, username, emailRow, passwordHash, createdAt, updatedAt);
      }
    }
  }

  public User getUserByEmail(String email) throws SQLException, IOException {
    String sql = "SELECT * FROM Users WHERE email = ? LIMIT 1";

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, email);
      try (ResultSet rs = statement.executeQuery()) {
        if (!rs.next()) return null;

        int userId = rs.getInt("user_id");

        String username = rs.getString("username");
        String emailCol = rs.getString("email");
        String passwordHash = rs.getString("password_hash");

        long createdAt = rs.getLong("created_at");
        long updatedAt = rs.getLong("updated_at");

        return new User(userId, username, emailCol, passwordHash, createdAt, updatedAt);
      }
    }
  }

  public User updateUser(int userId, String email, String passwordHash) throws SQLException, IOException {
    String sql = "UPDATE Users SET email = ?, password_hash = ?, updated_at = ? WHERE user_id = ?"; // fixed: was "passwordHash"

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, email);
      statement.setString(2, passwordHash);
      statement.setLong(3, System.currentTimeMillis());
      statement.setInt(4, userId);

      if (!(statement.executeUpdate() > 0)) return null;
    }

    return getUserById(userId);
  }

  public User deleteUser(int userId) throws SQLException, IOException {
    User deletedUser = getUserById(userId);
    if (deletedUser == null) return null;

    String sql = "DELETE FROM Users WHERE user_id = ?";

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setInt(1, userId);

      if (!(statement.executeUpdate() > 0)) return null;
    }

    return deletedUser;
  }
}