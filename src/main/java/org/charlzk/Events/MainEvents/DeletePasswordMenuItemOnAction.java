package org.charlzk.Events.MainEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.MainController;
import org.charlzk.DAO.PasswordEntryDAO;
import org.charlzk.Models.PasswordEntry;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class DeletePasswordMenuItemOnAction implements ActionListener {
  // DAO
  private final PasswordEntryDAO passwordEntryDAO = new PasswordEntryDAO();

  // Controller
  private final MainController mainController;

  public DeletePasswordMenuItemOnAction(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int result = CustomJOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item?", "Deletion Confirmation", CustomJOptionPane.YES_NO_OPTION);
    if (result == CustomJOptionPane.YES_OPTION) {
      int selectedPasswordTableIndex = mainController.getPasswordsTable().getSelectedRow();
      if (selectedPasswordTableIndex == -1) return;

      Object selectedPasswordTableValue = mainController.getPasswordsTableModel().getValueAt(selectedPasswordTableIndex, 0);
      try {
         int parsedRowValue = Integer.parseInt(String.valueOf(selectedPasswordTableValue));
        PasswordEntry passwordEntry = passwordEntryDAO.deletePasswordEntry(parsedRowValue);

        if (passwordEntry == null)
          return;
        mainController.getPasswordsTableModel().removeRow(selectedPasswordTableIndex);
      } catch (SQLException | IOException | NumberFormatException ex) {
        CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
      }
    }
  }
}
