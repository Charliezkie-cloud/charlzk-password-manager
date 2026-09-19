package org.charlzk.Components.Tabs;

import org.charlzk.Components.Layouts.FoldersTabLayout;

import javax.swing.*;
import java.awt.*;

public class FoldersTab extends JPanel {
  public FoldersTab() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
    add(new FoldersTabLayout(), BorderLayout.CENTER);
  }
}
