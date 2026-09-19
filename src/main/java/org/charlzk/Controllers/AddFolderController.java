package org.charlzk.Controllers;

import org.charlzk.Events.AddFolderEvents.AddFolderWindowListener;
import org.charlzk.Events.AddFolderEvents.CancelButtonOnAction;
import org.charlzk.Events.AddFolderEvents.SaveButtonOnAction;

import javax.swing.*;

public class AddFolderController {
  // View
  private final JFrame addFolderView;

  // Controller
  private final MainController mainController;

  // Fields
  private final JTextField folderNameField;

  // Buttons
  private final JButton cancelButton;
  private final JButton saveButton;

  // Events
  private final AddFolderWindowListener addFolderWindowListener;
  private final CancelButtonOnAction cancelButtonOnAction;
  private final SaveButtonOnAction saveButtonOnAction;

  public AddFolderController(
          JFrame addFolderView,

          MainController mainController,

          JTextField folderNameField,

          JButton cancelButton,
          JButton saveButton
  ) {
    this.addFolderView = addFolderView;

    this.mainController = mainController;

    this.folderNameField = folderNameField;

    this.cancelButton = cancelButton;
    this.saveButton = saveButton;

    this.addFolderWindowListener = new AddFolderWindowListener(this);
    this.cancelButtonOnAction = new CancelButtonOnAction(this);
    this.saveButtonOnAction = new SaveButtonOnAction(this);

    addFolderView.addWindowListener(addFolderWindowListener);
    cancelButton.addActionListener(cancelButtonOnAction);
    saveButton.addActionListener(saveButtonOnAction);
  }

  public void close() {
    addFolderView.removeWindowListener(addFolderWindowListener);
    cancelButton.removeActionListener(cancelButtonOnAction);
    saveButton.removeActionListener(saveButtonOnAction);
  }

  // Getters
  public MainController getMainController() { return mainController; }
  public JFrame getAddFolderView() { return addFolderView; }
  public JTextField getFolderNameField() { return folderNameField; }

  // Utils
  public void clearFields() {
    folderNameField.setText("");
  }
}
