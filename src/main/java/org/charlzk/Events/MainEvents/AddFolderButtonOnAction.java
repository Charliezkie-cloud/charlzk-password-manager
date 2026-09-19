package org.charlzk.Events.MainEvents;

import org.charlzk.Controllers.MainController;
import org.charlzk.Views.AuthViews.AddFolderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFolderButtonOnAction implements ActionListener {
  private final MainController mainController;

  public AddFolderButtonOnAction(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    new AddFolderView(mainController).setVisible(true);
  }
}
