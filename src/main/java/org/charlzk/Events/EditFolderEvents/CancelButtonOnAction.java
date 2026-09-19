package org.charlzk.Events.EditFolderEvents;

import org.charlzk.Controllers.EditFolderController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelButtonOnAction implements ActionListener {
  private final EditFolderController editFolderController;

  public CancelButtonOnAction(EditFolderController editFolderController) {
    this.editFolderController = editFolderController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    editFolderController.getEditFolderView().dispose();
  }
}
