package org.charlzk.DAO;

import org.charlzk.Database.DatabaseConnection;
import org.charlzk.Models.Folder;
import org.charlzk.Models.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FolderDAO {
  public boolean createFolder(int userId, String name) throws SQLException, IOException {
    String sql = "INSERT INTO Folders (user_id, name, created_at) VALUES (?, ?, ?)";

    Connection conn = DatabaseConnection.getConnection();
    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setInt(1, userId);
    statement.setString(2, name);
    statement.setLong(3, System.currentTimeMillis());

    return statement.executeUpdate() > 0;
  }

  public Folder getFolderByUserId(int userId, int folderId) throws SQLException, IOException {
    String sql = """
        SELECT
            -- Users
            u.user_id,
            u.email,
            u.username,
            u.password_hash,
            u.created_at AS users_created_at,
            u.updated_at AS users_updated_at,
            
            -- Folders
            f.folder_id,
            f.name,
            f.created_at AS folders_created_at
        FROM Folders f
        INNER JOIN Users u ON u.user_id = f.user_id
        WHERE f.user_id = ? AND f.folder_id = ?
        LIMIT 1;
    """;

    Connection conn = DatabaseConnection.getConnection();
    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setInt(1, userId);
    statement.setInt(2, folderId);

    ResultSet rs = statement.executeQuery();

    if (rs.next()) {
      int userIdRow = rs.getInt("user_id");
      String email = rs.getString("email");
      String username = rs.getString("username");
      String passwordHash = rs.getString("password_hash");
      long createdAt = rs.getLong("users_created_at");
      long updatedAt = rs.getLong("users_updated_at");

      User user = new User(userIdRow, email, username, passwordHash, createdAt, updatedAt);

      int folderIdRow = rs.getInt("folder_id");
      String name = rs.getString("name");
      long folderCreatedAt = rs.getLong("folders_created_at");

      return new Folder(folderIdRow, userId, name, folderCreatedAt, user);
    } else {
      return null;
    }
  }

  public Folder updateFolder(int userId, int folderId, String name) throws SQLException, IOException {
    String sql = "UPDATE FROM Folders SET name = ? WHERE user_id = ? AND folder_id = ?;";

    Connection conn = DatabaseConnection.getConnection();
    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setInt(1, userId);
    statement.setInt(2, folderId);

    statement.executeQuery();

    return getFolderByUserId(userId, folderId);
  }

  public Folder deleteFolder(int userId, int folderId) throws SQLException, IOException {
    String sql = "DELETE FROM Folders WHERE user_id = ? AND folder_id = ?;";

    Connection conn = DatabaseConnection.getConnection();
    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setInt(1, userId);
    statement.setInt(2, folderId);

    statement.executeQuery();

    return getFolderByUserId(userId, folderId);
  }

  public ArrayList<Folder> getAllUserFolders(int userId) throws SQLException, IOException {
    ArrayList<Folder> folders = new ArrayList<>();
    String sql = """
        SELECT
            -- Users
            u.user_id,
            u.email,
            u.username,
            u.password_hash,
            u.created_at AS users_created_at,
            u.updated_at AS users_updated_at,
            
            -- Folders
            f.folder_id,
            f.name,
            f.created_at AS folders_created_at
        FROM Folders f
        INNER JOIN Users u ON u.user_id = f.user_id
        WHERE f.user_id = ?
        LIMIT 1;
    """;

    Connection conn = DatabaseConnection.getConnection();
    PreparedStatement statement = conn.prepareStatement(sql);

    statement.setInt(1, userId);

    ResultSet rs = statement.executeQuery();

    if (rs.next()) {
      int userIdRow = rs.getInt("user_id");
      String email = rs.getString("email");
      String username = rs.getString("username");
      String passwordHash = rs.getString("password_hash");
      long createdAt = rs.getLong("users_created_at");
      long updatedAt = rs.getLong("users_updated_at");

      User user = new User(userIdRow, email, username, passwordHash, createdAt, updatedAt);

      int folderIdRow = rs.getInt("folder_id");
      String name = rs.getString("name");
      long folderCreatedAt = rs.getLong("folders_created_at");

      folders.add(new Folder(folderIdRow, userId, name, folderCreatedAt, user));

      return folders;
    } else {
      return folders;
    }
  }
}
