package org.charlzk.Events.AddFolderEvents;

import org.charlzk.Controllers.AddFolderController;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddFolderWindowListener extends WindowAdapter {
  private final AddFolderController addFolderController;

  public AddFolderWindowListener(AddFolderController addFolderController) {
    this.addFolderController = addFolderController;
  }

  @Override
  public void windowClosed(WindowEvent e) {
    super.windowClosed(e);
    addFolderController.close();
  }

  @Override
  public void windowOpened(WindowEvent e) {
    super.windowOpened(e);
    addFolderController.clearFields();
  }
}
