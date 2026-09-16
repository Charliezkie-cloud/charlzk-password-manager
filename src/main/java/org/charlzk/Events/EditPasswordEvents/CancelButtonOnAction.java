package org.charlzk.Events.EditPasswordEvents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelButtonOnAction implements ActionListener {
  private final JFrame editPasswordView;

  public CancelButtonOnAction(JFrame editPasswordView) {
    this.editPasswordView = editPasswordView;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    editPasswordView.dispose();
  }
}
