package org.charlzk.Components.Layouts;

import javax.swing.*;
import java.awt.*;

public class LoginLayout extends JPanel {
  // HTML content
  private static final String registerHtmlContent = """
    <html>
      <body style='font-family:Segoe UI, sans-serif;font-size:11px;'>
      Don’t have an account? <a href='register'>Create one</a>.
      </body>
    </html>
    """;

  // Field column length
  private static final int fieldColumnLength = 30;

  // Fields
  public static final JTextField emailField = new JTextField(fieldColumnLength);
  public static final JPasswordField passwordField = new JPasswordField(fieldColumnLength);

  // Buttons
  public static final JEditorPane registerPane = new JEditorPane("text/html", registerHtmlContent);
  public static final JButton loginButton = new JButton("Sign in");

  public LoginLayout() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    // ========== FORM PANEL ==========
    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.anchor = GridBagConstraints.FIRST_LINE_START;
    gbc.insets = new Insets(2, 5, 2, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // ===== Email row =====
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1.0;
    gbc.weighty = 0.0;
    formPanel.add(new JLabel("Email"), gbc);

    gbc.gridy = 1;
    formPanel.add(emailField, gbc);

    // ===== Password row =====
    gbc.gridy = 2;
    formPanel.add(new JLabel("Password"), gbc);

    gbc.gridy = 3;
    formPanel.add(passwordField, gbc);

    // ===== Buttons panel =====
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    gbc.gridy = 4;
    formPanel.add(buttonsPanel, gbc);

    registerPane.setEditable(false);
    registerPane.setOpaque(false);
    registerPane.setBorder(null);
    registerPane.setFocusable(false);

    buttonsPanel.add(registerPane);
    buttonsPanel.add(loginButton);

    add(formPanel, BorderLayout.NORTH);
  }
}
