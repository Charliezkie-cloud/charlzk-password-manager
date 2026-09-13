package org.charlzk.DAO;

import org.charlzk.Database.DatabaseConnection;
import org.charlzk.Models.Folder;
import org.charlzk.Models.User;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class FolderDAO {
  // Default folder ID
  public final int DEFAULT_FOLDER_ID = 1;

  public void createFolderIfNotExists(int userId, String name) throws SQLException, IOException {
    String sql = "INSERT OR IGNORE INTO Folders (user_id, name, created_at) VALUES (?, ?, ?)";

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setInt(1, userId);
      statement.setString(2, name);
      statement.setLong(3, System.currentTimeMillis());

      statement.executeUpdate();
    }
  }

  public Folder createFolder(int userId, String name) throws SQLException, IOException {
    String sql = "INSERT INTO Folders (user_id, name, created_at) VALUES (?, ?, ?)";

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      statement.setInt(1, userId);
      statement.setString(2, name);
      statement.setLong(3, System.currentTimeMillis());
      if (!(statement.executeUpdate() > 0)) return null;

      try (ResultSet rs = statement.getGeneratedKeys()) {
        if (!rs.next()) return null;

        int folderId = rs.getInt(1);
        return getFolderById(folderId);
      }
    }
  }

  public Folder getFolderById(int folderId) throws SQLException, IOException {
    String sql = """
        SELECT
            -- Users (owner info only)
            u.user_id,
            u.email,
            u.username,
            
            -- Folders
            f.folder_id,
            f.name,
            f.created_at AS folders_created_at
        FROM Folders f
        INNER JOIN Users u ON u.user_id = f.user_id
        WHERE f.folder_id = ?
        LIMIT 1;
    """;

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setInt(1, folderId);

      try (ResultSet rs = statement.executeQuery()) {
        if (rs.next()) {
          int userIdRow = rs.getInt("user_id");
          String email = rs.getString("email");
          String username = rs.getString("username");

          User user = new User(userIdRow, email, username, null);

          int folderIdRow = rs.getInt("folder_id");
          String name = rs.getString("name");
          long folderCreatedAt = rs.getLong("folders_created_at");

          return new Folder(folderIdRow, userIdRow, name, folderCreatedAt, user);
        } else {
          return null;
        }
      }
    }
  }

  public Folder updateFolder(int folderId, String name) throws SQLException, IOException {
    String sql = "UPDATE Folders SET name = ? WHERE folder_id = ?;";

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, name);
      statement.setInt(2, folderId);

      statement.executeUpdate();
    }

    return getFolderById(folderId);
  }

  public Folder deleteFolder(int folderId) throws SQLException, IOException {
    String sql = "DELETE FROM Folders WHERE folder_id = ?;";

    Connection conn = DatabaseConnection.getConnection();
    Folder folder = getFolderById(folderId);

    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setInt(1, folderId);
      statement.executeUpdate();
    }

    return folder;
  }

  public HashMap<Integer, Folder> getAllUserFolders(int userId) throws SQLException, IOException {
    HashMap<Integer, Folder> folders = new HashMap<>();
    String sql = """
        SELECT
            -- Users (owner info only)
            u.user_id,
            u.email,
            u.username,
            
            -- Folders
            f.folder_id,
            f.name,
            f.created_at AS folders_created_at
        FROM Folders f
        INNER JOIN Users u ON u.user_id = f.user_id
        WHERE f.user_id = ?;
    """;

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setInt(1, userId);

      try (ResultSet rs = statement.executeQuery()) {
        while (rs.next()) {
          int userIdRow = rs.getInt("user_id");
          String email = rs.getString("email");
          String username = rs.getString("username");

          User user = new User(userIdRow, email, username, null);

          int folderIdRow = rs.getInt("folder_id");
          String name = rs.getString("name");
          long folderCreatedAt = rs.getLong("folders_created_at");

          folders.put(folderIdRow, new Folder(folderIdRow, userId, name, folderCreatedAt, user));
        }
      }
    }

    return folders;
  }
}