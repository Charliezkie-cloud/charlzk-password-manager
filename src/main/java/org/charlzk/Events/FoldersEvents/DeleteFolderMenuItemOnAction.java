package org.charlzk.Events.FoldersEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.FoldersController;
import org.charlzk.DAO.FolderDAO;
import org.charlzk.Models.Folder;
import org.charlzk.Session.SessionManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteFolderMenuItemOnAction implements ActionListener {
  // DAO
  private final FolderDAO folderDAO = new FolderDAO();

  // Controller
  private final FoldersController foldersController;

  public DeleteFolderMenuItemOnAction(FoldersController foldersController) {
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
      CustomJOptionPane.showErrorMessageDialog("The default folder cannot be deleted.", "Action Denied");
      return;
    }

    int result = CustomJOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to delete this folder?",
            "Deletion Confirmation",
            CustomJOptionPane.YES_NO_OPTION
    );

    if (result == CustomJOptionPane.YES_OPTION) {
      try {
        Folder folder = folderDAO.deleteFolder(parsedFolderId);

        if (folder == null) return;

        if (SessionManager.getInstance().getUserFolders() != null) {
          SessionManager.getInstance().getUserFolders().remove(parsedFolderId);
        }

        foldersController.getFoldersTableModel().removeRow(modelRowIndex);
        foldersController.getMainController().getFoldersTableModel().removeRow(modelRowIndex);
      } catch (SQLException | IOException ex) {
        CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
      }
    }

  }
}
