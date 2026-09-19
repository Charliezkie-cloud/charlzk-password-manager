package org.charlzk.Controllers;

import org.charlzk.Events.EditFolderEvents.CancelButtonOnAction;
import org.charlzk.Events.EditFolderEvents.EditFolderWindowListener;
import org.charlzk.Events.EditFolderEvents.SaveButtonOnAction;
import org.charlzk.Models.Folder;
import org.charlzk.Views.AuthViews.EditFolderView;

import javax.swing.*;

public class EditFolderController {
  // View
  private final EditFolderView editFolderView;

  // Selected Folder
  private Folder selectedFolder;

  // Controller
  private final MainController mainController;
  private final FoldersController foldersController;

  // Fields
  private final JTextField folderNameField;

  // Buttons
  private final JButton cancelButton;
  private final JButton saveButton;

  // Events
  private final EditFolderWindowListener editFolderWindowListener;
  private final CancelButtonOnAction cancelButtonOnAction;
  private final SaveButtonOnAction saveButtonOnAction;

  public EditFolderController(
          EditFolderView editFolderView,

          MainController mainController,
          Folder selectedFolder,

          JTextField folderNameField,

          JButton cancelButton,
          JButton saveButton
  ) {
    this.editFolderView = editFolderView;

    this.mainController = mainController;
    this.foldersController = null;
    this.selectedFolder = selectedFolder;

    this.folderNameField = folderNameField;

    this.cancelButton = cancelButton;
    this.saveButton = saveButton;

    this.editFolderWindowListener = new EditFolderWindowListener(this);
    this.cancelButtonOnAction = new CancelButtonOnAction(this);
    this.saveButtonOnAction = new SaveButtonOnAction(this);

    editFolderView.addWindowListener(editFolderWindowListener);
    cancelButton.addActionListener(cancelButtonOnAction);
    saveButton.addActionListener(saveButtonOnAction);
  }

  public void close() {
    editFolderView.removeWindowListener(editFolderWindowListener);
    cancelButton.removeActionListener(cancelButtonOnAction);
    saveButton.removeActionListener(saveButtonOnAction);
  }

  // Getters
  public Folder getSelectedFolder() { return selectedFolder; }
  public MainController getMainController() { return mainController; }
  public EditFolderView getEditFolderView() { return editFolderView; }
  public JTextField getFolderNameField() { return folderNameField; }

  // Setters
  public void setSelectedFolder(Folder value) { selectedFolder = value; }
}
