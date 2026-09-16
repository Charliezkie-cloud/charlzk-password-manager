package org.charlzk.Events.MainEvents;

import org.charlzk.Controllers.MainController;
import org.charlzk.Views.AddPasswordView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPasswordButtonOnAction implements ActionListener {
  // Controller
  private final MainController mainController;

  public AddPasswordButtonOnAction(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    new AddPasswordView(mainController).setVisible(true);
  }
}
