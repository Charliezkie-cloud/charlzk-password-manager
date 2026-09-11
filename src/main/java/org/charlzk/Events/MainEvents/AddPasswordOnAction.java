package org.charlzk.Events.MainEvents;

import org.charlzk.Views.AddPasswordView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPasswordOnAction implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    AddPasswordView addPasswordView = new AddPasswordView();
    addPasswordView.setVisible(true);
  }
}
