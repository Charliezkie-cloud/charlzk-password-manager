package org.charlzk.Components.Layouts;

import org.charlzk.Models.Folder;

import javax.swing.*;
import java.awt.*;

public class AddPasswordLayout extends JPanel {
  // Field column length
  private static final int fieldColumnLength = 25;

  // Fields
  public static final JTextField titleField = new JTextField(fieldColumnLength);
  public static final JTextField usernameField = new JTextField(fieldColumnLength);
  public static final JTextField urlField = new JTextField("https://", fieldColumnLength);
  public static final JTextField passwordField = new JTextField(fieldColumnLength);
  public static final JTextArea noteTextArea = new JTextArea(5, fieldColumnLength);

  // ComboBox
  public static DefaultComboBoxModel<Folder> folderComboBoxModel = new DefaultComboBoxModel<>();
  public static final JComboBox<Folder> folderComboBox = new JComboBox<>(folderComboBoxModel);

  // Buttons
  public static final JButton cancelButton = new JButton("Cancel");
  public static final JButton saveButton = new JButton("Save");

  public AddPasswordLayout() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    gbc.insets = new Insets(2, 5, 2, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // ===== Title row =====
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 0.0;
    formPanel.add(new JLabel("URL"), gbc);

    gbc.gridy = 1;
    formPanel.add(urlField, gbc);

    // ===== Title panel =====
    JPanel titlePanel = new JPanel();
    titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

    JLabel titleLabel = new JLabel("Name");
    titleLabel.setAlignmentX(LEFT_ALIGNMENT);

    titlePanel.add(titleLabel);

    titleField.setAlignmentX(LEFT_ALIGNMENT);
    titlePanel.add(titleField);

    // ===== Folder panel =====
    JPanel folderPanel = new JPanel();
    folderPanel.setLayout(new BoxLayout(folderPanel, BoxLayout.Y_AXIS));

    JLabel folderLabel = new JLabel("Folder");
    folderLabel.setAlignmentX(LEFT_ALIGNMENT);

    folderPanel.add(folderLabel);

    folderComboBox.setAlignmentX(LEFT_ALIGNMENT);
    folderPanel.add(folderComboBox);

    // ========== Title and Folder Row ==========
    JPanel firstRow = new JPanel();
    firstRow.setLayout(new BorderLayout(5, 5));
    firstRow.add(titlePanel, BorderLayout.WEST);
    firstRow.add(folderPanel, BorderLayout.CENTER);

    gbc.gridy = 2;
    formPanel.add(firstRow, gbc);

    // ===== Username panel =====
    JPanel usernamePanel = new JPanel();
    usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));

    JLabel usernameLabel = new JLabel("Username");
    usernameLabel.setAlignmentX(LEFT_ALIGNMENT);

    usernamePanel.add(usernameLabel);

    usernameField.setAlignmentX(LEFT_ALIGNMENT);
    usernamePanel.add(usernameField);

    // ===== Password panel =====
    JPanel passwordPanel = new JPanel();
    passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));

    JLabel passwordLabel = new JLabel("Site Password");
    passwordLabel.setAlignmentX(LEFT_ALIGNMENT);

    passwordPanel.add(passwordLabel);

    passwordField.setAlignmentX(LEFT_ALIGNMENT);
    passwordPanel.add(passwordField);

    // ========== Username and Password Row ==========
    JPanel secondRow = new JPanel();
    secondRow.setLayout(new BorderLayout(5, 5));
    secondRow.add(usernamePanel, BorderLayout.WEST);
    secondRow.add(passwordPanel, BorderLayout.EAST);

    gbc.gridy = 3;
    formPanel.add(secondRow, gbc);

    // ===== Note row =====

    gbc.gridy = 4;
    formPanel.add(new JLabel("Note"), gbc);

    noteTextArea.setLineWrap(true);
    noteTextArea.setWrapStyleWord(true);
    JScrollPane noteScrollPane = new JScrollPane(noteTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    gbc.gridy = 5;
    formPanel.add(noteScrollPane, gbc);

    // ===== Buttons row =====
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    buttonsPanel.add(cancelButton);
    buttonsPanel.add(saveButton);

    gbc.gridy = 6;
    formPanel.add(buttonsPanel, gbc);

    add(formPanel, BorderLayout.NORTH);
  }
}
