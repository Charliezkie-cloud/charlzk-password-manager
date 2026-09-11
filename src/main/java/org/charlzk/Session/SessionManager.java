package org.charlzk.Session;

import org.charlzk.DAO.FolderDAO;
import org.charlzk.Models.User;

import java.io.IOException;
import java.sql.SQLException;

public class SessionManager {
  private final FolderDAO folderDAO = new FolderDAO();

  private static final SessionManager INSTANCE = new SessionManager();

  private User currentUser;
  private boolean isLoggedIn = false;

  private SessionManager() { }

  public static SessionManager getInstance() { return INSTANCE; }

  public void login(User user) throws SQLException, IOException {
    currentUser = user;
    isLoggedIn = true;
  }

  public void logout() {
    currentUser = null;
    isLoggedIn = false;
  }

  public User getCurrentUser() { return currentUser; }
  public int getCurrentUserId() { return getCurrentUser().getUserId(); }
}
