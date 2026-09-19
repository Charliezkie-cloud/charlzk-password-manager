package org.charlzk.Events.MainEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.MainController;
import org.charlzk.DAO.FolderDAO;
import org.charlzk.Views.AuthViews.EditFolderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditFolderMenuItemOnAction implements ActionListener {
  private final FolderDAO folderDAO = new FolderDAO();
  private final MainController mainController;

  public EditFolderMenuItemOnAction(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int selectedFolderRowIndex = mainController.getFoldersTable().getSelectedRow();
    if (selectedFolderRowIndex == -1) return;

    int modelRowIndex = mainController.getFoldersTable().convertRowIndexToModel(selectedFolderRowIndex);
    Object selectedFolderIdValue = mainController.getFoldersTableModel().getValueAt(modelRowIndex, 0);
    Object selectedFolderNameValue = mainController.getFoldersTableModel().getValueAt(modelRowIndex, 1);

    int parsedFolderId;
    try {
      parsedFolderId = Integer.parseInt(String.valueOf(selectedFolderIdValue));
    } catch (NumberFormatException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
      return;
    }

    String folderName = String.valueOf(selectedFolderNameValue);
    if (parsedFolderId == folderDAO.DEFAULT_FOLDER_ID || "None".equalsIgnoreCase(folderName)) {
      CustomJOptionPane.showErrorMessageDialog("The default folder cannot be edited.", "Action Denied");
      return;
    }

    new EditFolderView(mainController).setVisible(true);
  }
}
