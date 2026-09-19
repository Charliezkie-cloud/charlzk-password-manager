package org.charlzk.Events.AddPasswordEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.AddPasswordController;
import org.charlzk.Controllers.MainController;
import org.charlzk.DAO.PasswordEntryDAO;
import org.charlzk.Models.Folder;
import org.charlzk.Models.PasswordEntry;
import org.charlzk.Session.SessionManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class SaveButtonOnAction implements ActionListener {
  // DAO
  private final PasswordEntryDAO passwordEntryDAO = new PasswordEntryDAO();

  // Views
  private final JFrame addPasswordView;

  // Controller
  private final AddPasswordController addPasswordController;
  private final MainController mainController;

  public SaveButtonOnAction(
          JFrame addPasswordView,
          MainController mainController,
          AddPasswordController addPasswordController
  ) {
    this.addPasswordView = addPasswordView;

    this.mainController = mainController;
    this.addPasswordController = addPasswordController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!validateForm()) return;

    String title = addPasswordController.getTitleField().getText().trim();
    String username = addPasswordController.getUsernameField().getText().trim();
    String url = addPasswordController.getUrlField().getText().trim();
    String password = addPasswordController.getPasswordField().getText().trim();
    String note = addPasswordController.getNoteTextArea().getText().trim();

    try {
      Folder selectedFolder = (Folder) addPasswordController.getFolderComboBox().getSelectedItem();
      if (selectedFolder == null) {
        CustomJOptionPane.showErrorMessageDialog("Please select a Folder.", "Validation Message");
        return;
      }

      PasswordEntry passwordEntry = passwordEntryDAO.createPasswordEntry(
              SessionManager.getInstance().getCurrentUserId(),
              selectedFolder.getFolderId(),
              title,
              username,
              password,
              url,
              note
      );

      SessionManager.getInstance()
              .getUserPasswordEntries()
              .put(passwordEntry.getEntryId(), passwordEntry);
      mainController.addPasswordTableRow(passwordEntry);
      addPasswordController.close();
      addPasswordView.dispose();
      CustomJOptionPane.showSuccessMessageDialog("Password Added!", "Success");
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }

  private boolean validateForm() {
    String title = addPasswordController.getTitleField().getText().trim();
    String url = addPasswordController.getUrlField().getText().trim();

    if (title.isEmpty()) {
      CustomJOptionPane.showErrorMessageDialog("Name is Required.", "Validation Message");
      return false;
    }

    if (url.isEmpty()) {
      CustomJOptionPane.showErrorMessageDialog("URL is Required.", "Validation Message");
      return false;
    }

    String urlRegex = "^https?://$|^(https?://)?([\\w-]+\\.)+[a-zA-Z]{2,}(:\\d{1,5})?(/\\S*)?$";
    if (!url.matches(urlRegex)) {
      CustomJOptionPane.showErrorMessageDialog("Please enter a valid URL.", "Validation Message");
      return false;
    }

    return true;
  }
}
