package org.charlzk.Controllers;

import org.charlzk.Events.MainEvents.AddPasswordOnAction;
import org.charlzk.Events.MainEvents.FoldersTableSelectionListener;
import org.charlzk.Events.MainEvents.PasswordsTableSelectionListener;
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
  private final AddPasswordOnAction addPasswordOnAction;
  private final FoldersTableSelectionListener foldersTableSelectionListener;
  private final PasswordsTableSelectionListener passwordsTableSelectionListener;

  public MainController(
          // View
          JFrame mainView,

          // Table data model
          DefaultTableModel foldersTableModel,
          DefaultTableModel passwordsTableModel,

          // Tables
          JTable foldersTable,
          JTable passwordsTable,

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

    this.searchField = searchField;

    this.searchOptionComboBox = searchOptionComboBox;

    this.searchButton = searchButton;
    this.addPasswordButton = addPasswordButton;
    this.addFolderButton = addFolderButton;

    this.addPasswordOnAction = new AddPasswordOnAction(this);
    this.foldersTableSelectionListener = new FoldersTableSelectionListener(this);
    this.passwordsTableSelectionListener = new PasswordsTableSelectionListener(this);

    addPasswordButton.addActionListener(addPasswordOnAction);
    foldersTable.getSelectionModel().addListSelectionListener(foldersTableSelectionListener);
    passwordsTable.getSelectionModel().addListSelectionListener(passwordsTableSelectionListener);
  }

  public void close() {
    addPasswordButton.removeActionListener(addPasswordOnAction);
    foldersTable.getSelectionModel().removeListSelectionListener(foldersTableSelectionListener);
    passwordsTable.getSelectionModel().removeListSelectionListener(passwordsTableSelectionListener);
  }

  // Getters
  public DefaultTableModel getFoldersTableModel() { return foldersTableModel; }
  public JTable getFoldersTable() { return foldersTable; }
  public DefaultTableModel getPasswordsTableModel() { return passwordsTableModel; }
  public JTable getPasswordsTable() { return passwordsTable; }

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

    passwordsTableModel.addRow(item);
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
