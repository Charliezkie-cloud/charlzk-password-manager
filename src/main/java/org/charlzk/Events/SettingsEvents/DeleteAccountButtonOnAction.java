package org.charlzk.Events.SettingsEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.MainController;
import org.charlzk.Controllers.SettingsController;
import org.charlzk.DAO.UserDAO;
import org.charlzk.Session.SessionManager;
import org.charlzk.Views.NoAuthViews.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteAccountButtonOnAction implements ActionListener {
  // DAO
  private final UserDAO userDAO = new UserDAO();

  // Controller
  private final SettingsController settingsController;

  public DeleteAccountButtonOnAction(SettingsController settingsController) {
    this.settingsController = settingsController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int choice = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to delete your account? This action cannot be undone.",
            "Delete Account",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    if (choice != JOptionPane.YES_OPTION) return;

    int currentUserId = SessionManager.getInstance().getCurrentUserId();

    try {
      if (userDAO.deleteUser(currentUserId) == null) {
        CustomJOptionPane.showErrorMessageDialog("Failed to delete account.", "Application Error");
        return;
      }

      MainController mainController = settingsController.getMainController();
      SessionManager.getInstance().logout();
      mainController.close();
      mainController.getMainView().dispose();

      LoginView loginView = new LoginView();
      loginView.setVisible(true);
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }
}
