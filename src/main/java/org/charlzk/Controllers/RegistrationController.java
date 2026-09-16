package org.charlzk.Controllers;

import javax.swing.*;
import javax.swing.event.HyperlinkListener;

import org.charlzk.Events.RegistrationEvents.CancelButtonOnAction;
import org.charlzk.Events.RegistrationEvents.RegisterButtonOnAction;
import org.charlzk.Events.RegistrationEvents.RegistrationWindowListener;
import org.charlzk.Events.RegistrationEvents.TermsOfUsePaneHyperlinkListener;

public class RegistrationController {
  // Views
  private final JFrame registrationView;

  // Fields
  private final JTextField usernameField;
  private final JTextField emailField;
  private final JPasswordField passwordField;
  private final JPasswordField passwordConfirmationField;

  // Checkbox
  private final JCheckBox agreementCheckbox;

  // Buttons
  private final JEditorPane termsOfUsePane;
  private final JButton cancelButton;
  private final JButton registerButton;

  // Events
  private final HyperlinkListener termsOfUsePaneHyperlinkListener;
  private final RegisterButtonOnAction registerButtonOnAction;
  private final CancelButtonOnAction cancelButtonOnAction;
  private final RegistrationWindowListener registrationWindowListener;

  public RegistrationController (
          // View
          JFrame registrationView,

          // Fields
          JTextField usernameField,
          JTextField emailField,
          JPasswordField passwordField,
          JPasswordField passwordConfirmationField,

          // Legal components
          JEditorPane termsOfUsePane,
          JCheckBox agreementCheckbox,

          // Buttons
          JButton cancelButton,
          JButton registerButton
  ) {
    this.registrationView = registrationView;

    this.usernameField = usernameField;
    this.emailField = emailField;
    this.passwordField = passwordField;
    this.passwordConfirmationField = passwordConfirmationField;

    this.agreementCheckbox = agreementCheckbox;

    this.termsOfUsePane = termsOfUsePane;
    this.cancelButton = cancelButton;
    this.registerButton = registerButton;

    this.registrationWindowListener = new RegistrationWindowListener(this);
    this.termsOfUsePaneHyperlinkListener = new TermsOfUsePaneHyperlinkListener();
    this.cancelButtonOnAction = new CancelButtonOnAction(registrationView);
    this.registerButtonOnAction = new RegisterButtonOnAction(this);

    // Component events
    registrationView.addWindowListener(registrationWindowListener);
    termsOfUsePane.addHyperlinkListener(termsOfUsePaneHyperlinkListener);
    registerButton.addActionListener(registerButtonOnAction);
    cancelButton.addActionListener(cancelButtonOnAction);
  }

  public void close() {
    registrationView.removeWindowListener(registrationWindowListener);
    termsOfUsePane.removeHyperlinkListener(termsOfUsePaneHyperlinkListener);
    registerButton.removeActionListener(registerButtonOnAction);
    cancelButton.removeActionListener(cancelButtonOnAction);
  }

  // Getters
  public JFrame getRegistrationView() { return registrationView; }
  public JTextField getUsernameField() { return usernameField; }
  public JTextField getEmailField() { return emailField; }
  public JPasswordField getPasswordField() { return passwordField; }
  public JPasswordField getPasswordConfirmationField() { return passwordConfirmationField; }
  public JCheckBox getAgreementCheckbox() { return agreementCheckbox; }

  // Utils
  public void clearFields() {
    usernameField.setText("");
    emailField.setText("");
    passwordField.setText("");
    passwordConfirmationField.setText("");
    agreementCheckbox.setSelected(false);
  }
}
