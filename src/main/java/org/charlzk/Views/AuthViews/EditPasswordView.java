package org.charlzk.Views.AuthViews;

import org.charlzk.Components.Layouts.EditPasswordLayout;
import org.charlzk.Controllers.EditPasswordController;
import org.charlzk.Controllers.MainController;

import javax.swing.*;
import java.awt.*;

import static org.charlzk.Services.ApplicationServices.setGlobalFont;

public class EditPasswordView extends JFrame {
  public EditPasswordView(MainController mainController) {
    setGlobalFont(new Font("Segoe UI", Font.PLAIN, 14));

    setTitle("Charlzk Password Manager - Edit Password");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);

    // ========== START OF COMPONENTS ==========

    JPanel mainContent = new JPanel();
    mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.X_AXIS));
    mainContent.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    mainContent.add(new EditPasswordLayout());

    // ========== END OF COMPONENTS ==========

    new EditPasswordController(
            this,

            mainController,

            EditPasswordLayout.titleField,
            EditPasswordLayout.usernameField,
            EditPasswordLayout.urlField,
            EditPasswordLayout.passwordField,

            EditPasswordLayout.noteTextArea,

            EditPasswordLayout.folderComboBoxModel,
            EditPasswordLayout.folderComboBox,

            EditPasswordLayout.cancelButton,
            EditPasswordLayout.saveButton
    );

    add(mainContent);
    pack();
    setLocationRelativeTo(null);
  }
}
