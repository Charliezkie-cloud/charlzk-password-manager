package org.charlzk.Events.FoldersEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.FoldersController;
import org.charlzk.DAO.FolderDAO;
import org.charlzk.Models.Folder;
import org.charlzk.Session.SessionManager;
import org.charlzk.Views.AuthViews.EditFolderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateFolderMenuItemOnAction implements ActionListener {
  private final FolderDAO folderDAO = new FolderDAO();
  private final FoldersController foldersController;

  public UpdateFolderMenuItemOnAction(FoldersController foldersController) {
    this.foldersController = foldersController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int selectedFolderRowIndex = foldersController.getFoldersTable().getSelectedRow();
    if (selectedFolderRowIndex == -1) return;

    int modelRowIndex = foldersController.getFoldersTable().convertRowIndexToModel(selectedFolderRowIndex);
    Object selectedFolderIdValue = foldersController.getFoldersTableModel().getValueAt(modelRowIndex, 0);
    Object selectedFolderNameValue = foldersController.getFoldersTableModel().getValueAt(modelRowIndex, 1);

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

    Folder selectedFolder = SessionManager.getInstance().getUserFolders().get(parsedFolderId);
    new EditFolderView(foldersController.getMainController(), selectedFolder).setVisible(true);
  }
}
