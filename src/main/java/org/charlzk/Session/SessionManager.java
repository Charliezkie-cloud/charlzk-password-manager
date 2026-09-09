package org.charlzk.Session;

import org.charlzk.Models.User;

public class SessionManager {
  private static final SessionManager INSTANCE = new SessionManager();

  private User currentUser;
  private boolean isLoggedIn = false;

  private SessionManager() { }

  public static SessionManager getInstance() { return INSTANCE; }

  public void login(User user) {
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
