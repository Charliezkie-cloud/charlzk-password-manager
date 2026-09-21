package org.charlzk.Controllers;

import org.charlzk.Components.CustomJOptionPane;
import org.charlzk.Events.SettingsEvents.*;
import org.charlzk.Models.User;
import org.charlzk.Services.ThemeServices;
import org.charlzk.Session.SessionManager;

import javax.swing.*;
import java.io.IOException;

public class SettingsController {
  // Controller
  private final MainController mainController;

  // ComboBox
  private final JComboBox<String> themeComboBox;
  private final DefaultComboBoxModel<String> themeComboBoxModel;

  // Fields
  private final JTextField currentUsernameField;
  private final JTextField newUsernameField;
  private final JTextField currentEmailField;
  private final JTextField newEmailField;
  private final JPasswordField newPasswordField;
  private final JPasswordField newPasswordConfirmationField;

  // Buttons
  private final JButton saveThemeButton;
  private final JButton saveUsernameButton;
  private final JButton saveEmailButton;
  private final JButton savePasswordButton;
  private final JButton deleteAccountButton;

  // Events
  private final ThemeComboBoxOnAction themeComboBoxOnAction;
  private final SaveThemeButtonOnAction saveThemeButtonOnAction;
  private final SaveEmailButtonOnAction saveEmailButtonOnAction;
  private final SavePasswordButtonOnAction savePasswordButtonOnAction;
  private final SaveUsernameButtonOnAction saveUsernameButtonOnAction;
  private final DeleteAccountButtonOnAction deleteAccountButtonOnAction;

  public SettingsController(
          // Controller
          MainController mainController,

          // ComboBox
          JComboBox<String> themeComboBox,
          DefaultComboBoxModel<String> themeComboBoxModel,

          // Fields
          JTextField currentUsernameField,
          JTextField newUsernameField,
          JTextField currentEmailField,
          JTextField newEmailField,
          JPasswordField newPasswordField,
          JPasswordField newPasswordConfirmationField,

          // Buttons
          JButton saveThemeButton,
          JButton saveUsernameButton,
          JButton saveEmailButton,
          JButton savePasswordButton,
          JButton deleteAccountButton
  ) {
    this.mainController = mainController;

    this.themeComboBox = themeComboBox;
    this.themeComboBoxModel = themeComboBoxModel;

    this.currentUsernameField = currentUsernameField;
    this.newUsernameField = newUsernameField;
    this.currentEmailField = currentEmailField;
    this.newEmailField = newEmailField;
    this.newPasswordField = newPasswordField;
    this.newPasswordConfirmationField = newPasswordConfirmationField;

    this.saveThemeButton = saveThemeButton;
    this.saveUsernameButton = saveUsernameButton;
    this.saveEmailButton = saveEmailButton;
    this.savePasswordButton = savePasswordButton;
    this.deleteAccountButton = deleteAccountButton;

    this.themeComboBoxOnAction = new ThemeComboBoxOnAction(this);
    this.saveThemeButtonOnAction = new SaveThemeButtonOnAction(this);
    this.saveEmailButtonOnAction = new SaveEmailButtonOnAction(this);
    this.savePasswordButtonOnAction = new SavePasswordButtonOnAction(this);
    this.saveUsernameButtonOnAction = new SaveUsernameButtonOnAction(this);
    this.deleteAccountButtonOnAction = new DeleteAccountButtonOnAction(this);

    themeComboBox.addActionListener(themeComboBoxOnAction);
    saveThemeButton.addActionListener(saveThemeButtonOnAction);
    saveEmailButton.addActionListener(saveEmailButtonOnAction);
    savePasswordButton.addActionListener(savePasswordButtonOnAction);
    saveUsernameButton.addActionListener(saveUsernameButtonOnAction);
    deleteAccountButton.addActionListener(deleteAccountButtonOnAction);
  }

  public void close() {
    themeComboBox.removeActionListener(themeComboBoxOnAction);
    saveThemeButton.removeActionListener(saveThemeButtonOnAction);
    saveEmailButton.removeActionListener(saveEmailButtonOnAction);
    savePasswordButton.removeActionListener(savePasswordButtonOnAction);
    saveUsernameButton.removeActionListener(saveUsernameButtonOnAction);
    deleteAccountButton.removeActionListener(deleteAccountButtonOnAction);
  }

  // Getters
  public MainController getMainController() { return mainController; }
  public JComboBox<String> getThemeComboBox() { return themeComboBox; }
  public DefaultComboBoxModel<String> getThemeComboBoxModel() { return themeComboBoxModel; }
  public JTextField getCurrentUsernameField() { return currentUsernameField; }
  public JTextField getNewUsernameField() { return newUsernameField; }
  public JTextField getCurrentEmailField() { return currentEmailField; }
  public JTextField getNewEmailField() { return newEmailField; }
  public JPasswordField getNewPasswordField() { return newPasswordField; }
  public JPasswordField getNewPasswordConfirmationField() { return newPasswordConfirmationField; }
  public JButton getSaveThemeButton() { return saveThemeButton; }
  public JButton getSaveUsernameButton() { return saveUsernameButton; }
  public JButton getSaveEmailButton() { return saveEmailButton; }
  public JButton getSavePasswordButton() { return savePasswordButton; }
  public JButton getDeleteAccountButton() { return deleteAccountButton; }

  // Utils
  public void populateFields() {
    User currentUser = SessionManager.getInstance().getCurrentUser();
    if (currentUser != null) {
      currentUsernameField.setText(currentUser.getUsername());
      currentEmailField.setText(currentUser.getEmail());
    }

    String savedTheme = ThemeServices.getSavedTheme();
    themeComboBox.setSelectedItem(savedTheme);
  }

  public void clearFields() {
    newUsernameField.setText("");
    newEmailField.setText("");
    newPasswordField.setText("");
    newPasswordConfirmationField.setText("");
  }

  public void onThemeChanged() {
    String selectedTheme = (String) themeComboBox.getSelectedItem();
    if (selectedTheme == null) return;
    if (selectedTheme.equalsIgnoreCase(ThemeServices.getSavedTheme())) return;

    try {
      ThemeServices.applyTheme(selectedTheme);
      ThemeServices.saveTheme(selectedTheme);
    } catch (IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }

  public void saveTheme() {
    String selectedTheme = (String) themeComboBox.getSelectedItem();
    if (selectedTheme == null) return;

    try {
      ThemeServices.applyTheme(selectedTheme);
      ThemeServices.saveTheme(selectedTheme);
      CustomJOptionPane.showSuccessMessageDialog("Theme saved!", "Success");
    } catch (IOException ex) {
      CustomJOptionPane.showErrorMessageDialog(ex.getMessage(), "Application Error");
    }
  }
}

