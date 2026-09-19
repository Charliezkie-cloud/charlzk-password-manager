package org.charlzk.Components.Tabs;

import org.charlzk.Components.Layouts.MainTabLayout;

import javax.swing.*;
import java.awt.*;

public class MainTab extends JPanel {
  public MainTab() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    add(new MainTabLayout(), BorderLayout.CENTER);
  }
}
