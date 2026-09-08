package org.charlzk.Events.LoginEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Services.AuthServices;
import org.charlzk.Views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class LoginButtonOnAction implements ActionListener {
  // Views
  private final JFrame loginView;

  // Fields
  private final JTextField emailField;
  private final JPasswordField passwordField;

  public LoginButtonOnAction(
          // View
          JFrame loginView,

          // Fields
          JTextField emailField,
          JPasswordField passwordField
  ) {
    this.loginView = loginView;

    this.emailField = emailField;
    this.passwordField = passwordField;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String email = emailField.getText();
    char[] passwordChar = passwordField.getPassword();
    String password = new String(passwordChar);

    if (!validateForm(email, password))
      return;

    try {
      if (!AuthServices.login(email, password))
        return;

      loginView.dispose();
      MainView mainView = new MainView();
      mainView.setVisible(true);
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }


  private boolean validateForm(String email, String password) {
    if (email.isBlank()) {
      CustomJOptionPane.showErrorMessageDialog("Email cannot be empty.", "Validation Error");
      return false;
    }

    if (password.isBlank()) {
      CustomJOptionPane.showErrorMessageDialog("Password cannot be empty.", "Validation Error");
      return false;
    }

    return true;
  }
}