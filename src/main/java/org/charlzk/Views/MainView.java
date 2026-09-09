package org.charlzk.Views;

import org.charlzk.Components.Tabs.FoldersTab;
import org.charlzk.Components.Tabs.MainTab;
import org.charlzk.Components.Tabs.PasswordGeneratorTab;
import org.charlzk.Components.Tabs.SettingsTab;
import org.charlzk.Controllers.MainController;
import org.charlzk.Events.MainEvents.MainWindowListener;

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

    MainController mainController = new MainController();

    addWindowListener(new MainWindowListener(mainController));
    add(mainContent);
    pack();
    setLocationRelativeTo(null);
  }
}
