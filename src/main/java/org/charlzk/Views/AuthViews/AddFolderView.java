package org.charlzk.Views.AuthViews;

import org.charlzk.Components.Layouts.AddFolderLayout;
import org.charlzk.Controllers.AddFolderController;
import org.charlzk.Controllers.MainController;

import javax.swing.*;
import java.awt.*;

import static org.charlzk.Services.ApplicationServices.setGlobalFont;

public class AddFolderView extends JFrame {
  public AddFolderView(MainController mainController) {
    setGlobalFont(new Font("Segoe UI", Font.PLAIN, 14));

    setTitle("Charlzk Password Manager - New Folder");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setResizable(false);

    // ========== START OF COMPONENTS ==========

    JPanel mainContent = new JPanel();
    mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.X_AXIS));
    mainContent.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    mainContent.add(new AddFolderLayout());

    new AddFolderController(
            this,

            mainController,

            AddFolderLayout.folderNameField,

            AddFolderLayout.cancelButton,
            AddFolderLayout.saveButton
    );

    // ========== END OF COMPONENTS ==========

    add(mainContent);
    pack();
    setLocationRelativeTo(null);
  }
}
