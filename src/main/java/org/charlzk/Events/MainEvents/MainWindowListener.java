package org.charlzk.Events.MainEvents;

import org.charlzk.Controllers.MainController;
import org.charlzk.Models.Folder;
import org.charlzk.Session.SessionManager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

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

  @Override
  public void windowOpened(WindowEvent e) {
    super.windowOpened(e);

    HashMap<Integer, Folder> userFolders = SessionManager.getInstance().getUserFolders();
    for (Folder item : userFolders.values())
      mainController.addFolderTableRow(item);
  }
}
