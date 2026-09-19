package org.charlzk;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Database.DatabaseInitializer;
import org.charlzk.Views.NoAuthViews.LoginView;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    FlatMacDarkLaf.setup();

    try {
      DatabaseInitializer.initialize();
    } catch (SQLException | IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }

    SwingUtilities.invokeLater(() -> {
      LoginView loginView = new LoginView();
      loginView.setVisible(true);
    });
  }
}
