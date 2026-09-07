package org.charlzk.Components;

import javax.swing.*;
import java.awt.*;

public class CustomJOptionPane extends JOptionPane {
  public static void showErrorMessageDialog(Component parentComponent, String message, String title) {
    JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.ERROR_MESSAGE);
  }

  public static void showErrorMessageDialog(String message, String title) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
  }
}
