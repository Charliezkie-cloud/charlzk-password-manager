package org.charlzk.Events.MainEvents;

import org.charlzk.Controllers.MainController;
import org.charlzk.Views.AddPasswordView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPasswordOnAction implements ActionListener {
  // Views
  private JFrame addPasswordView;

  // Controller
  private final MainController mainController;

  public AddPasswordOnAction(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (addPasswordView == null)
      addPasswordView = new AddPasswordView(mainController);

    if (!addPasswordView.isVisible()) {
      addPasswordView.setVisible(true);
      return;
    }

    addPasswordView.toFront();
    addPasswordView.requestFocus();
  }
}
