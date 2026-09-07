package org.charlzk.Events.LoginEvents;

import org.charlzk.Controllers.LoginController;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginWindowListener extends WindowAdapter {
  private final LoginController loginController;

  public LoginWindowListener(LoginController loginController) {
    this.loginController = loginController;
  }

  @Override
  public void windowClosed(WindowEvent e) {
    super.windowClosed(e);
    loginController.close();
  }
}
