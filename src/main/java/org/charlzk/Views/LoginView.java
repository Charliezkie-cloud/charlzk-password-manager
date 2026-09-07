package org.charlzk.Views;

import org.charlzk.Components.Layouts.LoginLayout;
import org.charlzk.Controllers.LoginController;
import org.charlzk.Events.LoginEvents.LoginWindowListener;

import javax.swing.*;
import java.awt.*;

import static org.charlzk.Services.ApplicationService.setGlobalFont;

public class LoginView extends JFrame {
  public LoginView() {
    setGlobalFont(new Font("Segoe UI", Font.PLAIN, 14));

    setTitle("Charlzk Password Manager - Login");
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    // ========== START OF COMPONENTS ==========

    JPanel mainContent = new JPanel();
    mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.X_AXIS));
    mainContent.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    mainContent.add(new LoginLayout());

    // ========== END OF COMPONENTS ==========

    LoginController loginController = new LoginController(
            // View
            this,

            // Fields
            LoginLayout.emailField,
            LoginLayout.passwordField,

            // Buttons
            LoginLayout.registerButton,
            LoginLayout.loginButton
    );

    addWindowListener(new LoginWindowListener(loginController));
    add(mainContent);
    pack();
    setLocationRelativeTo(null);
  }
}
