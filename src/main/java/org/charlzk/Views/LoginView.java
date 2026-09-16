package org.charlzk.Views;

import org.charlzk.Components.Layouts.LoginLayout;
import org.charlzk.Controllers.LoginController;

import javax.swing.*;
import java.awt.*;

import static org.charlzk.Services.ApplicationServices.setGlobalFont;

public class LoginView extends JFrame {
  public LoginView() {
    setGlobalFont(new Font("Segoe UI", Font.PLAIN, 14));

    setTitle("Charlzk Password Manager - Login");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);

    // ========== START OF COMPONENTS ==========

    JPanel mainContent = new JPanel();
    mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.X_AXIS));
    mainContent.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    mainContent.add(new LoginLayout());

    // ========== END OF COMPONENTS ==========

    new LoginController(
            // View
            this,

            // Fields
            LoginLayout.emailField,
            LoginLayout.passwordField,

            // Buttons
            LoginLayout.registerPane,
            LoginLayout.loginButton
    );
    add(mainContent);
    pack();
    setLocationRelativeTo(null);
  }
}
