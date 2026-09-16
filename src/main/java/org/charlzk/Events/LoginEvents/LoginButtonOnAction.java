package org.charlzk.Events.LoginEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.LoginController;
import org.charlzk.Services.AuthServices;
import org.charlzk.Views.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class LoginButtonOnAction implements ActionListener {
  // Controller
  private final LoginController loginController;

  public LoginButtonOnAction(LoginController loginController) {
    this.loginController = loginController;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String email = loginController.getEmailField().getText().trim();
    char[] passwordChar = loginController.getPasswordField().getPassword();
    String password = new String(passwordChar);

    if (!validateForm(email, password))
      return;

    try {
      if (!AuthServices.login(email, password))
        return;

      loginController.close();
      loginController.getLoginView().dispose();
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