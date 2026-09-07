package org.charlzk.Events.RegistrationEvents;

import org.charlzk.Controllers.RegistrationController;
import org.charlzk.Views.LoginView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegistrationWindowListener extends WindowAdapter {
  private final JFrame registrationView;
  private final RegistrationController registrationController;

  public RegistrationWindowListener(JFrame registrationView, RegistrationController registrationController) {
    this.registrationView = registrationView;
    this.registrationController = registrationController;
  }

  @Override
  public void windowClosed(WindowEvent e) {
    super.windowClosed(e);

    registrationView.dispose();
    registrationController.close();

    LoginView loginView = new LoginView();
    loginView.setVisible(true);
  }
}
