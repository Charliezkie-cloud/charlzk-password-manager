package org.charlzk.Views;

import org.charlzk.Components.Layouts.RegistrationLayout;
import org.charlzk.Controllers.RegistrationController;

import javax.swing.*;
import java.awt.*;

import static org.charlzk.Services.ApplicationService.setGlobalFont;

public class RegistrationView extends JFrame {
  public RegistrationView() {
    setGlobalFont(new Font("Segoe UI", Font.PLAIN, 14));

    setTitle("Charlzk Password Manager - Register");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);

    // ========== START OF COMPONENTS ==========

    JPanel mainContent = new JPanel();
    mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.X_AXIS));
    mainContent.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    mainContent.add(new RegistrationLayout());

    // ========== END OF COMPONENTS ==========

    new RegistrationController(
            // Legal components
            RegistrationLayout.termsOfUseButton
    );

    add(mainContent);
    pack();
    setLocationRelativeTo(null);
  }
}
