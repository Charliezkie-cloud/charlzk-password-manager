package org.charlzk.Views.NoAuthViews;

import javax.swing.*;
import java.awt.*;

import static org.charlzk.Services.ApplicationServices.setGlobalFont;

public class TermsOfUseView extends JFrame {
  // Close button
  private static final JButton closeButton = new JButton("Close");

  // Terms of use HTML content (September 2026)
  private final String html = """
    <!DOCTYPE html>
    <html lang="en">
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Terms of Use</title>
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
    
    <h1>Terms of Use</h1>
    <p class="updated"><strong>Last Updated:</strong> September 2026</p>
    
    <p>By using this Password Manager application, you agree to the following terms and conditions.</p>
    
    <h2>1. Local-Only Storage</h2>
    <p>This application stores all user data locally on your device. No data, including passwords or personal information, is transmitted to any external server or third party. The developer does not have access to any stored data.</p>
    
    <h2>2. User Responsibility</h2>
    <p>You are solely responsible for:</p>
    <ul>
      <li>Maintaining the confidentiality of your master password</li>
      <li>Ensuring the security of your device</li>
      <li>Creating backups of your data if needed</li>
    </ul>
    <p>Loss of the master password may result in permanent loss of access to stored data.</p>
    
    <h2>3. No Password Recovery</h2>
    <p>This application does not provide any password recovery mechanism. If the master password is forgotten, the stored data cannot be recovered.</p>
    
    <h2>4. Security Disclaimer</h2>
    <p>While this application uses encryption technologies to protect stored data, no system can be guaranteed to be completely secure. You acknowledge that all use is at your own risk.</p>
    
    <h2>5. Limitation of Liability</h2>
    <p>This software is provided "as is" without warranties of any kind. The developer shall not be held liable for any data loss, security breaches, system failures, or damages arising from the use or inability to use this application.</p>
    
    <h2>6. Proper Use</h2>
    <p>You agree to use the application only for lawful purposes and in a manner that does not harm the system or other users.</p>
    
    <h2>7. Modifications</h2>
    <p>These terms may be updated in future versions of the application. Continued use of the application constitutes acceptance of any changes.</p>
    
    <h2>8. License</h2>
    <p>This project is open source and distributed under the MIT License. You are free to use, modify, and distribute the software, provided that the original license and copyright notice are included. The full source code, along with the license text, is publicly available at <code>https://github.com/Charliezkie-cloud/charlzk-password-manager</code>.</p>
    
    <p>By using this application, you acknowledge that you have read, understood, and agreed to these Terms of Use.</p>
    
    </body>
    </html>
    """;

  public TermsOfUseView() {
    setGlobalFont(new Font("Segoe UI", Font.PLAIN, 14));

    setTitle("Charlzk Password Manager - Terms of Use");
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
