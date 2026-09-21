package org.charlzk.Components.Tabs;

import org.charlzk.Components.Layouts.SettingsTabLayout;

import javax.swing.*;
import java.awt.*;

public class SettingsTab extends JPanel {
  public SettingsTab() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    // ========== Center Panel ==========
    add(new SettingsTabLayout(), BorderLayout.CENTER);
  }
}
