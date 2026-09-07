package org.charlzk.Controllers;

import org.charlzk.Events.LoginEvents.RegisterButtonOnAction;

import javax.swing.*;

public class LoginController {
  // Buttons
  private final JButton registerButton;
  private final JButton loginButton;

  // Events
  private final RegisterButtonOnAction registerButtonOnAction;

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

    // Component events
    registerButton.addActionListener(this.registerButtonOnAction);
  }

  public void close() {
    registerButton.removeActionListener(this.registerButtonOnAction);
  }
}
