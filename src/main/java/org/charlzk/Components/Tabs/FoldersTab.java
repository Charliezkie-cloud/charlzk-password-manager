package org.charlzk.Components.Tabs;

import javax.swing.*;
import java.awt.*;

public class FoldersTab extends JPanel {
  public FoldersTab() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    // ========== Center Panel ==========
    JPanel centerPanel = new JPanel();

    centerPanel.add(new JLabel("Folders tab here..."));

    add(centerPanel, BorderLayout.CENTER);
  }
}
