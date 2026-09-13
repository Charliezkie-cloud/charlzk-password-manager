package org.charlzk.Events.AddPasswordEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.MainController;
import org.charlzk.DAO.FolderDAO;
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
  private final MainController mainController;

  // Fields
  private final JTextField titleField;
  private final JTextField usernameField;
  private final JTextField urlField;
  private final JTextField passwordField;

  // ComboBox
  private final JComboBox<Folder> folderComboBox;

  public SaveButtonOnAction(
          JFrame addPasswordView,

          MainController mainController,

          JTextField titleField,
          JTextField usernameField,
          JTextField urlField,
          JTextField passwordField,

          JComboBox<Folder> folderComboBox
  ) {
    this.addPasswordView = addPasswordView;

    this.mainController = mainController;

    this.titleField = titleField;
    this.usernameField = usernameField;
    this.urlField = urlField;
    this.passwordField = passwordField;

    this.folderComboBox = folderComboBox;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!validateForm()) return;

    String title = titleField.getText().trim();
    String username = usernameField.getText().trim();
    String url = urlField.getText().trim();
    String password = passwordField.getText().trim();

    try {
      Folder selectedFolder = (Folder) folderComboBox.getSelectedItem();
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
              url
      );

      SessionManager.getInstance()
              .getUserPasswordEntries()
              .put(passwordEntry.getEntryId(), passwordEntry);
      mainController.refreshTables();
      addPasswordView.dispose();
      CustomJOptionPane.showSuccessMessageDialog("Password Added!", "Success");
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }

  private boolean validateForm() {
    String title = titleField.getText().trim();
    String url = urlField.getText().trim();

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
