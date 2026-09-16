package org.charlzk.Events.EditPasswordEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.EditPasswordController;
import org.charlzk.Models.Folder;
import org.charlzk.Models.PasswordEntry;
import org.charlzk.Session.SessionManager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class EditPasswordWindowListener extends WindowAdapter {
  // Controllers
  private final EditPasswordController editPasswordController;

  public EditPasswordWindowListener(EditPasswordController editPasswordController) {
    this.editPasswordController = editPasswordController;
  }

  @Override
  public void windowClosed(WindowEvent e) {
    super.windowClosed(e);
    editPasswordController.close();
  }

  @Override
  public void windowActivated(WindowEvent e) {
    super.windowActivated(e);

    SessionManager sessionManager = SessionManager.getInstance();
    HashMap<Integer, Folder> userFolders = sessionManager.getUserFolders();
    HashMap<Integer, PasswordEntry> userPasswordEntries = sessionManager.getUserPasswordEntries();

    for (Folder item : userFolders.values())
      editPasswordController.addFolderComboBoxItem(item);

    int selectedRowIndex = editPasswordController.getMainController().getPasswordsTable().getSelectedRow();
    Object selectedRowValue = editPasswordController.getMainController().getPasswordsTableModel().getValueAt(selectedRowIndex, 0);

    try {
      int parsedRowValue = Integer.parseInt(String.valueOf(selectedRowValue));
      PasswordEntry passwordEntry = userPasswordEntries.get(parsedRowValue);

      editPasswordController.setCurrentPasswordEntry(passwordEntry);
      editPasswordController.getTitleField().setText(passwordEntry.getTitle());
      editPasswordController.getUsernameField().setText(passwordEntry.getUsername());
      editPasswordController.getUrlField().setText(passwordEntry.getUrl());
      editPasswordController.getPasswordField().setText(passwordEntry.getPassword());
      editPasswordController.getNoteTextArea().setText(passwordEntry.getNote());
    } catch (NumberFormatException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }
}
