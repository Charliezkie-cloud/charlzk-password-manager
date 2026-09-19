package org.charlzk.Events.MainEvents;

import org.charlzk.Controllers.MainController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FoldersTableMouseAdapter extends MouseAdapter {
  private final MainController mainController;

  public FoldersTableMouseAdapter(MainController mainController) {
    this.mainController = mainController;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    super.mousePressed(e);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    super.mouseReleased(e);
    maybeShowPopup(e);
  }

  private void maybeShowPopup(MouseEvent e) {
    JTable foldersTable = mainController.getFoldersTable();
    JPopupMenu foldersTableContextMenu = mainController.getFoldersTableContextMenu();

    if (e.isPopupTrigger()) {
      int row = foldersTable.rowAtPoint(e.getPoint());
      if (row >= 0 && row < foldersTable.getRowCount()) {
        foldersTable.setRowSelectionInterval(row, row);
        foldersTableContextMenu.show(e.getComponent(), e.getX(), e.getY());
      }
    }
  }
}
