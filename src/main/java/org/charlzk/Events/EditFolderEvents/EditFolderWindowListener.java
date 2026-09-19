package org.charlzk.Events.EditFolderEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.EditFolderController;
import org.charlzk.Models.Folder;
import org.charlzk.Session.SessionManager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class EditFolderWindowListener extends WindowAdapter {
  private final EditFolderController editFolderController;

  public EditFolderWindowListener(EditFolderController editFolderController) {
    this.editFolderController = editFolderController;
  }

  @Override
  public void windowClosed(WindowEvent e) {
    super.windowClosed(e);
    editFolderController.close();
  }

  @Override
  public void windowOpened(WindowEvent e) {
    super.windowOpened(e);

    SessionManager sessionManager = SessionManager.getInstance();
    HashMap<Integer, Folder> userFolders = sessionManager.getUserFolders();

    int selectedRowIndex = editFolderController.getMainController().getFoldersTable().getSelectedRow();
    if (selectedRowIndex == -1) return;

    int modelRowIndex = editFolderController.getMainController().getFoldersTable().convertRowIndexToModel(selectedRowIndex);
    Object selectedRowValue = editFolderController.getMainController().getFoldersTableModel().getValueAt(modelRowIndex, 0);

    try {
      int parsedRowValue = Integer.parseInt(String.valueOf(selectedRowValue));
      Folder folder = userFolders.get(parsedRowValue);

      if (folder != null) {
        editFolderController.setSelectedFolder(folder);
        editFolderController.getFolderNameField().setText(folder.getName());
      }
    } catch (NumberFormatException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }
}
