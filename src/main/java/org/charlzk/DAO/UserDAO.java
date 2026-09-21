package org.charlzk.DAO;

import org.charlzk.Database.DatabaseConnection;
import org.charlzk.Models.User;

import java.io.IOException;
import java.sql.*;

public class UserDAO {
  public boolean createUser(String username, String email, String passwordHash) throws SQLException, IOException {
    String sql = "INSERT INTO Users (username, email, password_hash, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement statement = conn.prepareStatement(sql)) {
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

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setInt(1, userId);
      try (ResultSet rs = statement.executeQuery()) {
        if (!rs.next()) return null;

        int userIdCol = rs.getInt("user_id");

        String username = rs.getString("username");
        String emailRow = rs.getString("email");
        String passwordHash = rs.getString("password_hash");

        long createdAt = rs.getLong("created_at");
        long updatedAt = rs.getLong("updated_at");

        return new User(userIdCol, emailRow, username, passwordHash, createdAt, updatedAt);
      }
    }
  }

  public User getUserByEmail(String email) throws SQLException, IOException {
    String sql = "SELECT * FROM Users WHERE email = ? LIMIT 1";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, email);
      try (ResultSet rs = statement.executeQuery()) {
        if (!rs.next()) return null;

        int userId = rs.getInt("user_id");

        String username = rs.getString("username");
        String emailCol = rs.getString("email");
        String passwordHash = rs.getString("password_hash");

        long createdAt = rs.getLong("created_at");
        long updatedAt = rs.getLong("updated_at");

        return new User(userId, emailCol, username, passwordHash, createdAt, updatedAt);
      }
    }
  }

  public User updateUserPassword(int userId, String passwordHash) throws SQLException, IOException {
    String sql = "UPDATE Users SET password_hash = ?, updated_at = ? WHERE user_id = ?"; // fixed: was "passwordHash"

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, passwordHash);
      statement.setLong(2, System.currentTimeMillis());
      statement.setInt(3, userId);

      if (!(statement.executeUpdate() > 0)) return null;
    }

    return getUserById(userId);
  }

  public User updateUserEmail(int userId, String email) throws SQLException, IOException {
    String sql = "UPDATE Users SET email = ?, updated_at = ? WHERE user_id = ?"; // fixed: was "passwordHash"

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, email);
      statement.setLong(2, System.currentTimeMillis());
      statement.setInt(3, userId);

      if (!(statement.executeUpdate() > 0)) return null;
    }

    return getUserById(userId);
  }

  public User updateUserUsername(int userId, String username) throws SQLException, IOException {
    String sql = "UPDATE Users SET username = ?, updated_at = ? WHERE user_id = ?"; // fixed: was "passwordHash"

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, username);
      statement.setLong(2, System.currentTimeMillis());
      statement.setInt(3, userId);

      if (!(statement.executeUpdate() > 0)) return null;
    }

    return getUserById(userId);
  }

  public User deleteUser(int userId) throws SQLException, IOException {
    User deletedUser = getUserById(userId);
    if (deletedUser == null) return null;

    String sql = "DELETE FROM Users WHERE user_id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setInt(1, userId);

      if (!(statement.executeUpdate() > 0)) return null;
    }

    return deletedUser;
  }
}
