package org.charlzk.Events.AddPasswordEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.AddPasswordController;
import org.charlzk.DAO.FolderDAO;
import org.charlzk.Models.Folder;
import org.charlzk.Models.User;
import org.charlzk.Session.SessionManager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class AddPasswordWindowListener extends WindowAdapter {
  // DAO
  private final FolderDAO folderDAO = new FolderDAO();

  // Current user
  private final User user = SessionManager.getInstance().getCurrentUser();

  // Controller
  private final AddPasswordController addPasswordController;

  public AddPasswordWindowListener(AddPasswordController addPasswordController) {
    this.addPasswordController = addPasswordController;
  }

  @Override
  public void windowClosed(WindowEvent e) {
    super.windowClosed(e);
    addPasswordController.close();
  }

  @Override
  public void windowOpened(WindowEvent e) {
    super.windowOpened(e);

    try {
      HashMap<Integer, Folder> result = folderDAO.getAllUserFolders(user.getUserId());

      for (Folder item : result.values())
        addPasswordController.addFolderComboBoxItem(item);
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }
}
