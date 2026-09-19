package org.charlzk.Components.Layouts;

import javax.swing.*;
import java.awt.*;

public class EditFolderLayout extends JPanel {
  // Field column length
  private static final int fieldColumnLength = 25;

  // Fields
  public static final JTextField folderNameField = new JTextField(fieldColumnLength);

  // Buttons
  public static final JButton cancelButton = new JButton("Cancel");
  public static final JButton saveButton = new JButton("Save");

  public EditFolderLayout() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    gbc.insets = new Insets(2, 5, 2, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // ===== Name row =====
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 0.0;
    formPanel.add(new JLabel("Folder Name"), gbc);

    gbc.gridy = 1;
    formPanel.add(folderNameField, gbc);

    // ===== Buttons row =====
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    buttonsPanel.add(cancelButton);
    buttonsPanel.add(saveButton);

    gbc.gridy = 2;
    formPanel.add(buttonsPanel, gbc);

    add(formPanel, BorderLayout.NORTH);
  }
}
