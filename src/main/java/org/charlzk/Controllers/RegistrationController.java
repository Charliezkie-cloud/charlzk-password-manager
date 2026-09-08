package org.charlzk.Controllers;

import javax.swing.*;
import javax.swing.event.HyperlinkListener;

import org.charlzk.Events.RegistrationEvents.CancelButtonOnAction;
import org.charlzk.Events.RegistrationEvents.RegisterButtonOnAction;
import org.charlzk.Events.RegistrationEvents.TermsOfUsePaneHyperlinkListener;

public class RegistrationController {
  // Buttons
  private final JEditorPane termsOfUsePane;
  private final JButton cancelButton;
  private final JButton registerButton;

  // Events
  private final HyperlinkListener termsOfUsePaneHyperlinkListener;
  private final RegisterButtonOnAction registerButtonOnAction;
  private final CancelButtonOnAction cancelButtonOnAction;

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
    this.termsOfUsePane = termsOfUsePane;
    this.cancelButton = cancelButton;
    this.registerButton = registerButton;

    this.termsOfUsePaneHyperlinkListener = new TermsOfUsePaneHyperlinkListener();
    this.cancelButtonOnAction = new CancelButtonOnAction(registrationView);
    this.registerButtonOnAction = new RegisterButtonOnAction(
            registrationView,
            usernameField,
            emailField,
            passwordField,
            passwordConfirmationField,
            agreementCheckbox
    );

    // Component events
    termsOfUsePane.addHyperlinkListener(termsOfUsePaneHyperlinkListener);
    registerButton.addActionListener(registerButtonOnAction);
    cancelButton.addActionListener(cancelButtonOnAction);
  }

  public void close() {
    termsOfUsePane.removeHyperlinkListener(termsOfUsePaneHyperlinkListener);
    registerButton.removeActionListener(registerButtonOnAction);
    cancelButton.removeActionListener(cancelButtonOnAction);
  }
}
