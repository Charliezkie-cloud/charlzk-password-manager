package org.charlzk.Events.AddPasswordEvents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelButtonOnAction implements ActionListener {
  private final JFrame addPasswordView;

  public CancelButtonOnAction(JFrame addPasswordView) {
    this.addPasswordView = addPasswordView;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    addPasswordView.dispose();
  }
}
