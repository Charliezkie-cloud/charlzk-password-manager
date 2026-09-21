package org.charlzk.Components.Layouts;

import javax.swing.*;
import java.awt.*;

public class SettingsTabLayout extends JPanel {
  // Field column length
  private static final int fieldColumnLength = 30;

  // ComboBox
  public static final DefaultComboBoxModel<String> themeComboBoxModel = new DefaultComboBoxModel<>(
    new String[]{"Dark", "Light"}
  );
  public static final JComboBox<String> themeComboBox = new JComboBox<>(themeComboBoxModel);

  // Fields
  public static final JTextField currentUsernameField = new JTextField(fieldColumnLength);
  public static final JTextField newUsernameField = new JTextField(fieldColumnLength);
  public static final JTextField currentEmailField = new JTextField(fieldColumnLength);
  public static final JTextField newEmailField = new JTextField(fieldColumnLength);
  public static final JPasswordField newPasswordField = new JPasswordField(fieldColumnLength);
  public static final JPasswordField newPasswordConfirmationField = new JPasswordField(fieldColumnLength);

  public static final JButton saveThemeButton = new JButton("Save");
  public static final JButton saveUsernameButton = new JButton("Save");
  public static final JButton saveEmailButton = new JButton("Save");
  public static final JButton savePasswordButton = new JButton("Save");
  public static final JButton deleteAccountButton = new JButton("Delete Account");

  // Buttons
  public SettingsTabLayout() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    // ========== FORM PANEL ==========
    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    gbc.insets = new Insets(2, 5, 2, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 0.0;

    int row = 0;

    // ========== PREFERENCE SECTION ==========
    gbc.gridy = row++;
    JLabel preferenceLabel = new JLabel("Preference");
    preferenceLabel.setFont(preferenceLabel.getFont().deriveFont(Font.BOLD, 14f));
    formPanel.add(preferenceLabel, gbc);

    // ===== Theme row =====
    gbc.gridy = row++;
    formPanel.add(new JLabel("Theme"), gbc);

    gbc.gridy = row++;
    formPanel.add(themeComboBox, gbc);

    // ===== Save theme button =====
    JPanel saveThemePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    saveThemePanel.add(saveThemeButton);

    gbc.gridy = row++;
    formPanel.add(saveThemePanel, gbc);

    // ===== Separator =====
    gbc.gridy = row++;
    gbc.insets = new Insets(8, 5, 8, 5);
    formPanel.add(new JSeparator(), gbc);
    gbc.insets = new Insets(2, 5, 2, 5);

    // ========== SECURITY SECTION ==========
    gbc.gridy = row++;
    JLabel securityLabel = new JLabel("Profile and Security");
    securityLabel.setFont(securityLabel.getFont().deriveFont(Font.BOLD, 14f));
    formPanel.add(securityLabel, gbc);

    // ===== Username form =====
    gbc.gridy = row++;
    formPanel.add(new JLabel("Current Username"), gbc);

    gbc.gridy = row++;
    currentUsernameField.setEditable(false);
    formPanel.add(currentUsernameField, gbc);

    gbc.gridy = row++;
    formPanel.add(new JLabel("New Username"), gbc);

    gbc.gridy = row++;
    formPanel.add(newUsernameField, gbc);

    // ===== Save username button =====
    JPanel saveUsernamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    saveUsernamePanel.add(saveUsernameButton);

    gbc.gridy = row++;
    formPanel.add(saveUsernamePanel, gbc);

    // ===== Security forms (Two columns: Email on left, Password on right) =====
    JPanel securityFormsPanel = new JPanel(new GridLayout(1, 2, 10, 0));

    // --- Email form (Left column) ---
    JPanel emailPanel = new JPanel(new GridBagLayout());
    GridBagConstraints emailGbc = new GridBagConstraints();
    emailGbc.anchor = GridBagConstraints.FIRST_LINE_START;
    emailGbc.insets = new Insets(2, 0, 2, 0);
    emailGbc.fill = GridBagConstraints.HORIZONTAL;
    emailGbc.gridx = 0;
    emailGbc.weightx = 1.0;

    int emailRow = 0;
    emailGbc.gridy = emailRow++;
    emailPanel.add(new JLabel("Current Email Address"), emailGbc);

    emailGbc.gridy = emailRow++;
    currentEmailField.setEnabled(false);
    emailPanel.add(currentEmailField, emailGbc);

    emailGbc.gridy = emailRow++;
    emailPanel.add(new JLabel("New Email Address"), emailGbc);

    emailGbc.gridy = emailRow++;
    emailPanel.add(newEmailField, emailGbc);

    JPanel saveEmailPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    saveEmailPanel.add(saveEmailButton);
    emailGbc.gridy = emailRow;
    emailPanel.add(saveEmailPanel, emailGbc);

    // --- Password form (Right column) ---
    JPanel passwordPanel = new JPanel(new GridBagLayout());
    GridBagConstraints passwordGbc = new GridBagConstraints();
    passwordGbc.anchor = GridBagConstraints.FIRST_LINE_START;
    passwordGbc.insets = new Insets(2, 0, 2, 0);
    passwordGbc.fill = GridBagConstraints.HORIZONTAL;
    passwordGbc.gridx = 0;
    passwordGbc.weightx = 1.0;

    int passwordRow = 0;
    passwordGbc.gridy = passwordRow++;
    passwordPanel.add(new JLabel("New Password"), passwordGbc);

    passwordGbc.gridy = passwordRow++;
    passwordPanel.add(newPasswordField, passwordGbc);

    passwordGbc.gridy = passwordRow++;
    passwordPanel.add(new JLabel("New Password Confirmation"), passwordGbc);

    passwordGbc.gridy = passwordRow++;
    passwordPanel.add(newPasswordConfirmationField, passwordGbc);

    JPanel savePasswordPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    savePasswordPanel.add(savePasswordButton);
    passwordGbc.gridy = passwordRow;
    passwordPanel.add(savePasswordPanel, passwordGbc);

    securityFormsPanel.add(emailPanel);
    securityFormsPanel.add(passwordPanel);

    gbc.gridy = row++;
    formPanel.add(securityFormsPanel, gbc);

    // ===== Separator =====
    gbc.gridy = row++;
    gbc.insets = new Insets(8, 5, 8, 5);
    formPanel.add(new JSeparator(), gbc);
    gbc.insets = new Insets(2, 5, 2, 5);

    // ========== DANGEROUS ACTIONS SECTION ==========
    gbc.gridy = row++;
    JLabel dangerousActionsLabel = new JLabel("Dangerous Actions");
    dangerousActionsLabel.setFont(dangerousActionsLabel.getFont().deriveFont(Font.BOLD, 14f));
    formPanel.add(dangerousActionsLabel, gbc);

    gbc.gridy = row;
    deleteAccountButton.setBackground(Color.decode("#f87171"));
    deleteAccountButton.setForeground(Color.WHITE);
    formPanel.add(deleteAccountButton, gbc);

    add(formPanel, BorderLayout.NORTH);
  }
}
