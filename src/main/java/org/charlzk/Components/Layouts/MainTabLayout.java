package org.charlzk.Components.Layouts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainTabLayout extends JPanel {
  // Table data model and tables
  private static final String[] foldersTableColumns = {"Folder ID", "Folder Name", "Created At"};
  public static final DefaultTableModel foldersTableModel = new DefaultTableModel(foldersTableColumns, 0) {
    @Override
    public boolean isCellEditable(int row, int column) {
      return false;
    }
  };
  public static final JTable foldersTable = new JTable(foldersTableModel);

  private static final String[] passwordsTableColumns = {"Password ID", "Name", "Url", "Created At", "Last Updated"};
  public static final DefaultTableModel passwordsTableModel = new DefaultTableModel(passwordsTableColumns, 0) {
    @Override
    public boolean isCellEditable(int row, int column) {
      return false;
    }
  };
  public static final JTable passwordsTable = new JTable(passwordsTableModel);

  // Context menu and menu items
  public static final JPopupMenu passwordsTableContextMenu = new JPopupMenu();
  public static final JMenuItem updatePasswordMenuItem = new JMenuItem("Edit");
  public static final JMenuItem deletePasswordMenuItem = new JMenuItem("Delete");

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
    JPanel foldersPanel = new JPanel();
    foldersPanel.setLayout(new BoxLayout(foldersPanel, BoxLayout.Y_AXIS));

    foldersTable.removeColumn(foldersTable.getColumnModel().getColumn(0));
    JScrollPane foldersScrollPane = new JScrollPane(foldersTable);
    foldersScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

    JLabel foldersLabel = new JLabel("Folders");
    foldersLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

    foldersPanel.add(foldersLabel);
    foldersPanel.add(Box.createVerticalStrut(4));
    foldersPanel.add(foldersScrollPane);

    // ========== PASSWORDS TABLE =========
    passwordsTableContextMenu.add(updatePasswordMenuItem);
    passwordsTableContextMenu.addSeparator();
    passwordsTableContextMenu.add(deletePasswordMenuItem);

    JPanel passwordsPanel = new JPanel();
    passwordsPanel.setLayout(new BoxLayout(passwordsPanel, BoxLayout.Y_AXIS));

    passwordsTable.removeColumn(passwordsTable.getColumnModel().getColumn(0));
    JScrollPane passwordsScrollPane = new JScrollPane(passwordsTable);
    passwordsScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

    JLabel passwordsLabel = new JLabel("Passwords");
    passwordsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

    passwordsPanel.add(passwordsLabel);
    passwordsPanel.add(Box.createVerticalStrut(4));
    passwordsPanel.add(passwordsScrollPane);

    // ========== CENTER PANEL ==========
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout(1, 2, 8, 0));
    centerPanel.add(foldersPanel);
    centerPanel.add(passwordsPanel);

    // ========== BOTTOM PANEL ==========
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    bottomPanel.add(addFolderButton);
    bottomPanel.add(addPasswordButton);

    add(searchPanel, BorderLayout.NORTH);
    add(centerPanel, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.SOUTH);
  }
}
