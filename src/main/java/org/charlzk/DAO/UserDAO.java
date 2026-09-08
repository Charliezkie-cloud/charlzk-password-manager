package org.charlzk.DAO;

import org.charlzk.Database.DatabaseConnection;
import org.charlzk.Models.User;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
  public boolean createUser(String username, String email, String passwordHash) {
    String sql = "INSERT INTO Users (username, email, password_hash, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection()) {
      PreparedStatement statement = conn.prepareStatement(sql);

      statement.setString(1, username);
      statement.setString(2, email);
      statement.setString(3, passwordHash);
      statement.setLong(4, System.currentTimeMillis());
      statement.setLong(5, System.currentTimeMillis());

      return statement.executeUpdate() > 0;
    } catch (SQLException ex) {
      System.out.println("UserDAO.createUser Error: " + ex.getMessage());
      return false;
    }
  }

  public User getUserById(int userId) {
    String sql = "SELECT * FROM Users WHERE user_id = ? LIMIT 1";

    try (Connection conn = DatabaseConnection.getConnection()) {
      PreparedStatement statement = conn.prepareStatement(sql);
      statement.setInt(1, userId);
      ResultSet rs = statement.executeQuery();

      if (rs.next()) {
        int userIdCol = rs.getInt("user_id");
        String username = rs.getString("username");
        String emailRow = rs.getString("email");
        String passwordHash = rs.getString("password_hash");
        long createdAt = rs.getLong("created_at");
        long updatedAt = rs.getLong("updated_at");

        return new User(
                userIdCol,
                username,
                emailRow,
                passwordHash,
                createdAt,
                updatedAt
        );
      } else {
        return null;
      }
    } catch (SQLException ex) {
      System.out.println("UserDAO.getUserById Error: " + ex.getMessage());
      return null;
    }
  }

  public User getUserByEmail(String email) {
    String sql = "SELECT * FROM Users WHERE email = ? LIMIT 1";

    try (Connection conn = DatabaseConnection.getConnection()) {
      PreparedStatement statement = conn.prepareStatement(sql);
      statement.setString(1, email);
      ResultSet rs = statement.executeQuery();

      if (rs.next()) {
        int userId = rs.getInt("user_id");
        String username = rs.getString("username");
        String emailCol = rs.getString("email");
        String passwordHash = rs.getString("password_hash");
        long createdAt = rs.getLong("created_at");
        long updatedAt = rs.getLong("updated_at");

        return new User(
                userId,
                username,
                emailCol,
                passwordHash,
                createdAt,
                updatedAt
        );
      } else {
        return null;
      }
    } catch (SQLException ex) {
      System.out.println("UserDAO.getUserByEmail Error: " + ex.getMessage());
      return null;
    }
  }

  public User updateUser(int userId, String email, String passwordHash) {
    String sql = "UPDATE Users SET email = ?, passwordHash = ?, updated_at = ? WHERE user_id = ?";
    try (Connection conn = DatabaseConnection.getConnection()) {
      PreparedStatement statement = conn.prepareStatement(sql);

      statement.setString(1, email);
      statement.setString(2, passwordHash);
      statement.setLong(3, System.currentTimeMillis());
      statement.setInt(4, userId);

      boolean result = statement.executeUpdate() > 0;
      if (result)
        return getUserById(userId);
      else
        return null;
    } catch (SQLException ex) {
      System.out.println("UserDAO.updateUser Error: " + ex.getMessage());
      return null;
    }
  }

  public User deleteUser(int userId) {
    String sql = "DELETE FROM Users WHERE user_id = ?";

    try (Connection conn = DatabaseConnection.getConnection()) {
      PreparedStatement statement = conn.prepareStatement(sql);
      statement.setInt(1, userId);

      boolean result = statement.executeUpdate() > 0;
      if (result)
        return getUserById(userId);
      else
        return null;
    } catch (SQLException ex) {
      System.out.println("UserDAO.deleteUser Error: " + ex.getMessage());
      return null;
    }
  }

  public ArrayList<User> getAllUsers() {
    ArrayList<User> users = new ArrayList<>();
    String sql = "SELECT * FROM Users;";

    try (Connection conn = DatabaseConnection.getConnection()) {
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery(sql);

      while (rs.next()) {
        int userIdCol = rs.getInt("user_id");
        users.add(getUserById(userIdCol));
      }

      return users;
    } catch (SQLException ex) {
      System.out.println("UserDAO.getAllUsers Error: " + ex.getMessage());
      return new ArrayList<>();
    }
  }
}
