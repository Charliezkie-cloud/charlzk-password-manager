package org.charlzk.Events.FoldersEvents;

import org.charlzk.Controllers.FoldersController;
import org.charlzk.Models.Folder;
import org.charlzk.Session.SessionManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class SearchButtonOnAction implements ActionListener {
  private final FoldersController foldersController;

  public SearchButtonOnAction(FoldersController foldersController) {
    this.foldersController = foldersController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String searchText = foldersController.getSearchField().getText().trim();

    if (searchText.isEmpty()) {
      foldersController.refreshTable();
      return;
    }

    String searchLower = searchText.toLowerCase();
    HashMap<Integer, Folder> userFolders = SessionManager.getInstance().getUserFolders();

    if (userFolders != null) {
      foldersController.getFoldersTableModel().setRowCount(0);

      for (Folder item : userFolders.values()) {
        if (item.getName().toLowerCase().contains(searchLower)) {
          foldersController.addFolderTableRow(item);
        }
      }
    }
  }
}
