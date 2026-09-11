package org.charlzk.Controllers;

import org.charlzk.Events.AddPasswordEvents.CancelButtonOnAction;

import javax.swing.*;

public class AddPasswordController {
  // View
  private final JFrame addPasswordView;

  // Fields
  private final JTextField titleField;
  private final JTextField usernameField;
  private final JTextField urlField;
  private final JTextField passwordField;

  // Buttons
  private final JButton cancelButton;
  private final JButton saveButton;

  // Events
  private final CancelButtonOnAction cancelButtonOnAction;

  public AddPasswordController(
          // View
          JFrame addPasswordView,

          // Fields
          JTextField titleField,
          JTextField usernameField,
          JTextField urlField,
          JTextField passwordField,

          // Buttons
          JButton cancelButton,
          JButton saveButton
  ) {
    this.addPasswordView = addPasswordView;

    this.titleField = titleField;
    this.usernameField = usernameField;
    this.urlField = urlField;
    this.passwordField = passwordField;

    this.cancelButton = cancelButton;
    this.saveButton = saveButton;

    this.cancelButtonOnAction = new CancelButtonOnAction(addPasswordView);

    cancelButton.addActionListener(cancelButtonOnAction);
  }

  public void close() {
    cancelButton.removeActionListener(cancelButtonOnAction);
  }
}
