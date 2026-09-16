package org.charlzk.Controllers;

import org.charlzk.Events.EditPasswordEvents.CancelButtonOnAction;
import org.charlzk.Events.EditPasswordEvents.EditPasswordWindowListener;
import org.charlzk.Events.EditPasswordEvents.SaveButtonOnAction;
import org.charlzk.Models.Folder;
import org.charlzk.Models.PasswordEntry;

import javax.swing.*;

public class EditPasswordController {
  // View
  private final JFrame editPasswordView;

  // Current Password Entry
  private PasswordEntry currentPasswordEntry;

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
  private final DefaultComboBoxModel<Folder> folderComboBoxModel;
  private final JComboBox<Folder> folderComboBox;

  // Buttons
  private final JButton cancelButton;
  private final JButton saveButton;

  // Events
  private final CancelButtonOnAction cancelButtonOnAction;
  private final SaveButtonOnAction saveButtonOnAction;
  private final EditPasswordWindowListener editPasswordWindowListener;

  public EditPasswordController(
          // View
          JFrame editPasswordView,

          // Controller
          MainController mainController,

          // Fields
          JTextField titleField,
          JTextField usernameField,
          JTextField urlField,
          JTextField passwordField,

          JTextArea noteTextArea,

          // ComboBox
          DefaultComboBoxModel<Folder> folderComboBoxModel,
          JComboBox<Folder> folderComboBox,

          // Buttons
          JButton cancelButton,
          JButton saveButton
  ) {
    this.editPasswordView = editPasswordView;

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

    this.cancelButtonOnAction = new CancelButtonOnAction(editPasswordView);
    this.saveButtonOnAction = new SaveButtonOnAction(this);
    this.editPasswordWindowListener = new EditPasswordWindowListener(this);

    editPasswordView.addWindowListener(editPasswordWindowListener);
    cancelButton.addActionListener(cancelButtonOnAction);
    saveButton.addActionListener(saveButtonOnAction);
  }

  public void close() {
    editPasswordView.removeWindowListener(editPasswordWindowListener);
    cancelButton.removeActionListener(cancelButtonOnAction);
    saveButton.removeActionListener(saveButtonOnAction);
  }

  // Getters
  public JTextField getTitleField() { return titleField; }
  public JTextField getUsernameField() { return usernameField; }
  public JTextField getUrlField() { return urlField; }
  public JTextField getPasswordField() { return passwordField; }
  public JTextArea getNoteTextArea() { return noteTextArea; }
  public DefaultComboBoxModel<Folder> getFolderComboBoxModel() { return folderComboBoxModel; }
  public JComboBox<Folder> getFolderComboBox() { return folderComboBox; }
  public PasswordEntry getCurrentPasswordEntry() { return currentPasswordEntry; }
  public JFrame getEditPasswordView() { return editPasswordView; }
  public MainController getMainController() { return mainController; }

  // Setters
  public void setCurrentPasswordEntry(PasswordEntry value) { currentPasswordEntry = value; }

  // Utils
  public void addFolderComboBoxItem(Folder folder) {
    folderComboBoxModel.addElement(folder);
  }
}
