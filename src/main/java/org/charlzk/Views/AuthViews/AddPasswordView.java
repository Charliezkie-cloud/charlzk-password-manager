package org.charlzk.Views.AuthViews;

import org.charlzk.Components.Layouts.AddPasswordLayout;
import org.charlzk.Controllers.AddPasswordController;
import org.charlzk.Controllers.MainController;

import javax.swing.*;
import java.awt.*;

import static org.charlzk.Services.ApplicationServices.setGlobalFont;

public class AddPasswordView extends JFrame {
  public AddPasswordView(MainController mainController) {
    setGlobalFont(new Font("Segoe UI", Font.PLAIN, 14));

    setTitle("Charlzk Password Manager - New Password");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);

    // ========== START OF COMPONENTS ==========

    JPanel mainContent = new JPanel();
    mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.X_AXIS));
    mainContent.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    mainContent.add(new AddPasswordLayout());

    // ========== END OF COMPONENTS ==========

    new AddPasswordController(
            this,

            mainController,

            AddPasswordLayout.titleField,
            AddPasswordLayout.usernameField,
            AddPasswordLayout.urlField,
            AddPasswordLayout.passwordField,

            AddPasswordLayout.noteTextArea,

            AddPasswordLayout.folderComboBox,
            AddPasswordLayout.folderComboBoxModel,

            AddPasswordLayout.cancelButton,
            AddPasswordLayout.saveButton
    );

    add(mainContent);
    pack();
    setLocationRelativeTo(null);
  }
}
