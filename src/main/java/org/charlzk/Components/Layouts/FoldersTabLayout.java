package org.charlzk.Components.Layouts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FoldersTabLayout extends JPanel {
  // Table data model and tables
  private static final String[] foldersTableColumns = {"Folder ID", "Folder Name", "Created At"};
  public static final DefaultTableModel foldersTableModel = new DefaultTableModel(foldersTableColumns, 0) {
    @Override
    public boolean isCellEditable(int row, int column) {
      return false;
    }
  };
  public static final JTable foldersTable = new JTable(foldersTableModel);

  // Fields
  public static final JTextField searchField = new JTextField();

  // Context menu and menu items
  public static final JPopupMenu foldersTableContextMenu = new JPopupMenu();
  public static final JMenuItem updateFolderMenuItem = new JMenuItem("Edit");
  public static final JMenuItem deleteFolderMenuItem = new JMenuItem("Delete");

  // Buttons
  public static final JButton searchButton = new JButton("Search");
  public static final JButton addFolderButton = new JButton("Add Folder");

  public FoldersTabLayout() {
    setLayout(new BorderLayout(0, 8));
    setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    // ========== SEARCH PANEL ==========
    JPanel searchPanel = new JPanel(new BorderLayout(4, 4));

    searchPanel.add(searchField, BorderLayout.CENTER);
    searchPanel.add(searchButton, BorderLayout.EAST);

    // ========== FOLDERS TABLE ==========
    foldersTableContextMenu.add(updateFolderMenuItem);
    foldersTableContextMenu.addSeparator();
    foldersTableContextMenu.add(deleteFolderMenuItem);

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

    // ========== CENTER PANEL ==========
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BorderLayout());
    centerPanel.add(foldersPanel, BorderLayout.CENTER);

    // ========== BOTTOM PANEL ==========
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    bottomPanel.add(addFolderButton);

    add(searchPanel, BorderLayout.NORTH);
    add(centerPanel, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.SOUTH);
  }
}
