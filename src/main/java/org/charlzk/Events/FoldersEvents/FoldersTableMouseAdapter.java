package org.charlzk.Events.FoldersEvents;

import org.charlzk.Controllers.FoldersController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FoldersTableMouseAdapter extends MouseAdapter {
  private final FoldersController foldersController;

  public FoldersTableMouseAdapter(FoldersController foldersController) {
    this.foldersController = foldersController;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    super.mouseReleased(e);
    maybeShowPopup(e);
  }

  private void maybeShowPopup(MouseEvent e) {
    JTable foldersTable = foldersController.getFoldersTable();
    JPopupMenu foldersTableContextMenu = foldersController.getFoldersTableContextMenu();

    if (e.isPopupTrigger()) {
      int row = foldersTable.rowAtPoint(e.getPoint());
      if (row >= 0 && row < foldersTable.getRowCount()) {
        foldersTable.setRowSelectionInterval(row, row);
        foldersTableContextMenu.show(e.getComponent(), e.getX(), e.getY());
      }
    }
  }
}
