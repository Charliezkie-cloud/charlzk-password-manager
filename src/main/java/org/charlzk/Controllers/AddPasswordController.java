package org.charlzk.Controllers;

import org.charlzk.Events.AddPasswordEvents.AddPasswordWindowListener;
import org.charlzk.Events.AddPasswordEvents.CancelButtonOnAction;
import org.charlzk.Events.AddPasswordEvents.SaveButtonOnAction;
import org.charlzk.Models.Folder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AddPasswordController {
  // Views
  private final JFrame addPasswordView;

  // Controller
  private final MainController mainController;

  // Fields
  private final JTextField titleField;
  private final JTextField usernameField;
  private final JTextField urlField;
  private final JTextField passwordField;

  // Text area
  private final JTextArea noteTextArea;

  // ComboBox
  private final JComboBox<Folder> folderComboBox;
  private final DefaultComboBoxModel<Folder> folderComboBoxModel;

  // Buttons
  private final JButton cancelButton;
  private final JButton saveButton;

  // Events
  private final CancelButtonOnAction cancelButtonOnAction;
  private final SaveButtonOnAction saveButtonOnAction;
  private final AddPasswordWindowListener addPasswordWindowListener;

  public AddPasswordController(
          // View
          JFrame addPasswordView,

          // Controller
          MainController mainController,

          // Fields
          JTextField titleField,
          JTextField usernameField,
          JTextField urlField,
          JTextField passwordField,

          JTextArea noteTextArea,

          // ComboBox
          JComboBox<Folder> folderComboBox,
          DefaultComboBoxModel<Folder> folderComboBoxModel,

          // Buttons
          JButton cancelButton,
          JButton saveButton
  ) {
    this.addPasswordView = addPasswordView;
    this.mainController = mainController;

    this.titleField = titleField;
    this.usernameField = usernameField;
    this.urlField = urlField;
    this.passwordField = passwordField;

    this.noteTextArea = noteTextArea;

    this.folderComboBox = folderComboBox;
    this.folderComboBoxModel = folderComboBoxModel;

    this.cancelButton = cancelButton;
    this.saveButton = saveButton;

    this.addPasswordWindowListener = new AddPasswordWindowListener(this);
    this.cancelButtonOnAction = new CancelButtonOnAction(addPasswordView);
    this.saveButtonOnAction = new SaveButtonOnAction(addPasswordView, mainController, this);

    addPasswordView.addWindowListener(addPasswordWindowListener);
    cancelButton.addActionListener(cancelButtonOnAction);
    saveButton.addActionListener(saveButtonOnAction);
  }

  public void close() {
    addPasswordView.removeWindowListener(addPasswordWindowListener);
    cancelButton.removeActionListener(cancelButtonOnAction);
    saveButton.removeActionListener(saveButtonOnAction);
  }

  // Getters
  public JTextField getTitleField() { return titleField; }
  public JTextField getUsernameField() { return usernameField; }
  public JTextField getUrlField() { return urlField; }
  public JTextField getPasswordField() { return passwordField; }
  public JTextArea getNoteTextArea() { return noteTextArea; }
  public JComboBox<Folder> getFolderComboBox() { return folderComboBox; }
  public MainController getMainController() { return mainController; }

  public int getSelectedFolderId() {
    JTable foldersTable = mainController.getFoldersTable();
    DefaultTableModel foldersTableModel = mainController.getFoldersTableModel();
    int selectedRow = foldersTable.getSelectedRow();
    if (selectedRow == -1) return -1;

    int modelRow = foldersTable.convertRowIndexToModel(selectedRow);

    try {
      return Integer.parseInt(String.valueOf(foldersTableModel.getValueAt(modelRow, 0)));
    } catch (NumberFormatException ex) {
      return -1;
    }
  }

  // Utils
  public void addFolderComboBoxItem(Folder folder) { folderComboBoxModel.addElement(folder); }

  public void clearFolderComboBoxItem() {folderComboBoxModel.removeAllElements(); }

  public void clearFields() {
    clearFolderComboBoxItem();
    urlField.setText("https://example.com/");
    titleField.setText("");
    usernameField.setText("");
    passwordField.setText("");
    noteTextArea.setText("");
    noteTextArea.setText("");
  }
}
