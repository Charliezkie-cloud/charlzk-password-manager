package org.charlzk.Events.RegistrationEvents;

import org.charlzk.Controllers.RegistrationController;
import org.charlzk.Views.LoginView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegistrationWindowListener extends WindowAdapter {
  private final RegistrationController registrationController;

  public RegistrationWindowListener(RegistrationController registrationController) {
    this.registrationController = registrationController;
  }

  @Override
  public void windowClosed(WindowEvent e) {
    super.windowClosed(e);

    registrationController.getRegistrationView().dispose();
    registrationController.close();

    new LoginView().setVisible(true);
  }

  @Override
  public void windowOpened(WindowEvent e) {
    super.windowOpened(e);
    registrationController.clearFields();
  }
}
