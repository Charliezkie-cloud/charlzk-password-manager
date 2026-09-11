package org.charlzk.Events.AddPasswordEvents;

import org.charlzk.Controllers.AddPasswordController;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddPasswordWindowListener extends WindowAdapter {
  private final AddPasswordController addPasswordController;

  public AddPasswordWindowListener(AddPasswordController addPasswordController) {
    this.addPasswordController = addPasswordController;
  }

  @Override
  public void windowClosed(WindowEvent e) {
    super.windowClosed(e);
    addPasswordController.close();
  }
}
