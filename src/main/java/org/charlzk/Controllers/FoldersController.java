package org.charlzk.Controllers;

import org.charlzk.Events.FoldersEvents.AddFolderButtonOnAction;
import org.charlzk.Events.FoldersEvents.DeleteFolderMenuItemOnAction;
import org.charlzk.Events.FoldersEvents.FoldersTableMouseAdapter;
import org.charlzk.Events.FoldersEvents.SearchButtonOnAction;
import org.charlzk.Events.FoldersEvents.UpdateFolderMenuItemOnAction;
import org.charlzk.Models.Folder;
import org.charlzk.Session.SessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class FoldersController {
  // Controller
  private final MainController mainController;

  // Table data model and tables
  private final DefaultTableModel foldersTableModel;
  private final JTable foldersTable;

  // Context menu and menu items
  private final JPopupMenu foldersTableContextMenu;
  private final JMenuItem updateFolderMenuItem;
  private final JMenuItem deleteFolderMenuItem;

  // Fields
  private final JTextField searchField;

  // Buttons
  private final JButton searchButton;
  private final JButton addFolderButton;

  // Events
  private final SearchButtonOnAction searchButtonOnAction;
  private final AddFolderButtonOnAction addFolderButtonOnAction;
  private final UpdateFolderMenuItemOnAction updateFolderMenuItemOnAction;
  private final DeleteFolderMenuItemOnAction deleteFolderMenuItemOnAction;
  private final FoldersTableMouseAdapter foldersTableMouseAdapter;

  public FoldersController(
          MainController mainController,

          // Table data model and tables
          DefaultTableModel foldersTableModel,
          JTable foldersTable,

          // Context menu and menu items
          JPopupMenu foldersTableContextMenu,
          JMenuItem updateFolderMenuItem,
          JMenuItem deleteFolderMenuItem,

          // Fields
          JTextField searchField,

          // Buttons
          JButton searchButton,
          JButton addFolderButton
  ) {
    this.mainController = mainController;

    this.foldersTableModel = foldersTableModel;
    this.foldersTable = foldersTable;

    this.foldersTableContextMenu = foldersTableContextMenu;
    this.updateFolderMenuItem = updateFolderMenuItem;
    this.deleteFolderMenuItem = deleteFolderMenuItem;

    this.searchField = searchField;

    this.searchButton = searchButton;
    this.addFolderButton = addFolderButton;

    this.searchButtonOnAction = new SearchButtonOnAction(this);
    this.addFolderButtonOnAction = new AddFolderButtonOnAction(this);
    this.updateFolderMenuItemOnAction = new UpdateFolderMenuItemOnAction(this);
    this.deleteFolderMenuItemOnAction = new DeleteFolderMenuItemOnAction(this);
    this.foldersTableMouseAdapter = new FoldersTableMouseAdapter(this);

    searchButton.addActionListener(searchButtonOnAction);
    searchField.addActionListener(searchButtonOnAction);
    addFolderButton.addActionListener(addFolderButtonOnAction);
    updateFolderMenuItem.addActionListener(updateFolderMenuItemOnAction);
    deleteFolderMenuItem.addActionListener(deleteFolderMenuItemOnAction);
    foldersTable.addMouseListener(foldersTableMouseAdapter);
  }

  public void close() {
    searchButton.removeActionListener(searchButtonOnAction);
    searchField.removeActionListener(searchButtonOnAction);
    addFolderButton.removeActionListener(addFolderButtonOnAction);
    updateFolderMenuItem.removeActionListener(updateFolderMenuItemOnAction);
    deleteFolderMenuItem.removeActionListener(deleteFolderMenuItemOnAction);
    foldersTable.removeMouseListener(foldersTableMouseAdapter);
  }

  // Getters
  public MainController getMainController() { return mainController; }
  public DefaultTableModel getFoldersTableModel() { return foldersTableModel; }
  public JTable getFoldersTable() { return foldersTable; }
  public JPopupMenu getFoldersTableContextMenu() { return foldersTableContextMenu; }
  public JMenuItem getUpdateFolderMenuItem() { return updateFolderMenuItem; }
  public JMenuItem getDeleteFolderMenuItem() { return deleteFolderMenuItem; }
  public JTextField getSearchField() { return searchField; }
  public JButton getSearchButton() { return searchButton; }
  public JButton getAddFolderButton() { return addFolderButton; }

  // Utils
  public void addFolderTableRow(Folder folder) {
    Object[] item = new Object[] {
            folder.getFolderId(),
            folder.getName(),
            folder.getFormattedCreatedAt()
    };

    foldersTableModel.addRow(item);
  }

  public void refreshTable() {
    foldersTableModel.setRowCount(0);

    HashMap<Integer, Folder> userFolders = SessionManager.getInstance().getUserFolders();
    if (userFolders != null) {
      for (Folder item : userFolders.values())
        addFolderTableRow(item);
    }
  }
}
