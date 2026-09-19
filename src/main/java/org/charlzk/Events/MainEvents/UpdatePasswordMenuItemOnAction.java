package org.charlzk.Events.MainEvents;

import org.charlzk.Controllers.MainController;
import org.charlzk.Views.AuthViews.EditPasswordView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePasswordMenuItemOnAction implements ActionListener {
  // Controller
  private final MainController mainController;

  public UpdatePasswordMenuItemOnAction(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    new EditPasswordView(mainController).setVisible(true);
  }
}
