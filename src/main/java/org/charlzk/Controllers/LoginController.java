package org.charlzk.Controllers;

import org.charlzk.Events.LoginEvents.LoginButtonOnAction;
import org.charlzk.Events.LoginEvents.LoginWindowListener;
import org.charlzk.Events.LoginEvents.RegisterPaneHyperlinkListener;

import javax.swing.*;

public class LoginController {
  // Views
  private final JFrame loginView;

  // Fields
  private final JTextField emailField;
  private final JPasswordField passwordField;

  // Buttons
  private final JEditorPane registerPane;
  private final JButton loginButton;

  // Events
  private final RegisterPaneHyperlinkListener registerPaneHyperlinkListener;
  private final LoginButtonOnAction loginButtonOnAction;
  private final LoginWindowListener loginWindowListener;

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
    this.loginView = loginView;

    this.emailField = emailField;
    this.passwordField = passwordField;

    this.registerPane = registerPane;
    this.loginButton = loginButton;

    this.loginWindowListener = new LoginWindowListener(this);
    this.registerPaneHyperlinkListener = new RegisterPaneHyperlinkListener(this);
    this.loginButtonOnAction = new LoginButtonOnAction(this);

    // Component events
    loginView.addWindowListener(loginWindowListener);
    registerPane.addHyperlinkListener(registerPaneHyperlinkListener);
    loginButton.addActionListener(loginButtonOnAction);
  }

  public void close() {
    loginView.removeWindowListener(loginWindowListener);
    registerPane.removeHyperlinkListener(registerPaneHyperlinkListener);
    loginButton.removeActionListener(loginButtonOnAction);
  }

  // Getters
  public JFrame getLoginView() { return loginView; }
  public JTextField getEmailField() { return emailField; }
  public JPasswordField getPasswordField() { return passwordField; }

  // Utils
  public void clearFields() {
    emailField.setText("");
    passwordField.setText("");
  }
}
