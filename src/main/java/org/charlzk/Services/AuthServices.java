package org.charlzk.Services;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.DAO.UserDAO;
import org.charlzk.Models.User;

import java.sql.SQLException;

public class AuthServices {
  private static final UserDAO userDAO = new UserDAO();

  public static boolean register(String username, String email, String password) throws SQLException {
    String hashedPassword = PasswordServices.hashPassword(password);
    return userDAO.createUser(username, email, hashedPassword);
  }

  public static boolean login(String email, String password) throws SQLException {
    User user = userDAO.getUserByEmail(email);

    if (user == null) {
      CustomJOptionPane.showErrorMessageDialog("The email or password you entered is incorrect. Please try again.", "Login Failed");
      return false;
    }

    if (!PasswordServices.verifyPassword(user.getPasswordHash(), password)) {
      CustomJOptionPane.showErrorMessageDialog("We couldn't verify your credentials. Please check your email and password and try again.", "Login Failed");
      return false;
    }

    return true;
  }
}
