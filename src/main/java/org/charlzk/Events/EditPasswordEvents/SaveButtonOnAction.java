package org.charlzk.Events.EditPasswordEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.EditPasswordController;
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

  // Controller
  private final EditPasswordController editPasswordController;

  public SaveButtonOnAction(EditPasswordController editPasswordController) {
    this.editPasswordController = editPasswordController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!validateForm()) return;

    String title = editPasswordController.getTitleField().getText().trim();
    String username = editPasswordController.getUsernameField().getText().trim();
    String url = editPasswordController.getUrlField().getText().trim();
    String password = editPasswordController.getPasswordField().getText().trim();
    String note = editPasswordController.getNoteTextArea().getText().trim();

    try {
      Folder selectedFolder = (Folder) editPasswordController.getFolderComboBoxModel().getSelectedItem();
      if (selectedFolder == null) {
        CustomJOptionPane.showErrorMessageDialog("Please select a Folder.", "Validation Message");
        return;
      }

      int currentPasswordEntryId = editPasswordController.getCurrentPasswordEntry().getEntryId();
      PasswordEntry passwordEntry = passwordEntryDAO.updatePasswordEntry(
              currentPasswordEntryId,
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
      editPasswordController.getMainController().refreshTables();
      editPasswordController.close();
      editPasswordController.getEditPasswordView().dispose();
      CustomJOptionPane.showSuccessMessageDialog("Password Updated!", "Success");
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }

  private boolean validateForm() {
    String title = editPasswordController.getTitleField().getText().trim();
    String url = editPasswordController.getUrlField().getText().trim();

    if (editPasswordController.getCurrentPasswordEntry() == null) {
      CustomJOptionPane.showErrorMessageDialog("Please select a Password.", "Validation Message");
      return false;
    }

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
