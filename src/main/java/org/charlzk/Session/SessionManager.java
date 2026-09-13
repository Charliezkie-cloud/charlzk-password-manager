package org.charlzk.Session;

import org.charlzk.DAO.FolderDAO;
import org.charlzk.DAO.PasswordEntryDAO;
import org.charlzk.Models.Folder;
import org.charlzk.Models.PasswordEntry;
import org.charlzk.Models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class SessionManager {
  // DAO's
  private final FolderDAO folderDAO = new FolderDAO();
  private final PasswordEntryDAO passwordEntryDAO = new PasswordEntryDAO();

  // Instance
  private static final SessionManager INSTANCE = new SessionManager();

  // User data
  private User currentUser;
  private boolean isLoggedIn = false;

  // User folders and passwords
  private HashMap<Integer, Folder> userFolders = new HashMap<>();
  private HashMap<Integer, PasswordEntry> userPasswordEntries = new HashMap<>();

  private SessionManager() { }

  public void login(User user) throws SQLException, IOException {
    currentUser = user;
    isLoggedIn = true;

    folderDAO.createFolderIfNotExists(user.getUserId(), "None");
    userFolders = folderDAO.getAllUserFolders(getCurrentUserId());

    userPasswordEntries = passwordEntryDAO.getAllUserPasswordEntries(getCurrentUserId());
  }

  public void logout() {
    currentUser = null;
    isLoggedIn = false;
  }

  // Static getters
  public static SessionManager getInstance() { return INSTANCE; }

  // Getters
  public User getCurrentUser() { return currentUser; }
  public int getCurrentUserId() { return getCurrentUser().getUserId(); }
  public HashMap<Integer, Folder> getUserFolders() { return userFolders; }
  public HashMap<Integer, PasswordEntry> getUserPasswordEntries() { return userPasswordEntries; }
}
