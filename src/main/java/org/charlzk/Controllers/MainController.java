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

  // Controllers
  private FoldersController foldersController;
  private PasswordGeneratorController passwordGeneratorController;

  // Table data model
  private final DefaultTableModel foldersTableModel;
  private final DefaultTableModel passwordsTableModel;

  // Context menu and menu items
  private final JPopupMenu passwordsTableContextMenu;
  private final JMenuItem updatePasswordMenuItem;
  private final JMenuItem deletePasswordMenuItem;
  private final JPopupMenu foldersTableContextMenu;
  private final JMenuItem updateFolderMenuItem;
  private final JMenuItem deleteFolderMenuItem;

  // Tables
  private final JTable foldersTable;
  private final JTable passwordsTable;

  // Fields
  private final JTextField searchField;

  // ComboBox
  private final JComboBox<String> searchOptionComboBox;
  private final DefaultComboBoxModel<String> searchOptionComboBoxModel;

  // Buttons
  private final JButton searchButton;
  private final JButton addPasswordButton;
  private final JButton addFolderButton;

  // Events
  private final MainWindowListener mainWindowListener;
  private final AddPasswordButtonOnAction addPasswordButtonOnAction;
  private final FoldersTableSelectionListener foldersTableSelectionListener;
  private final FoldersTableMouseAdapter foldersTableMouseAdapter;
  private final EditFolderMenuItemOnAction editFolderMenuItemOnAction;
  private final DeleteFolderMenuItemOnAction deleteFolderMenuItemOnAction;
  private final PasswordsTableSelectionListener passwordsTableSelectionListener;
  private final PasswordsTableMouseAdapter passwordsTableMouseAdapter;
  private final UpdatePasswordMenuItemOnAction updatePasswordMenuItemOnAction;
  private final DeletePasswordMenuItemOnAction deletePasswordMenuItemOnAction;
  private final AddFolderButtonOnAction addFolderButtonOnAction;
  private final SearchButtonOnAction searchButtonOnAction;

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
          JPopupMenu foldersTableContextMenu,
          JMenuItem updateFolderMenuItem,
          JMenuItem deleteFolderMenuItem,

          // Fields
          JTextField searchField,

          // ComboBox
          JComboBox<String> searchOptionComboBox,
          DefaultComboBoxModel<String> searchOptionComboBoxModel,

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
    this.foldersTableContextMenu = foldersTableContextMenu;
    this.updateFolderMenuItem = updateFolderMenuItem;
    this.deleteFolderMenuItem = deleteFolderMenuItem;

    this.searchField = searchField;

    this.searchOptionComboBox = searchOptionComboBox;
    this.searchOptionComboBoxModel = searchOptionComboBoxModel;

    this.searchButton = searchButton;
    this.addPasswordButton = addPasswordButton;
    this.addFolderButton = addFolderButton;

    this.mainWindowListener = new MainWindowListener(this);
    this.addPasswordButtonOnAction = new AddPasswordButtonOnAction(this);
    this.foldersTableSelectionListener = new FoldersTableSelectionListener(this);
    this.foldersTableMouseAdapter = new FoldersTableMouseAdapter(this);
    this.editFolderMenuItemOnAction = new EditFolderMenuItemOnAction(this);
    this.deleteFolderMenuItemOnAction = new DeleteFolderMenuItemOnAction(this);
    this.passwordsTableSelectionListener = new PasswordsTableSelectionListener(this);
    this.passwordsTableMouseAdapter = new PasswordsTableMouseAdapter(this);
    this.updatePasswordMenuItemOnAction = new UpdatePasswordMenuItemOnAction(this);
    this.deletePasswordMenuItemOnAction = new DeletePasswordMenuItemOnAction(this);
    this.addFolderButtonOnAction = new AddFolderButtonOnAction(this);
    this.searchButtonOnAction = new SearchButtonOnAction(this);

    searchOptionComboBoxModel.addElement("Folder");
    searchOptionComboBoxModel.addElement("Password");

    mainView.addWindowListener(mainWindowListener);
    addPasswordButton.addActionListener(addPasswordButtonOnAction);
    foldersTable.getSelectionModel().addListSelectionListener(foldersTableSelectionListener);
    foldersTable.addMouseListener(foldersTableMouseAdapter);
    updateFolderMenuItem.addActionListener(editFolderMenuItemOnAction);
    deleteFolderMenuItem.addActionListener(deleteFolderMenuItemOnAction);
    passwordsTable.getSelectionModel().addListSelectionListener(passwordsTableSelectionListener);
    passwordsTable.addMouseListener(passwordsTableMouseAdapter);
    updatePasswordMenuItem.addActionListener(updatePasswordMenuItemOnAction);
    deletePasswordMenuItem.addActionListener(deletePasswordMenuItemOnAction);
    addFolderButton.addActionListener(addFolderButtonOnAction);
    searchButton.addActionListener(searchButtonOnAction);
  }

  public void close() {
    mainView.removeWindowListener(mainWindowListener);
    addPasswordButton.removeActionListener(addPasswordButtonOnAction);
    foldersTable.getSelectionModel().removeListSelectionListener(foldersTableSelectionListener);
    foldersTable.removeMouseListener(foldersTableMouseAdapter);
    updateFolderMenuItem.removeActionListener(editFolderMenuItemOnAction);
    deleteFolderMenuItem.removeActionListener(deleteFolderMenuItemOnAction);
    passwordsTable.getSelectionModel().removeListSelectionListener(passwordsTableSelectionListener);
    passwordsTable.removeMouseListener(passwordsTableMouseAdapter);
    updatePasswordMenuItem.removeActionListener(updatePasswordMenuItemOnAction);
    deletePasswordMenuItem.removeActionListener(deletePasswordMenuItemOnAction);
    addFolderButton.removeActionListener(addFolderButtonOnAction);
    searchButton.removeActionListener(searchButtonOnAction);
    if (foldersController != null) {
      foldersController.close();
    }
    if (passwordGeneratorController != null) {
      passwordGeneratorController.close();
    }
  }

  // Getters
  public DefaultTableModel getFoldersTableModel() { return foldersTableModel; }
  public JTable getFoldersTable() { return foldersTable; }
  public DefaultTableModel getPasswordsTableModel() { return passwordsTableModel; }
  public JTable getPasswordsTable() { return passwordsTable; }
  public JPopupMenu getPasswordsTableContextMenu() { return passwordsTableContextMenu; }
  public JPopupMenu getFoldersTableContextMenu() { return foldersTableContextMenu; }
  public JTextField getSearchField() { return searchField; }
  public JComboBox<String> getSearchOptionComboBox() { return searchOptionComboBox; }
  public DefaultComboBoxModel<String> getSearchOptionComboBoxModel() { return searchOptionComboBoxModel; }
  public FoldersController getFoldersController() { return foldersController; }

  // Setters
  public void setFoldersController(FoldersController foldersController) { this.foldersController = foldersController; }
  public void setPasswordGeneratorController(PasswordGeneratorController passwordGeneratorController) { this.passwordGeneratorController = passwordGeneratorController; }

  // Utils
  public void addFolderTableRow(Folder folder) {
    Object[] item = new Object[] {
            folder.getFolderId(),
            folder.getName(),
            folder.getFormattedCreatedAt()
    };

    foldersTableModel.addRow(item);
    if (foldersController != null) {
      foldersController.addFolderTableRow(folder);
    }
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
    if (foldersController != null) {
      foldersController.getFoldersTableModel().setRowCount(0);
    }

    HashMap<Integer, Folder> userFolders = SessionManager.getInstance().getUserFolders();
    HashMap<Integer, PasswordEntry> userPasswordEntries = SessionManager.getInstance().getUserPasswordEntries();

    for (Folder item : userFolders.values())
      addFolderTableRow(item);
    for (PasswordEntry item : userPasswordEntries.values())
      addPasswordTableRow(item);
  }
}
