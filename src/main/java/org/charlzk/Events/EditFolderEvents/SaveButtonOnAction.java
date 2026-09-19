package org.charlzk.Events.EditFolderEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.EditFolderController;
import org.charlzk.DAO.FolderDAO;
import org.charlzk.Models.Folder;
import org.charlzk.Session.SessionManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class SaveButtonOnAction implements ActionListener {
  // DAO
  private final FolderDAO folderDAO = new FolderDAO();

  // Controller
  private final EditFolderController editFolderController;

  public SaveButtonOnAction(EditFolderController editFolderController) {
    this.editFolderController = editFolderController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!validateForm()) return;

    SessionManager sessionManager = SessionManager.getInstance();
    Folder currentFolder = editFolderController.getSelectedFolder();
    String folderName = editFolderController.getFolderNameField().getText().trim();

    if (currentFolder.getName().equalsIgnoreCase(folderName)) {
      editFolderController.close();
      editFolderController.getEditFolderView().dispose();

      CustomJOptionPane.showSuccessMessageDialog("Folder Updated!", "Success");
      return;
    }

    try {
      Folder folder = folderDAO.updateFolder(currentFolder.getFolderId(), folderName);
      sessionManager.getUserFolders().put(folder.getFolderId(), folder);

      editFolderController.getMainController().refreshTables();
      editFolderController.getMainController().getFoldersController().refreshTable();
      editFolderController.close();
      editFolderController.getEditFolderView().dispose();

      CustomJOptionPane.showSuccessMessageDialog("Folder Updated!", "Success");
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showSuccessMessageDialog(ex.getMessage(), "Application Error");
    }
  }

  private boolean validateForm() {
    String folderName = editFolderController.getFolderNameField().getText().trim();

    if (folderName.trim().isEmpty()) {
      CustomJOptionPane.showErrorMessageDialog("Folder name is required.", "Validation Message");
      return false;
    }

    return true;
  }
}
