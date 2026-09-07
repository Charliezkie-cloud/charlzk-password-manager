package org.charlzk.Events.RegistrationEvents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelButtonOnAction implements ActionListener {
  private final JFrame registrationView;

  public CancelButtonOnAction(JFrame registrationView) {
    this.registrationView = registrationView;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    registrationView.dispose();
  }
}
