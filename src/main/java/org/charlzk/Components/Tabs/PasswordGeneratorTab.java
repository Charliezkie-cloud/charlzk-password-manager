package org.charlzk.Components.Tabs;

import org.charlzk.Components.Layouts.PasswordGeneratorLayout;

import javax.swing.*;
import java.awt.*;

public class PasswordGeneratorTab extends JPanel {
  public PasswordGeneratorTab() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    add(new PasswordGeneratorLayout(), BorderLayout.CENTER);
  }
}
