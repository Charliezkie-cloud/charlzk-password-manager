package org.charlzk.Components.Layouts;

import javax.swing.*;
import java.awt.*;

public class MainTabLayout extends JPanel {
  // Fields
  private static final JTextField searchField = new JTextField();

  // Buttons
  private static final JButton searchButton = new JButton("Search");

  public MainTabLayout() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    // ========== SEARCH PANEL ==========
    JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
    searchPanel.add(new JLabel("Search"), BorderLayout.NORTH);
    searchPanel.add(searchField, BorderLayout.CENTER);
    searchPanel.add(searchButton, BorderLayout.EAST);

    add(searchPanel, BorderLayout.NORTH);
  }
}
