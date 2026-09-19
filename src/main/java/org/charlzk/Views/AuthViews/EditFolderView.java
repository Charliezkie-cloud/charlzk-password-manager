package org.charlzk.Views.AuthViews;

import org.charlzk.Components.Layouts.EditFolderLayout;
import org.charlzk.Controllers.EditFolderController;
import org.charlzk.Controllers.MainController;

import javax.swing.*;
import java.awt.*;

import static org.charlzk.Services.ApplicationServices.setGlobalFont;

public class EditFolderView extends JFrame {
  public EditFolderView(MainController mainController) {
    setGlobalFont(new Font("Segoe UI", Font.PLAIN, 14));

    setTitle("Charlzk Password Manager - New Folder");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);

    // ========== START OF COMPONENTS ==========

    JPanel mainContent = new JPanel();
    mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.X_AXIS));
    mainContent.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    mainContent.add(new EditFolderLayout());

    new EditFolderController(
            this,

            mainController,

            EditFolderLayout.folderNameField,

            EditFolderLayout.cancelButton,
            EditFolderLayout.saveButton
    );

    // ========== END OF COMPONENTS ==========

    add(mainContent);
    pack();
    setLocationRelativeTo(null);
  }
}
