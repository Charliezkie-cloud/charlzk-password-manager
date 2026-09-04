package org.charlzk.Components;

import javax.swing.*;
import java.awt.*;

public class SettingsTab extends JPanel {
  public SettingsTab() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    // ========== Center Panel ==========
    JPanel centerPanel = new JPanel();

    centerPanel.add(new JLabel("Settings tab here..."));

    add(centerPanel, BorderLayout.CENTER);
  }
}
