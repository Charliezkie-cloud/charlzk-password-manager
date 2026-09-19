package org.charlzk.Events.EditFolderEvents;

import org.charlzk.Controllers.EditFolderController;
import org.charlzk.Models.Folder;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    Folder selectedFolder = editFolderController.getSelectedFolder();
    if (selectedFolder != null) {
      editFolderController.getFolderNameField().setText(selectedFolder.getName());
    }
  }
}
