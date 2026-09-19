package org.charlzk.Events.AddFolderEvents;

import org.charlzk.Controllers.AddFolderController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelButtonOnAction implements ActionListener {
  private final AddFolderController addFolderController;

  public CancelButtonOnAction(AddFolderController addFolderController) {
    this.addFolderController = addFolderController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    addFolderController.getAddFolderView().dispose();
  }
}
