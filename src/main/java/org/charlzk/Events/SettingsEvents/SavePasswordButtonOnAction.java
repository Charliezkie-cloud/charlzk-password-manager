package org.charlzk.Events.SettingsEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.SettingsController;
import org.charlzk.DAO.UserDAO;
import org.charlzk.Models.User;
import org.charlzk.Services.PasswordServices;
import org.charlzk.Session.SessionManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class SavePasswordButtonOnAction implements ActionListener {
  // DAO
  private final UserDAO userDAO = new UserDAO();

  // Controller
  private final SettingsController settingsController;

  public SavePasswordButtonOnAction(SettingsController settingsController) {
    this.settingsController = settingsController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!validateForm()) return;

    char[] passwordChar = settingsController.getNewPasswordField().getPassword();
    String password = new String(passwordChar);
    Arrays.fill(passwordChar, '\0');

    String hashedPassword = PasswordServices.hashPassword(password);
    int currentUserId = SessionManager.getInstance().getCurrentUserId();

    try {
      User updatedUser = userDAO.updateUserPassword(currentUserId, hashedPassword);
      if (updatedUser == null) {
        CustomJOptionPane.showErrorMessageDialog("Failed to update password.", "Application Error");
        return;
      }

      settingsController.getNewPasswordField().setText("");
      settingsController.getNewPasswordConfirmationField().setText("");

      CustomJOptionPane.showSuccessMessageDialog("Password updated successfully!", "Success");
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }

  private boolean validateForm() {
    char[] password = settingsController.getNewPasswordField().getPassword();
    char[] passwordConfirmation = settingsController.getNewPasswordConfirmationField().getPassword();

    try {
      if (password.length == 0) {
        CustomJOptionPane.showErrorMessageDialog("Password is required.", "Validation Error");
        return false;
      }

      if (password.length < 8) {
        CustomJOptionPane.showErrorMessageDialog("Password must be at least 8 characters long.", "Validation Error");
        return false;
      }

      boolean hasUpper = false, hasLower = false, hasDigit = false;
      for (char c : password) {
        if (Character.isUpperCase(c)) hasUpper = true;
        else if (Character.isLowerCase(c)) hasLower = true;
        else if (Character.isDigit(c)) hasDigit = true;
      }

      if (!hasUpper || !hasLower || !hasDigit) {
        CustomJOptionPane.showErrorMessageDialog("Password must contain at least one uppercase letter, one lowercase letter, and one number.", "Validation Error");
        return false;
      }

      if (!Arrays.equals(password, passwordConfirmation)) {
        CustomJOptionPane.showErrorMessageDialog("Passwords do not match.", "Validation Error");
        return false;
      }

      return true;
    } finally {
      Arrays.fill(password, '\0');
      Arrays.fill(passwordConfirmation, '\0');
    }
  }
}
