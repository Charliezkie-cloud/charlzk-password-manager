package org.charlzk.Controllers;

import org.charlzk.Events.LoginEvents.LoginButtonOnAction;
import org.charlzk.Events.LoginEvents.RegisterButtonOnAction;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginController {
  // Buttons
  private final JButton registerButton;
  private final JButton loginButton;

  // Events
  private final RegisterButtonOnAction registerButtonOnAction;
  private final LoginButtonOnAction loginButtonOnAction;

  public LoginController(
          // View
          JFrame loginView,

          // Fields
          JTextField emailField,
          JPasswordField passwordField,

          // Buttons
          JButton registerButton,
          JButton loginButton
  ) {
    this.registerButton = registerButton;
    this.loginButton = loginButton;

    this.registerButtonOnAction = new RegisterButtonOnAction(loginView);
    this.loginButtonOnAction = new LoginButtonOnAction(loginView, emailField, passwordField);

    // Component events
    registerButton.addActionListener(registerButtonOnAction);
    loginButton.addActionListener(loginButtonOnAction);
  }

  public void close() {
    registerButton.removeActionListener(registerButtonOnAction);
    loginButton.removeActionListener(loginButtonOnAction);
  }
}
