package org.charlzk.DAO;

import org.charlzk.Database.DatabaseConnection;
import org.charlzk.Models.Folder;
import org.charlzk.Models.PasswordEntry;
import org.charlzk.Models.User;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class PasswordEntryDAO {
  // DAO's
  private final FolderDAO folderDAO = new FolderDAO();
  private final UserDAO userDAO = new UserDAO();

  public PasswordEntry createPasswordEntry(int userId, String title, String username, String password, String url) throws SQLException, IOException {
    String sql = """
            INSERT INTO PasswordEntries(folder_id, user_id, title, username, password, url, created_at, updated_at)
            VALUES(?, ?, ?, ?, ?, ?, ?, ?);
            """;

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      statement.setInt(1, folderDAO.DEFAULT_FOLDER_ID);
      statement.setInt(2, userId);

      statement.setString(3, title);
      statement.setString(4, username);
      statement.setString(5, password);
      statement.setString(6, url);

      statement.setLong(7, System.currentTimeMillis());
      statement.setLong(8, System.currentTimeMillis());

      if (!(statement.executeUpdate() > 0)) return null;

      try (ResultSet rs = statement.getGeneratedKeys()) {
        if (!rs.next()) return null;

        int passwordEntryId = rs.getInt(1);
        return getPasswordEntryById(passwordEntryId);
      }
    }
  }

  public PasswordEntry createPasswordEntry(int userId, int folderId, String title, String username, String password, String url) throws SQLException, IOException {
    String sql = """
            INSERT INTO PasswordEntries(folder_id, user_id, title, username, password, url, created_at, updated_at)
            VALUES(?, ?, ?, ?, ?, ?, ?, ?);
            """;

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      statement.setInt(1, folderId);
      statement.setInt(2, userId);

      statement.setString(3, title);
      statement.setString(4, username);
      statement.setString(5, password);
      statement.setString(6, url);

      statement.setLong(7, System.currentTimeMillis());
      statement.setLong(8, System.currentTimeMillis());

      if (!(statement.executeUpdate() > 0)) return null;

      try (ResultSet rs = statement.getGeneratedKeys()) {
        if (!rs.next()) return null;

        int passwordEntryId = rs.getInt(1);
        return getPasswordEntryById(passwordEntryId);
      }
    }
  }

  public PasswordEntry getPasswordEntryById(int passwordEntryId) throws SQLException, IOException {
    String sql = "SELECT * FROM PasswordEntries WHERE entry_id = ? LIMIT 1";

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setInt(1, passwordEntryId);
      ResultSet rs = statement.executeQuery();

      if (rs.next()) {
        int entryId = rs.getInt("entry_id");
        int folderId = rs.getInt("folder_id");
        int userIdRow = rs.getInt("user_id");

        String title = rs.getString("title");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String url = rs.getString("url");
        String note = rs.getString("note");

        long createdAt = rs.getLong("created_at");
        long updatedAt = rs.getLong("updated_at");

        Folder folder = folderDAO.getFolderById(folderId);
        User user = userDAO.getUserById(userIdRow);

        return new PasswordEntry(entryId, folderId, userIdRow, title, username, password, url, note, createdAt, updatedAt, folder, user);
      } else {
        return null;
      }
    }
  }

  public PasswordEntry updatePasswordEntry(int passwordEntryId, String title, String username, String password, String url) throws SQLException, IOException {
    String sql = """
            UPDATE PasswordEntries
            SET
              title = ?,
              username = ?,
              password = ?,
              url = ?,
              updated_at = ?
            WHERE entry_id = ?;
            """;

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setString(1, title);
      statement.setString(2, username);
      statement.setString(3, password);
      statement.setString(4, url);
      statement.setLong(5, System.currentTimeMillis());
      statement.setInt(6, passwordEntryId);

      if (!(statement.executeUpdate() > 0)) return null;
    }

    return getPasswordEntryById(passwordEntryId);
  }

  public PasswordEntry deletePasswordEntry(int passwordEntryId) throws SQLException, IOException {
    PasswordEntry entryToDelete = getPasswordEntryById(passwordEntryId);
    if (entryToDelete == null) return null;

    String sql = "DELETE FROM PasswordEntries WHERE entry_id = ?";

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setInt(1, passwordEntryId);
      statement.executeUpdate();
    }

    return entryToDelete;
  }

  public HashMap<Integer, PasswordEntry> getAllUserPasswordEntries(int userId) throws SQLException, IOException {
    HashMap<Integer, PasswordEntry> userPasswordEntries = new HashMap<>();
    String sql = "SELECT * FROM PasswordEntries WHERE user_id = ?";

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setInt(1, userId);
      ResultSet rs = statement.executeQuery();

      while (rs.next()) {
        int entryId = rs.getInt("entry_id");
        int folderId = rs.getInt("folder_id");
        int userIdRow = rs.getInt("user_id");

        String title = rs.getString("title");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String url = rs.getString("url");
        String note = rs.getString("note");

        long createdAt = rs.getLong("created_at");
        long updatedAt = rs.getLong("updated_at");

        Folder folder = folderDAO.getFolderById(folderId);
        User user = userDAO.getUserById(userIdRow);

        userPasswordEntries.put(entryId, new PasswordEntry(entryId, folderId, userIdRow, title, username, password, url, note, createdAt, updatedAt, folder, user));
      }
    }

    return userPasswordEntries;
  }

  public HashMap<Integer, PasswordEntry> getAllFolderPasswordEntries(int folderId) throws SQLException, IOException {
    HashMap<Integer, PasswordEntry> folderPasswordEntries = new HashMap<>();
    String sql = "SELECT * FROM PasswordEntries WHERE folder_id = ?";

    Connection conn = DatabaseConnection.getConnection();
    try (PreparedStatement statement = conn.prepareStatement(sql)) {
      statement.setInt(1, folderId);
      ResultSet result = statement.executeQuery();

      while (result.next()) {
        int entryId = result.getInt("entry_id");
        int folderIdColumn = result.getInt("folder_id");
        int userId = result.getInt("user_id");

        String title = result.getString("title");
        String username = result.getString("username");
        String password = result.getString("password");
        String url = result.getString("url");
        String note = result.getString("note");

        long createdAt = result.getLong("created_at");
        long updatedAt = result.getLong("updated_at");

        Folder folder = folderDAO.getFolderById(folderIdColumn);
        User user = userDAO.getUserById(userId);

        PasswordEntry passwordEntry = new PasswordEntry(
                entryId,
                folderIdColumn,
                userId,

                title,
                username,
                password,
                url,
                note,

                createdAt,
                updatedAt,

                folder,
                user
        );

        folderPasswordEntries.put(entryId, passwordEntry);
      }
    }

    return folderPasswordEntries;
  }
}