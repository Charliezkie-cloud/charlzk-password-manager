package org.charlzk.Controllers;

import org.charlzk.Events.LoginEvents.LoginButtonOnAction;
import org.charlzk.Events.LoginEvents.RegisterPaneHyperlinkListener;

import javax.swing.*;

public class LoginController {
  // Buttons
  private final JEditorPane registerPane;
  private final JButton loginButton;

  // Events
  private final RegisterPaneHyperlinkListener registerPaneHyperlinkListener;
  private final LoginButtonOnAction loginButtonOnAction;

  public LoginController(
          // View
          JFrame loginView,

          // Fields
          JTextField emailField,
          JPasswordField passwordField,

          // Buttons
          JEditorPane registerPane,
          JButton loginButton
  ) {
    this.registerPane = registerPane;
    this.loginButton = loginButton;

    this.registerPaneHyperlinkListener = new RegisterPaneHyperlinkListener(loginView);
    this.loginButtonOnAction = new LoginButtonOnAction(loginView, emailField, passwordField);

    // Component events
    registerPane.addHyperlinkListener(registerPaneHyperlinkListener);
    loginButton.addActionListener(loginButtonOnAction);
  }

  public void close() {
    registerPane.removeHyperlinkListener(registerPaneHyperlinkListener);
    loginButton.removeActionListener(loginButtonOnAction);
  }
}
