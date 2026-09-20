package org.charlzk.Views.AuthViews;

import org.charlzk.Components.Layouts.FoldersTabLayout;
import org.charlzk.Components.Layouts.MainTabLayout;
import org.charlzk.Components.Layouts.PasswordGeneratorLayout;
import org.charlzk.Components.Tabs.FoldersTab;
import org.charlzk.Components.Tabs.MainTab;
import org.charlzk.Components.Tabs.PasswordGeneratorTab;
import org.charlzk.Components.Tabs.SettingsTab;
import org.charlzk.Controllers.FoldersController;
import org.charlzk.Controllers.MainController;
import org.charlzk.Controllers.PasswordGeneratorController;

import javax.swing.*;
import java.awt.*;

import static org.charlzk.Services.ApplicationServices.setGlobalFont;

public class MainView extends JFrame {
  public MainView() {
    setGlobalFont(new Font("Segoe UI", Font.PLAIN, 14));

    setTitle("Charlzk Password Manager");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setSize(1250, 800);
    setMinimumSize(new Dimension(900, 600));

    // ========== START OF COMPONENTS ==========

    JPanel mainContent = new JPanel();
    mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.X_AXIS));
    mainContent.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    JTabbedPane mainTabbedPane = new JTabbedPane();
    mainTabbedPane.add("My Passwords", new MainTab());
    mainTabbedPane.add("My Folders", new FoldersTab());
    mainTabbedPane.add("Password Generator", new PasswordGeneratorTab());
    mainTabbedPane.add("Settings", new SettingsTab());

    mainContent.add(mainTabbedPane);

    // ========== END OF COMPONENTS ==========

    MainController mainController = new MainController(
            this,

            MainTabLayout.foldersTableModel,
            MainTabLayout.passwordsTableModel,

            MainTabLayout.foldersTable,
            MainTabLayout.passwordsTable,

            MainTabLayout.passwordsTableContextMenu,
            MainTabLayout.updatePasswordMenuItem,
            MainTabLayout.deletePasswordMenuItem,
            MainTabLayout.foldersTableContextMenu,
            MainTabLayout.updateFolderMenuItem,
            MainTabLayout.deleteFolderMenuItem,

            MainTabLayout.searchField,

            MainTabLayout.searchOptionComboBox,
            MainTabLayout.searchOptionComboBoxModel,

            MainTabLayout.searchButton,
            MainTabLayout.addPasswordButton,
            MainTabLayout.addFolderButton
    );

    FoldersController foldersController = new FoldersController(
            mainController,

            FoldersTabLayout.foldersTableModel,
            FoldersTabLayout.foldersTable,

            FoldersTabLayout.foldersTableContextMenu,
            FoldersTabLayout.updateFolderMenuItem,
            FoldersTabLayout.deleteFolderMenuItem,

            FoldersTabLayout.searchField,

            FoldersTabLayout.searchButton,
            FoldersTabLayout.addFolderButton
    );

    mainController.setFoldersController(foldersController);

    PasswordGeneratorController passwordGeneratorController = new PasswordGeneratorController(
            mainController,

            PasswordGeneratorLayout.generatedPasswordField,

            PasswordGeneratorLayout.passwordLengthSpinner,

            PasswordGeneratorLayout.uppercaseCheckbox,
            PasswordGeneratorLayout.lowercaseCheckbox,
            PasswordGeneratorLayout.numbersCheckbox,
            PasswordGeneratorLayout.symbolsCheckbox,

            PasswordGeneratorLayout.generateButton,
            PasswordGeneratorLayout.saveButton
    );

    mainController.setPasswordGeneratorController(passwordGeneratorController);

    add(mainContent);
    pack();
    setLocationRelativeTo(null);
  }
}
