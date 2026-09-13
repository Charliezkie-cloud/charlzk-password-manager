package org.charlzk.Events.MainEvents;

import org.charlzk.Controllers.MainController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PasswordsTableSelectionListener implements ListSelectionListener {
  private final MainController mainController;

  public PasswordsTableSelectionListener(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {
    if (e.getValueIsAdjusting()) return;

    int selectedRow = mainController.getPasswordsTable().getSelectedRow();
    if (selectedRow == -1) return;
  }
}
