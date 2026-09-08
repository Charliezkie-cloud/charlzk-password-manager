package org.charlzk.Components.Tabs;

import javax.swing.*;
import java.awt.*;

public class PasswordGeneratorTab extends JPanel {
  public PasswordGeneratorTab() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    // ========== Center Panel ==========
    JPanel centerPanel = new JPanel();

    centerPanel.add(new JLabel("Password generator tab here..."));

    add(centerPanel, BorderLayout.CENTER);
  }
}
