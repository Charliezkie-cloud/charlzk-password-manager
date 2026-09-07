package org.charlzk;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Database.DatabaseInitializer;
import org.charlzk.Views.LoginView;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    FlatMacLightLaf.setup();

    try {
      DatabaseInitializer.initialize();
    } catch (SQLException ex) {
      ex.printStackTrace();
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }

    SwingUtilities.invokeLater(() -> {
      LoginView loginView = new LoginView();
      loginView.setVisible(true);
    });
  }
}
