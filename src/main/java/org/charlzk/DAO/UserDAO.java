package org.charlzk.DAO;

import org.charlzk.Database.DatabaseConnection;

import java.sql.*;

public class UserDAO {
  public boolean createUser(String email, String passwordHash) {
    String sql = "INSERT INTO Users (email, password_hash, created_at)";

    try (Connection conn = DatabaseConnection.getConnection()) {
      PreparedStatement statement = conn.prepareStatement(sql);

      statement.setString(1, email);
      statement.setString(2, passwordHash);
      statement.setLong(3, System.currentTimeMillis());

      return statement.executeUpdate() > 0;
    } catch (SQLException ex) {
      System.out.println("UserDAO.createUser Error: " + ex.getMessage());
      return false;
    }
  }

  public ResultSet getUserById(int userId) {
    String sql = "SELECT * FROM Users WHERE user_id = ?";

    try (Connection conn = DatabaseConnection.getConnection()) {
      PreparedStatement statement = conn.prepareStatement(sql);

      statement.setInt(1, userId);

      return statement.executeQuery();
    } catch (SQLException ex) {
      System.out.println("UserDAO.getUserById Error: " + ex.getMessage());
      return null;
    }
  }

  public ResultSet getUserByEmail(String email) {
    String sql = "SELECT * FROM Users WHERE email = ?";

    try (Connection conn = DatabaseConnection.getConnection()) {
      PreparedStatement statement = conn.prepareStatement(sql);

      statement.setString(1, email);

      return statement.executeQuery();
    } catch (SQLException ex) {
      System.out.println("UserDAO.getUserByEmail Error: " + ex.getMessage());
      return null;
    }
  }

  public boolean updateUser(int userId, String email, String passwordHash) {
    String sql = "UPDATE Users SET email = ?, passwordHash = ?, updated_at = ? WHERE user_id = ?";

    try (Connection conn = DatabaseConnection.getConnection()) {
      PreparedStatement statement = conn.prepareStatement(sql);

      statement.setString(1, email);
      statement.setString(2, passwordHash);
      statement.setLong(3, System.currentTimeMillis());
      statement.setInt(4, userId);

      return statement.executeUpdate() > 0;
    } catch (SQLException ex) {
      System.out.println("UserDAO.updateUser Error: " + ex.getMessage());
      return false;
    }
  }

  public boolean deleteUser(int userId) {
    String sql = "DELETE FROM Users WHERE user_id = ?";

    try (Connection conn = DatabaseConnection.getConnection()) {
      PreparedStatement statement = conn.prepareStatement(sql);

      statement.setInt(1, userId);

      return statement.executeUpdate() > 0;
    } catch (SQLException ex) {
      System.out.println("UserDAO.deleteUser Error: " + ex.getMessage());
      return false;
    }
  }

  public ResultSet getAllUsers() {
    String sql = "SELECT * FROM Users;";

    try (Connection conn = DatabaseConnection.getConnection()) {
      Statement statement = conn.createStatement();

      return statement.executeQuery(sql);
    } catch (SQLException ex) {
      System.out.println("UserDAO.getAllUsers Error: " + ex.getMessage());
      return null;
    }
  }
}
