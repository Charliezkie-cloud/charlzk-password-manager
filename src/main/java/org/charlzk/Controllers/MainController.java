package org.charlzk.Controllers;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Events.MainEvents.*;
import org.charlzk.Models.Folder;
import org.charlzk.Models.PasswordEntry;
import org.charlzk.Services.TimeServices;
import org.charlzk.Session.SessionManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class MainController {
  // View
  private final JFrame mainView;

  // Table data model
  private final DefaultTableModel foldersTableModel;
  private final DefaultTableModel passwordsTableModel;

  // Context menu and menu items
  private final JPopupMenu passwordsTableContextMenu;
  private final JMenuItem updatePasswordMenuItem;
  private final JMenuItem deletePasswordMenuItem;

  // Tables
  private final JTable foldersTable;
  private final JTable passwordsTable;

  // Fields
  private final JTextField searchField;

  // ComboBox
  private final JComboBox<String> searchOptionComboBox;

  // Buttons
  private final JButton searchButton;
  private final JButton addPasswordButton;
  private final JButton addFolderButton;

  // Events
  private final AddPasswordButtonOnAction addPasswordButtonOnAction;
  private final FoldersTableSelectionListener foldersTableSelectionListener;
  private final PasswordsTableSelectionListener passwordsTableSelectionListener;
  private final PasswordsTableMouseAdapter passwordsTableMouseAdapter;
  private final UpdatePasswordMenuItemOnAction updatePasswordMenuItemOnAction;
  private final DeletePasswordMenuItemOnAction deletePasswordMenuItemOnAction;
  private final MainWindowListener mainWindowListener;

  public MainController(
          // View
          JFrame mainView,

          // Table data model
          DefaultTableModel foldersTableModel,
          DefaultTableModel passwordsTableModel,

          // Tables
          JTable foldersTable,
          JTable passwordsTable,

          // Context menu and menu items
          JPopupMenu passwordTableContextMenu,
          JMenuItem updatePasswordMenuItem,
          JMenuItem deletePasswordMenuItem,

          // Fields
          JTextField searchField,

          // ComboBox
          JComboBox<String> searchOptionComboBox,

          // Buttons
          JButton searchButton,
          JButton addPasswordButton,
          JButton addFolderButton
  ) {
    this.mainView = mainView;

    this.foldersTableModel = foldersTableModel;
    this.passwordsTableModel = passwordsTableModel;

    this.foldersTable = foldersTable;
    this.passwordsTable = passwordsTable;

    this.passwordsTableContextMenu = passwordTableContextMenu;
    this.updatePasswordMenuItem = updatePasswordMenuItem;
    this.deletePasswordMenuItem = deletePasswordMenuItem;

    this.searchField = searchField;

    this.searchOptionComboBox = searchOptionComboBox;

    this.searchButton = searchButton;
    this.addPasswordButton = addPasswordButton;
    this.addFolderButton = addFolderButton;

    this.addPasswordButtonOnAction = new AddPasswordButtonOnAction(this);
    this.foldersTableSelectionListener = new FoldersTableSelectionListener(this);
    this.passwordsTableSelectionListener = new PasswordsTableSelectionListener(this);
    this.passwordsTableMouseAdapter = new PasswordsTableMouseAdapter(this);
    this.updatePasswordMenuItemOnAction = new UpdatePasswordMenuItemOnAction(this);
    this.deletePasswordMenuItemOnAction = new DeletePasswordMenuItemOnAction(this);
    this.mainWindowListener = new MainWindowListener(this);

    mainView.addWindowListener(mainWindowListener);
    addPasswordButton.addActionListener(addPasswordButtonOnAction);
    foldersTable.getSelectionModel().addListSelectionListener(foldersTableSelectionListener);
    passwordsTable.getSelectionModel().addListSelectionListener(passwordsTableSelectionListener);
    passwordsTable.addMouseListener(passwordsTableMouseAdapter);
    updatePasswordMenuItem.addActionListener(updatePasswordMenuItemOnAction);
    deletePasswordMenuItem.addActionListener(deletePasswordMenuItemOnAction);
  }

  public void close() {
    mainView.removeWindowListener(mainWindowListener);
    addPasswordButton.removeActionListener(addPasswordButtonOnAction);
    foldersTable.getSelectionModel().removeListSelectionListener(foldersTableSelectionListener);
    passwordsTable.getSelectionModel().removeListSelectionListener(passwordsTableSelectionListener);
    passwordsTable.removeMouseListener(passwordsTableMouseAdapter);
    updatePasswordMenuItem.removeActionListener(updatePasswordMenuItemOnAction);
    deletePasswordMenuItem.removeActionListener(deletePasswordMenuItemOnAction);
  }

  // Getters
  public DefaultTableModel getFoldersTableModel() { return foldersTableModel; }
  public JTable getFoldersTable() { return foldersTable; }
  public DefaultTableModel getPasswordsTableModel() { return passwordsTableModel; }
  public JTable getPasswordsTable() { return passwordsTable; }
  public JPopupMenu getPasswordsTableContextMenu() { return passwordsTableContextMenu; }

  // Utils
  public void addFolderTableRow(Folder folder) {
    Object[] item = new Object[] {
            folder.getFolderId(),
            folder.getName(),
            folder.getFormattedCreatedAt()
    };

    foldersTableModel.addRow(item);
  }

  public void addPasswordTableRow(PasswordEntry passwordEntry) {
    Object[] item = new Object[] {
            passwordEntry.getEntryId(),
            passwordEntry.getTitle(),
            passwordEntry.getUrl(),
            TimeServices.formatTimeMillis(passwordEntry.getCreatedAt()),
            TimeServices.formatTimeMillis(passwordEntry.getUpdatedAt())
    };

    int selectedFolderRowIndex = foldersTable.getSelectedRow();
    if (selectedFolderRowIndex == -1) return;

    Object selectedFolderRowValue = foldersTableModel.getValueAt(selectedFolderRowIndex, 0);
    try {
      int selectedFolderId = Integer.parseInt(String.valueOf(selectedFolderRowValue));
      if (passwordEntry.getFolderId() == selectedFolderId)
        passwordsTableModel.addRow(item);
    } catch (NumberFormatException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }

  public void refreshTables() {
    foldersTableModel.setRowCount(0);
    passwordsTableModel.setRowCount(0);

    HashMap<Integer, Folder> userFolders = SessionManager.getInstance().getUserFolders();
    HashMap<Integer, PasswordEntry> userPasswordEntries = SessionManager.getInstance().getUserPasswordEntries();

    for (Folder item : userFolders.values())
      addFolderTableRow(item);
    for (PasswordEntry item : userPasswordEntries.values())
      addPasswordTableRow(item);
  }
}
