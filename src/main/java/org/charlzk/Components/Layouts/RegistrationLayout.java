package org.charlzk.Components.Layouts;

import javax.swing.*;
import java.awt.*;

public class RegistrationLayout extends JPanel {
  // Field column length
  private static final int fieldColumnLength = 30;

  // Fields
  public static final JTextField usernameField = new JTextField(fieldColumnLength);
  public static final JTextField emailField = new JTextField(fieldColumnLength);
  public static final JPasswordField passwordField = new JPasswordField(fieldColumnLength);
  public static final JPasswordField passwordConfirmationField = new JPasswordField(fieldColumnLength);

  // Legal components
  public static final JButton termsOfUseButton = new JButton("By creating an account, you agree to our Terms of Use and Privacy Policy.");
  public static final JCheckBox agreementCheckbox = new JCheckBox("I Agree");

  // Buttons
  public static final JButton cancelButton = new JButton("Cancel");
  public static final JButton registerButton = new JButton("Register");

  public RegistrationLayout() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    // ========== FORM PANEL ==========
    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    gbc.insets = new Insets(2, 5, 2, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // ===== Username row =====
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 0.0;
    formPanel.add(new JLabel("Username"), gbc);

    gbc.gridy = 1;
    formPanel.add(usernameField, gbc);

    // ===== Email row =====
    gbc.gridy = 2;
    formPanel.add(new JLabel("Email"), gbc);

    gbc.gridy = 3;
    gbc.weighty = 1.0;
    formPanel.add(emailField, gbc);

    // ===== Password row =====
    gbc.gridy = 4;
    formPanel.add(new JLabel("Password"), gbc);

    gbc.gridy = 5;
    gbc.weighty = 1.0;
    formPanel.add(passwordField, gbc);

    // ===== Password confirmation row =====
    gbc.gridy = 6;
    formPanel.add(new JLabel("Confirm Password"), gbc);

    gbc.gridy = 7;
    gbc.weighty = 1.0;
    formPanel.add(passwordConfirmationField, gbc);

    // ===== Buttons panel =====
    JPanel legalPanel = new JPanel();
    legalPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    gbc.gridy = 8;
    formPanel.add(legalPanel, gbc);

    legalPanel.add(termsOfUseButton);
    legalPanel.add(agreementCheckbox);


    // ===== Buttons panel =====
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    gbc.gridy = 9;
    formPanel.add(buttonsPanel, gbc);

    buttonsPanel.add(cancelButton);
    buttonsPanel.add(registerButton);

    add(formPanel, BorderLayout.NORTH);
  }
}
