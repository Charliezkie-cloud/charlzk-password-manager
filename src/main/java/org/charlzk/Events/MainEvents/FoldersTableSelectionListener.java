package org.charlzk.Events.MainEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.MainController;
import org.charlzk.DAO.PasswordEntryDAO;
import org.charlzk.Models.PasswordEntry;
import org.charlzk.Services.TimeServices;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class FoldersTableSelectionListener implements ListSelectionListener {
  // DAO's
  private final PasswordEntryDAO passwordEntryDAO = new PasswordEntryDAO();

  // Controller
  private final MainController mainController;

  public FoldersTableSelectionListener(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    if (e.getValueIsAdjusting()) return;

    mainController.getPasswordsTableModel().setRowCount(0);
    int selectedRow = mainController.getFoldersTable().getSelectedRow();
    if (selectedRow == -1) return;

    Object selectedRowValue = mainController.getFoldersTableModel().getValueAt(selectedRow, 0);

    try {
      int parsedRowValue = Integer.parseInt(String.valueOf(selectedRowValue));
      HashMap<Integer, PasswordEntry> folderPasswordEntries = passwordEntryDAO.getAllFolderPasswordEntries(parsedRowValue);

      for (PasswordEntry item : folderPasswordEntries.values())
        mainController.getPasswordsTableModel().addRow(new Object[]{
                item.getEntryId(),
                item.getTitle(),
                item.getUrl(),
                TimeServices.formatTimeMillis(item.getCreatedAt()),
                TimeServices.formatTimeMillis(item.getUpdatedAt())
        });
    } catch (SQLException | IOException | NumberFormatException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }
}
