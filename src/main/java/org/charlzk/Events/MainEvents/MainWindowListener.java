package org.charlzk.Events.MainEvents;

import org.charlzk.Controllers.MainController;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindowListener extends WindowAdapter {
  private final MainController mainController;

  public MainWindowListener(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void windowClosed(WindowEvent e) {
    super.windowClosed(e);
    mainController.close();
  }
}
