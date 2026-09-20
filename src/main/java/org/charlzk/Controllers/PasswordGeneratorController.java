package org.charlzk.Controllers;

import org.charlzk.Events.PasswordGeneratorEvents.GenerateButtonOnAction;
import org.charlzk.Events.PasswordGeneratorEvents.SaveButtonOnAction;
import org.charlzk.Views.AuthViews.AddPasswordView;

import javax.swing.*;
import java.security.SecureRandom;

public class PasswordGeneratorController {
  // Controller
  private final MainController mainController;

  private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
  private static final String NUMBER_CHARACTERS = "0123456789";
  private static final String SYMBOL_CHARACTERS = "!@#$%^&*()-_=+[]{};:,.?";

  // Fields
  private final JTextField generatedPasswordField;

  // Spinner
  private final JSpinner passwordLengthSpinner;

  // Checkboxes
  private final JCheckBox uppercaseCheckbox;
  private final JCheckBox lowercaseCheckbox;
  private final JCheckBox numbersCheckbox;
  private final JCheckBox symbolsCheckbox;

  // Buttons
  private final JButton generateButton;
  private final JButton saveButton;

  // Events
  private final GenerateButtonOnAction generateButtonOnAction;
  private final SaveButtonOnAction saveButtonOnAction;

  public PasswordGeneratorController(
          // Controller
          MainController mainController,

          // Fields
          JTextField generatedPasswordField,

          // Spinner
          JSpinner passwordLengthSpinner,

          // Checkboxes
          JCheckBox uppercaseCheckbox,
          JCheckBox lowercaseCheckbox,
          JCheckBox numbersCheckbox,
          JCheckBox symbolsCheckbox,

          // Buttons
          JButton generateButton,
          JButton saveButton
  ) {
    this.mainController = mainController;
    this.generatedPasswordField = generatedPasswordField;
    this.passwordLengthSpinner = passwordLengthSpinner;
    this.uppercaseCheckbox = uppercaseCheckbox;
    this.lowercaseCheckbox = lowercaseCheckbox;
    this.numbersCheckbox = numbersCheckbox;
    this.symbolsCheckbox = symbolsCheckbox;
    this.generateButton = generateButton;
    this.saveButton = saveButton;

    this.generateButtonOnAction = new GenerateButtonOnAction(this);
    this.saveButtonOnAction = new SaveButtonOnAction(this);

    generateButton.addActionListener(generateButtonOnAction);
    saveButton.addActionListener(saveButtonOnAction);
  }

  public void generatePassword() {
    if (!validateForm()) return;

    int passwordLength = (int) passwordLengthSpinner.getValue();
    StringBuilder characterPool = new StringBuilder();
    String[] selectedCharacterTypes = new String[4];
    int selectedCharacterTypeCount = 0;

    if (uppercaseCheckbox.isSelected()) {
      characterPool.append(UPPERCASE_CHARACTERS);
      selectedCharacterTypes[selectedCharacterTypeCount++] = UPPERCASE_CHARACTERS;
    }
    if (lowercaseCheckbox.isSelected()) {
      characterPool.append(LOWERCASE_CHARACTERS);
      selectedCharacterTypes[selectedCharacterTypeCount++] = LOWERCASE_CHARACTERS;
    }
    if (numbersCheckbox.isSelected()) {
      characterPool.append(NUMBER_CHARACTERS);
      selectedCharacterTypes[selectedCharacterTypeCount++] = NUMBER_CHARACTERS;
    }
    if (symbolsCheckbox.isSelected()) {
      characterPool.append(SYMBOL_CHARACTERS);
      selectedCharacterTypes[selectedCharacterTypeCount++] = SYMBOL_CHARACTERS;
    }

    SecureRandom secureRandom = new SecureRandom();
    char[] password = new char[passwordLength];

    for (int i = 0; i < selectedCharacterTypeCount; i++) {
      String characterType = selectedCharacterTypes[i];
      password[i] = characterType.charAt(secureRandom.nextInt(characterType.length()));
    }

    for (int i = selectedCharacterTypeCount; i < password.length; i++) {
      password[i] = characterPool.charAt(secureRandom.nextInt(characterPool.length()));
    }

    for (int i = password.length - 1; i > 0; i--) {
      int randomIndex = secureRandom.nextInt(i + 1);
      char character = password[i];
      password[i] = password[randomIndex];
      password[randomIndex] = character;
    }

    generatedPasswordField.setText(new String(password));
  }

  public void saveGeneratedPassword() {
    String generatedPassword = generatedPasswordField.getText();
    if (generatedPassword.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Generate a password first.", "Password Generator", JOptionPane.WARNING_MESSAGE);
      return;
    }

    new AddPasswordView(mainController, generatedPassword).setVisible(true);
  }

  private boolean validateForm() {
    if (uppercaseCheckbox.isSelected() || lowercaseCheckbox.isSelected()
            || numbersCheckbox.isSelected() || symbolsCheckbox.isSelected()) {
      return true;
    }

    JOptionPane.showMessageDialog(null, "Select at least one character type.", "Password Generator", JOptionPane.WARNING_MESSAGE);
    return false;
  }
}
