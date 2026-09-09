package org.charlzk.Components.Layouts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainTabLayout extends JPanel {

  // Table data model
  private static final String[] foldersTableColumns = {"Folder Name", "Last Updated", "Date Updated"};
  public static final DefaultTableModel foldersTableModel = new DefaultTableModel(foldersTableColumns, 0);

  private static final String[] passwordsTableColumns = {"Title", "Username", "Url", "Last Updated", "Date Updated"};
  public static final DefaultTableModel passwordsTableModel = new DefaultTableModel(passwordsTableColumns, 0);

  // Fields
  public static final JTextField searchField = new JTextField();

  // ComboBox
  private static final String[] searchOptions = {"Folder", "Password"};
  public static final JComboBox<String> searchOptionComboBox = new JComboBox<>(searchOptions);

  // Buttons
  public static final JButton searchButton = new JButton("Search");
  public static final JButton addPasswordButton = new JButton("Add Password");
  public static final JButton addFolderButton = new JButton("Add Folder");

  public MainTabLayout() {
    setLayout(new BorderLayout(0, 8));
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    // ========== SEARCH PANEL ==========
    JPanel searchPanel = new JPanel(new BorderLayout(4, 4));
    JPanel searchButtonsPanel = new JPanel(new BorderLayout(2, 2));

    searchButtonsPanel.add(searchButton, BorderLayout.WEST);
    searchButtonsPanel.add(searchOptionComboBox, BorderLayout.EAST);

    searchPanel.add(searchField, BorderLayout.CENTER);
    searchPanel.add(searchButtonsPanel, BorderLayout.EAST);

    // ========== FOLDERS TABLE ==========
    JTable foldersTable = new JTable(foldersTableModel);
    JScrollPane foldersScrollPane = new JScrollPane(foldersTable);

    // ========== PASSWORDS TABLE ==========
    JTable passwordsTable = new JTable(passwordsTableModel);
    JScrollPane passwordsScrollPane = new JScrollPane(passwordsTable);

    // ========== CENTER PANEL ==========
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout(1, 2, 8, 0));
    centerPanel.add(foldersScrollPane);
    centerPanel.add(passwordsScrollPane);

    // ========== BOTTOM PANEL ==========
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    bottomPanel.add(addPasswordButton);
    bottomPanel.add(addFolderButton);

    add(searchPanel, BorderLayout.NORTH);
    add(centerPanel, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.SOUTH);
  }
}
