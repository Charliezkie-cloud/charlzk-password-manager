package org.charlzk.Events.MainEvents;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Controllers.MainController;
import org.charlzk.Models.PasswordEntry;
import org.charlzk.Session.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

public class PasswordsTableMouseAdapter extends MouseAdapter {
  // Controller
  private final MainController mainController;

  public PasswordsTableMouseAdapter(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    super.mousePressed(e);

    maybeShowPopup(e);
    if (e.getClickCount() == 2) {
      int selectedRow = mainController.getPasswordsTable().getSelectedRow();
      if (selectedRow == -1) return;

      Object selectedRowValue = mainController.getPasswordsTableModel().getValueAt(selectedRow, 0);

      try {
        int parsedRowValue = Integer.parseInt(String.valueOf(selectedRowValue));
        PasswordEntry passwordEntry = SessionManager.getInstance().getUserPasswordEntries().get(parsedRowValue);
        openUrl(passwordEntry.getUrl());
      } catch (NumberFormatException ex) {
        CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
      }
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    super.mouseReleased(e);
    maybeShowPopup(e);
  }

  private void maybeShowPopup(MouseEvent e) {
    JTable passwordsTable = mainController.getPasswordsTable();
    JPopupMenu passwordsTableContextMenu = mainController.getPasswordsTableContextMenu();

    if (e.isPopupTrigger()) {
      int row = mainController.getPasswordsTable().rowAtPoint(e.getPoint());
      if (row >= 0 && row < passwordsTable.getRowCount()) {
        passwordsTable.setRowSelectionInterval(row, row);
        passwordsTableContextMenu.show(e.getComponent(), e.getX(), e.getY());
      }
    }
  }

  private void openUrl(String url) {
    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
      try {
        Desktop.getDesktop().browse(new URI(url));
      } catch (Exception e) {
        CustomJOptionPane.showErrorMessageDialog(e.getMessage(), "Application Error");
      }
    } else {
      CustomJOptionPane.showErrorMessageDialog("Desktop browsing not supported on this system.", "Application Error");
    }
  }
}
