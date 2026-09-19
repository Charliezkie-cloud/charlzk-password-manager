package org.charlzk.Events.RegistrationEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.RegistrationController;
import org.charlzk.Services.AuthServices;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterButtonOnAction implements ActionListener {
  private final RegistrationController registrationController;

  public RegisterButtonOnAction(RegistrationController registrationController) {
    this.registrationController = registrationController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (!validateForm()) return;

    String username = registrationController.getUsernameField().getText().trim();
    String email = registrationController.getEmailField().getText().trim();
    char[] passwordChar = registrationController.getPasswordField().getPassword();
    String password = new String(passwordChar);

    try {
      if (!AuthServices.register(username, email, password))
        return;

      CustomJOptionPane.showSuccessMessageDialog("Account registered!", "Registration Success");
      registrationController.getRegistrationView().dispose();
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }

  private boolean validateForm() {
    String username = registrationController.getUsernameField().getText().trim();
    String email = registrationController.getEmailField().getText().trim();
    char[] password = registrationController.getPasswordField().getPassword();
    char[] passwordConfirmation = registrationController.getPasswordConfirmationField().getPassword();

    try {
      if (username.isEmpty()) {
        CustomJOptionPane.showErrorMessageDialog("Username is required.", "Validation Error");
        return false;
      }

      if (username.length() < 3 || username.length() > 20) {
        CustomJOptionPane.showErrorMessageDialog("Username must be between 3 and 20 characters.", "Validation Error");
        return false;
      }

      if (!username.matches("^[a-zA-Z0-9_]+$")) {
        CustomJOptionPane.showErrorMessageDialog("Username can only contain letters, numbers, and underscores.", "Validation Error");
        return false;
      }

      if (email.isEmpty()) {
        CustomJOptionPane.showErrorMessageDialog("Email is required.", "Validation Error");
        return false;
      }

      if (!email.matches("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
        CustomJOptionPane.showErrorMessageDialog("Please enter a valid email address.", "Validation Error");
        return false;
      }

      if (password.length == 0) {
        CustomJOptionPane.showErrorMessageDialog("Password is required.", "Validation Error");
        return false;
      }

      if (password.length < 8) {
        CustomJOptionPane.showErrorMessageDialog("Password must be at least 8 characters long.", "Validation Error");
        return false;
      }

      boolean hasUpper = false, hasLower = false, hasDigit = false;
      for (char c : password) {
        if (Character.isUpperCase(c)) hasUpper = true;
        else if (Character.isLowerCase(c)) hasLower = true;
        else if (Character.isDigit(c)) hasDigit = true;
      }

      if (!hasUpper || !hasLower || !hasDigit) {
        CustomJOptionPane.showErrorMessageDialog("Password must contain at least one uppercase letter, one lowercase letter, and one number.", "Validation Error");
        return false;
      }

      if (!java.util.Arrays.equals(password, passwordConfirmation)) {
        CustomJOptionPane.showErrorMessageDialog("Passwords do not match.", "Validation Error");
        return false;
      }

      if (!registrationController.getAgreementCheckbox().isSelected()) {
        CustomJOptionPane.showErrorMessageDialog("You must agree to the terms before registering.", "Validation Error");
        return false;
      }

      return true;
    } finally {
      java.util.Arrays.fill(password, '\0');
      java.util.Arrays.fill(passwordConfirmation, '\0');
    }
  }
}
