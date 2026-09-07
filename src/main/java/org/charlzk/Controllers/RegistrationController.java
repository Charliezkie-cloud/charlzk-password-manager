package org.charlzk.Controllers;

import javax.swing.*;

import org.charlzk.Events.RegistrationEvents.CancelButtonOnAction;
import org.charlzk.Events.RegistrationEvents.RegisterButtonOnAction;
import org.charlzk.Events.RegistrationEvents.TermsOfUseButtonOnAction;

public class RegistrationController {
  // Buttons
  private final JButton termsOfUseButton;
  private final JButton cancelButton;
  private final JButton registerButton;

  // Events
  private final TermsOfUseButtonOnAction termsOfUseButtonOnAction;
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
          JButton termsOfUseButton,
          JCheckBox agreementCheckbox,

          // Buttons
          JButton cancelButton,
          JButton registerButton
  ) {
    this.termsOfUseButton = termsOfUseButton;
    this.cancelButton = cancelButton;
    this.registerButton = registerButton;
    
    this.termsOfUseButtonOnAction = new TermsOfUseButtonOnAction();
    this.cancelButtonOnAction = new CancelButtonOnAction(registrationView);
    this.registerButtonOnAction = new RegisterButtonOnAction(
            usernameField,
            emailField,
            passwordField,
            passwordConfirmationField,
            agreementCheckbox
    );

    // Component events
    termsOfUseButton.addActionListener(this.termsOfUseButtonOnAction);
    registerButton.addActionListener(this.registerButtonOnAction);
    cancelButton.addActionListener(this.cancelButtonOnAction);
  }

  public void close() {
    termsOfUseButton.removeActionListener(termsOfUseButtonOnAction);
    registerButton.removeActionListener(registerButtonOnAction);
    cancelButton.removeActionListener(cancelButtonOnAction);
  }
}
