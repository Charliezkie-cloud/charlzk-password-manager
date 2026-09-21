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

public class SaveEmailButtonOnAction implements ActionListener {
  // DAO
  private final UserDAO userDAO = new UserDAO();

  // Controller
  private final SettingsController settingsController;

  public SaveEmailButtonOnAction(SettingsController settingsController) {
    this.settingsController = settingsController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!validateForm()) return;

    String newEmail = settingsController.getNewEmailField().getText().trim();
    int currentUserId = SessionManager.getInstance().getCurrentUserId();

    try {
      User updatedUser = userDAO.updateUserEmail(currentUserId, newEmail);
      if (updatedUser == null) {
        CustomJOptionPane.showErrorMessageDialog("Failed to update email.", "Application Error");
        return;
      }

      settingsController.getCurrentEmailField().setText(newEmail);
      settingsController.getNewEmailField().setText("");

      CustomJOptionPane.showSuccessMessageDialog("Email updated successfully!", "Success");
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }

  private boolean validateForm() {
    String newEmail = settingsController.getNewEmailField().getText().trim();

    if (newEmail.isEmpty()) {
      CustomJOptionPane.showErrorMessageDialog("Email is required.", "Validation Error");
      return false;
    }

    if (!newEmail.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
      CustomJOptionPane.showErrorMessageDialog("Please enter a valid email address.", "Validation Error");
      return false;
    }

    String currentEmail = settingsController.getCurrentEmailField().getText().trim();
    if (newEmail.equalsIgnoreCase(currentEmail)) {
      CustomJOptionPane.showErrorMessageDialog("New email cannot be the same as the current email.", "Validation Error");
      return false;
    }

    return true;
  }
}
