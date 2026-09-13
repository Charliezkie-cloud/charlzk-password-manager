package org.charlzk.Controllers;

import org.charlzk.Events.AddPasswordEvents.CancelButtonOnAction;
import org.charlzk.Events.AddPasswordEvents.SaveButtonOnAction;
import org.charlzk.Models.Folder;

import javax.swing.*;

public class AddPasswordController {
  // View
  private final JFrame addPasswordView;

  // Controller
  private final MainController mainController;

  // Fields
  private final JTextField titleField;
  private final JTextField usernameField;
  private final JTextField urlField;
  private final JTextField passwordField;

  // ComboBox
  private final JComboBox<Folder> folderComboBox;
  private final DefaultComboBoxModel<Folder> folderComboBoxModel;

  // Buttons
  private final JButton cancelButton;
  private final JButton saveButton;

  // Events
  private final CancelButtonOnAction cancelButtonOnAction;
  private final SaveButtonOnAction saveButtonOnAction;

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

    this.folderComboBox = folderComboBox;
    this.folderComboBoxModel = folderComboBoxModel;

    this.cancelButton = cancelButton;
    this.saveButton = saveButton;

    this.cancelButtonOnAction = new CancelButtonOnAction(addPasswordView);
    this.saveButtonOnAction = new SaveButtonOnAction(
            addPasswordView,

            mainController,

            titleField,
            usernameField,
            urlField,
            passwordField,

            folderComboBox
    );

    cancelButton.addActionListener(cancelButtonOnAction);
    saveButton.addActionListener(saveButtonOnAction);
  }

  public void close() {
    cancelButton.removeActionListener(cancelButtonOnAction);
    saveButton.removeActionListener(saveButtonOnAction);
  }

  // Utils
  public void addFolderComboBoxItem(Folder folder) {
    folderComboBoxModel.addElement(folder);
  }
}
