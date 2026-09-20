package org.charlzk.Components.Layouts;

import javax.swing.*;
import java.awt.*;

public class PasswordGeneratorLayout extends JPanel {
  // Fields
  public static final JTextField generatedPasswordField = new JTextField();

  // Spinner
  public static final JSpinner passwordLengthSpinner = new JSpinner(new SpinnerNumberModel(16, 4, 128, 1));

  // Checkboxes
  public static final JCheckBox uppercaseCheckbox = new JCheckBox("Uppercase (A-Z)", true);
  public static final JCheckBox lowercaseCheckbox = new JCheckBox("Lowercase (a-z)", true);
  public static final JCheckBox numbersCheckbox = new JCheckBox("Numbers (0-9)", true);
  public static final JCheckBox symbolsCheckbox = new JCheckBox("Symbols", true);

  // Buttons
  public static final JButton generateButton = new JButton("Generate");
  public static final JButton saveButton = new JButton("Save");

  public PasswordGeneratorLayout() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    generatedPasswordField.setEditable(false);

    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    gbc.insets = new Insets(2, 5, 2, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // ===== Generated password row =====
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 0.0;
    formPanel.add(new JLabel("Generated Password"), gbc);

    gbc.gridy = 1;
    formPanel.add(generatedPasswordField, gbc);

    // ===== Password length row =====
    gbc.gridy = 2;
    formPanel.add(new JLabel("Password Length"), gbc);

    gbc.gridy = 3;
    formPanel.add(passwordLengthSpinner, gbc);

    // ===== Character types row =====
    gbc.gridy = 4;
    formPanel.add(new JLabel("Character Types"), gbc);

    JPanel characterTypesPanel = new JPanel(new GridLayout(2, 2));
    characterTypesPanel.add(uppercaseCheckbox);
    characterTypesPanel.add(lowercaseCheckbox);
    characterTypesPanel.add(numbersCheckbox);
    characterTypesPanel.add(symbolsCheckbox);

    gbc.gridy = 5;
    formPanel.add(characterTypesPanel, gbc);

    // ===== Buttons row =====
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    buttonsPanel.add(generateButton);
    buttonsPanel.add(saveButton);

    gbc.gridy = 6;
    formPanel.add(buttonsPanel, gbc);

    add(formPanel, BorderLayout.NORTH);
  }
}
