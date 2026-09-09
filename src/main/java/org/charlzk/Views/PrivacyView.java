package org.charlzk.Views;

import javax.swing.*;
import java.awt.*;

import static org.charlzk.Services.ApplicationServices.setGlobalFont;

public class PrivacyView extends JFrame {
  // Close button
  private static final JButton closeButton = new JButton("Close");

  // Privacy HTML content (September 2026)
  private final String html = """
    <!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Privacy Policy</title>
    <style>
      body {
        font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
        max-width: 760px;
        margin: 40px auto;
        padding: 0 20px;
        line-height: 1.6;
        color: #222;
        background-color: #fdfdfd;
      }
      h1 {
        border-bottom: 2px solid #ddd;
        padding-bottom: 10px;
      }
      h2 {
        margin-top: 36px;
        color: #1a1a1a;
      }
      .updated {
        color: #666;
        font-size: 0.95em;
        margin-bottom: 30px;
      }
      code {
        background-color: #f0f0f0;
        padding: 2px 6px;
        border-radius: 4px;
        font-size: 0.9em;
      }
      ul {
        padding-left: 22px;
      }
      li {
        margin-bottom: 6px;
      }
      strong {
        color: #111;
      }
    </style>
    </head>
    <body>
    
    <h1>Privacy Policy</h1>
    <p class="updated"><strong>Last updated:</strong> September 8, 2026</p>
    
    <h2>1. Introduction</h2>
    <p>This Privacy Policy explains how this Password Manager application (referred to here as "the Application") handles your data. The short version is simple: the Application does not collect, transmit, or share any of your information. Everything you store stays on your own computer, and nobody but you has access to it.</p>
    <p>We wrote this policy in plain language on purpose. If any part of it feels unclear, feel free to open an issue on the project's GitHub repository at <code>https://github.com/Charliezkie-cloud/charlzk-password-manager</code>.</p>
    
    <h2>2. What Data the Application Collects</h2>
    <p>None. This is a fully offline, local-first application. It does not connect to the internet, does not phone home, does not use analytics, and does not send crash reports anywhere. There are no servers involved in how this software operates, because there is nothing to connect to in the first place.</p>
    <p>Whatever passwords, notes, or account details you save are entered by you and stored by you, on your own device, and nowhere else.</p>
    
    <h2>3. How Your Data is Stored</h2>
    <p>The Application uses SQLite as its local database and SQLCipher to encrypt that database file. In practice, this means your vault is saved as a single encrypted file on your hard drive. Without your master password, that file is unreadable, even to someone who has direct access to the file itself.</p>
    <p>The database file typically lives in a local application data folder on your operating system. No copies are made outside that folder, and no background process uploads it anywhere.</p>
    
    <h2>4. Your Master Password</h2>
    <p>Your master password is the key that unlocks your encrypted vault. It is never stored in plain text, and it is never saved anywhere by the Application in a way that could be reversed or recovered.</p>
    <p>This leads to something important that I want to be upfront about: <strong>if you forget your master password, there is no way to recover it, and there is no way to recover the data inside your vault.</strong> This is not a limitation we plan to fix later. It is a direct consequence of how real encryption works. If a password manager could recover your master password for you, that would mean the password itself is not actually protecting your data, which would defeat the entire purpose of the tool.</p>
    <p>Because of this, please treat your master password with real care. Write it down somewhere safe if you need to, use a passphrase you can remember, or use whatever backup method you trust, but understand that once it is lost, it is lost for good.</p>
    
    <h2>5. Third-Party Sharing</h2>
    <p>There is no third-party sharing to speak of, since there is no third party involved at all. The Application does not use external APIs, cloud services, advertising networks, or telemetry libraries of any kind. Nothing about your usage of this software is visible to us, to Anthropic, to Charles Henry M. Tinoy Jr., or to anyone else.</p>
    
    <h2>6. Security Measures</h2>
    <p>A few things are worth knowing about how your data is protected:</p>
    <ul>
      <li>Your vault is encrypted at rest using SQLCipher, so the file on disk is not readable without the correct master password.</li>
      <li>The application does not log your plaintext passwords to any log file.</li>
      <li>No network sockets are opened by the application during normal use, so there is no exposure to network-based attacks through the app itself.</li>
    </ul>
    <p>That said, encryption protects your data from someone who gets hold of the file. It cannot protect you from things outside the application's control, such as malware already running on your computer, a compromised operating system, or someone who already knows your master password. Keeping your own device secure is still your responsibility.</p>
    
    <h2>7. Open Source Transparency</h2>
    <p>This project is open source and released under the MIT License. The full source code is publicly available at <code>https://github.com/Charliezkie-cloud/charlzk-password-manager</code>, so you, or anyone else, can inspect exactly what the Application does with your data. You do not have to take our word for any of the claims in this policy. You can read the code yourself, or ask someone you trust to review it for you.</p>
    
    <h2>8. Backups Are Your Responsibility</h2>
    <p>Since everything is stored locally and there is no cloud sync, there is also no automatic backup of your vault. If your computer's hard drive fails, or the vault file is accidentally deleted, that data cannot be recovered by us, because we never had a copy in the first place. We recommend backing up your encrypted vault file periodically to a location you trust, such as an external drive.</p>
    
    <h2>9. Changes to This Policy</h2>
    <p>If this policy is ever updated, the changes will be reflected in this document and noted in the project's version history on GitHub. Since the Application itself has no way to reach you directly, checking the repository is the only way to know if something has changed.</p>
    
    <h2>10. License</h2>
    <p>This project is distributed under the MIT License. You are free to use, modify, and distribute the software, provided that the original license and copyright notice are included. As with most open source software distributed under MIT, it is provided "as is," without warranty of any kind.</p>
    
    <h2>11. Contact</h2>
    <p>If you have questions about this policy or the project in general, the best place to reach out is through the GitHub repository at <code>https://github.com/Charliezkie-cloud/charlzk-password-manager</code>, either by opening an issue or checking the contact details listed there.</p>
    
    </body>
    </html>
    """;
  public PrivacyView() {
    setGlobalFont(new Font("Segoe UI", Font.PLAIN, 14));

    setTitle("Charlzk Password Manager - Privacy");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);

    // ========== START OF COMPONENTS ==========

    JPanel mainContent = new JPanel(new BorderLayout(10, 10));
    mainContent.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    JEditorPane contentArea = new JEditorPane("text/html", html);
    contentArea.setEditable(false);
    contentArea.setOpaque(false);
    contentArea.setBorder(null);
    contentArea.setFocusable(false);

    JScrollPane scrollPane = new JScrollPane(contentArea);
    scrollPane.setPreferredSize(new Dimension(500, 400));
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    closeButton.addActionListener(e -> dispose());

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(closeButton);

    mainContent.add(scrollPane, BorderLayout.CENTER);
    mainContent.add(buttonPanel, BorderLayout.SOUTH);

    // ========== END OF COMPONENTS ==========

    add(mainContent);
    pack();
    setLocationRelativeTo(null);
  }
}
