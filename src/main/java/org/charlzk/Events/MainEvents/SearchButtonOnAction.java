package org.charlzk.Events.MainEvents;

import org.charlzk.Controllers.MainController;
import org.charlzk.Models.Folder;
import org.charlzk.Models.PasswordEntry;
import org.charlzk.Services.TimeServices;
import org.charlzk.Session.SessionManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SearchButtonOnAction implements ActionListener {
  private final MainController mainController;

  public SearchButtonOnAction(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String searchText = mainController.getSearchField().getText().trim();

    if (searchText.isEmpty()) {
      mainController.refreshTables();
      return;
    }

    String selectedOption = (String) mainController.getSearchOptionComboBoxModel().getSelectedItem();
    String searchLower = searchText.toLowerCase();

    HashMap<Integer, Folder> userFolders = SessionManager.getInstance().getUserFolders();
    HashMap<Integer, PasswordEntry> userPasswordEntries = SessionManager.getInstance().getUserPasswordEntries();

    if ("Folder".equals(selectedOption)) {
      mainController.getFoldersTableModel().setRowCount(0);
      mainController.getPasswordsTableModel().setRowCount(0);

      for (Folder item : userFolders.values()) {
        if (item.getName().toLowerCase().contains(searchLower))
          mainController.addFolderTableRow(item);
      }
    } else if ("Password".equals(selectedOption)) {
      mainController.getPasswordsTableModel().setRowCount(0);

      for (PasswordEntry item : userPasswordEntries.values()) {
        if (item.getTitle().toLowerCase().contains(searchLower))
          mainController.getPasswordsTableModel().addRow(new Object[]{
                  item.getEntryId(),
                  item.getTitle(),
                  item.getUrl(),
                  TimeServices.formatTimeMillis(item.getCreatedAt()),
                  TimeServices.formatTimeMillis(item.getUpdatedAt())
          });
      }
    }
  }
}
