package org.charlzk.Events.AddFolderEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.AddFolderController;
import org.charlzk.Controllers.MainController;
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
  private final AddFolderController addFolderController;

  public SaveButtonOnAction(AddFolderController addFolderController) {
    this.addFolderController = addFolderController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!validateForm()) return;

    String folderName = addFolderController.getFolderNameField().getText().trim();
    int currentUserId = SessionManager.getInstance().getCurrentUserId();
    MainController mainController = addFolderController.getMainController();

    try {
      Folder folder = folderDAO.createFolder(currentUserId, folderName);
      mainController.addFolderTableRow(folder);

      addFolderController.close();
      addFolderController.getAddFolderView().dispose();
      CustomJOptionPane.showSuccessMessageDialog("Folder Added!", "Success");
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }

  private boolean validateForm() {
    String folderName = addFolderController.getFolderNameField().getText();

    if (folderName.trim().isEmpty()) {
      CustomJOptionPane.showErrorMessageDialog("Folder name is required.", "Validation Message");
      return false;
    }

    return true;
  }
}
