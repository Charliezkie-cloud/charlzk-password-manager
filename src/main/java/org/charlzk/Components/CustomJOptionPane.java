package org.charlzk.Components;

import javax.swing.*;

public class CustomJOptionPane extends JOptionPane {
  public static void showErrorMessageDialog(String message, String title) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
  }

  public static void showSuccessMessageDialog(String message, String title) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
  }
}
