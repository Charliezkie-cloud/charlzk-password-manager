package org.charlzk.Events.SettingsEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.SettingsController;
import org.charlzk.DAO.UserDAO;
import org.charlzk.Models.User;
import org.charlzk.Session.SessionManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class SaveUsernameButtonOnAction implements ActionListener {
  // DAO
  private final UserDAO userDAO = new UserDAO();

  // Controller
  private final SettingsController settingsController;

  public SaveUsernameButtonOnAction(SettingsController settingsController) {
    this.settingsController = settingsController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!validateForm()) return;

    String newUsername = settingsController.getNewUsernameField().getText().trim();
    int currentUserId = SessionManager.getInstance().getCurrentUserId();

    try {
      User updatedUser = userDAO.updateUserUsername(currentUserId, newUsername);
      if (updatedUser == null) {
        CustomJOptionPane.showErrorMessageDialog("Failed to update username.", "Application Error");
        return;
      }

      settingsController.getCurrentUsernameField().setText(newUsername);
      settingsController.getNewUsernameField().setText("");

      CustomJOptionPane.showSuccessMessageDialog("Username updated successfully!", "Success");
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }

  private boolean validateForm() {
    String newUsername = settingsController.getNewUsernameField().getText().trim();

    if (newUsername.isEmpty()) {
      CustomJOptionPane.showErrorMessageDialog("Username is required.", "Validation Error");
      return false;
    }

    if (newUsername.length() < 3 || newUsername.length() > 20) {
      CustomJOptionPane.showErrorMessageDialog("Username must be between 3 and 20 characters.", "Validation Error");
      return false;
    }

    if (!newUsername.matches("^[a-zA-Z0-9_]+$")) {
      CustomJOptionPane.showErrorMessageDialog("Username can only contain letters, numbers, and underscores.", "Validation Error");
      return false;
    }

    String currentUsername = settingsController.getCurrentUsernameField().getText().trim();
    if (newUsername.equals(currentUsername)) {
      CustomJOptionPane.showErrorMessageDialog("New username cannot be the same as the current username.", "Validation Error");
      return false;
    }

    return true;
  }
}
