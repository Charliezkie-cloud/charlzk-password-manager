package org.charlzk;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import org.charlzk.Database.DatabaseInitializer;
import org.charlzk.Views.RegistrationView;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    FlatMacLightLaf.setup();

    try {
      DatabaseInitializer.initialize();
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(
              null,
              "An expected error occurred. Please check the logs.",
              "Application Error",
              JOptionPane.ERROR_MESSAGE
      );
    }

    SwingUtilities.invokeLater(() -> {
      RegistrationView registrationView = new RegistrationView();
      registrationView.setVisible(true);
    });
  }
}
