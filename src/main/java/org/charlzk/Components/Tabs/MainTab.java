package org.charlzk.Components.Tabs;

import javax.swing.*;
import java.awt.*;

public class MainTab extends JPanel {
  public MainTab() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    JPanel centerPanel = new JPanel();

    // ===== Center Panel =====
    centerPanel.add(new JLabel("My passwords tab here..."));

    add(centerPanel, BorderLayout.CENTER);
  }
}
