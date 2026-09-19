package org.charlzk.Events.FoldersEvents;

import org.charlzk.Controllers.FoldersController;
import org.charlzk.Views.AuthViews.AddFolderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFolderButtonOnAction implements ActionListener {
  private final FoldersController foldersController;

  public AddFolderButtonOnAction(FoldersController foldersController) {
    this.foldersController = foldersController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    new AddFolderView(foldersController.getMainController()).setVisible(true);
  }
}
