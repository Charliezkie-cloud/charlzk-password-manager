package org.charlzk.Services;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.DAO.UserDAO;
import org.charlzk.Models.User;
import org.charlzk.Session.SessionManager;

import java.io.IOException;
import java.sql.SQLException;

public class AuthServices {
  private static final UserDAO userDAO = new UserDAO();

  public static boolean register(String username, String email, String password) throws SQLException, IOException {
    String hashedPassword = PasswordServices.hashPassword(password);
    return userDAO.createUser(username, email, hashedPassword);
  }

  public static boolean login(String email, String password) throws SQLException, IOException {
    User user = userDAO.getUserByEmail(email);

    if (user == null) {
      CustomJOptionPane.showErrorMessageDialog("The email or password you entered is incorrect. Please try again.", "Login Failed");
      return false;
    }

    if (!PasswordServices.verifyPassword(user.getPasswordHash(), password)) {
      CustomJOptionPane.showErrorMessageDialog("We couldn't verify your credentials. Please check your email and password and try again.", "Login Failed");
      return false;
    }

    SessionManager.getInstance().login(user);

    return true;
  }
}
