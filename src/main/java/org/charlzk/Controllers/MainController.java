package org.charlzk.Controllers;

import org.charlzk.Events.MainEvents.AddPasswordOnAction;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainController {
  // View
  private final JFrame mainView;

  // Table data model
  private final DefaultTableModel foldersTableModel;
  private final DefaultTableModel passwordsTableModel;

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

  public MainController(
          // View
          JFrame mainView,

          // Table data model
          DefaultTableModel foldersTableModel,
          DefaultTableModel passwordsTableModel,

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

    this.searchField = searchField;

    this.searchOptionComboBox = searchOptionComboBox;

    this.searchButton = searchButton;
    this.addPasswordButton = addPasswordButton;
    this.addFolderButton = addFolderButton;

    this.addPasswordOnAction = new AddPasswordOnAction();

    addPasswordButton.addActionListener(addPasswordOnAction);
  }

  public void close() {
    addPasswordButton.removeActionListener(addPasswordOnAction);
  }
}
