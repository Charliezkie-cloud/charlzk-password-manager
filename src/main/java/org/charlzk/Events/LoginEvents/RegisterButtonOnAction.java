package org.charlzk.Events.LoginEvents;

import org.charlzk.Views.RegistrationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterButtonOnAction implements ActionListener {
  private final JFrame loginView;

  public RegisterButtonOnAction(JFrame loginView) {
    this.loginView = loginView;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    loginView.dispose();

    RegistrationView registrationView = new RegistrationView();
    registrationView.setVisible(true);
  }
}
