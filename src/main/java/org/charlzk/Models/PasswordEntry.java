package org.charlzk.Models;

public class PasswordEntry {
  private final int entryId;
  private final int folderId;
  private final int userId;

  private final String title;
  private final String username;
  private final String password;
  private final String url;

  private final long createdAt;
  private final long updatedAt;

  // Relationships
  private final Folder folder;
  private final User user;

  public PasswordEntry(
          int entryId,
          int folderId,
          int userId,

          String title,
          String username,
          String password,
          String url,

          long createdAt,
          long updatedAt,

          Folder folder,
          User user
  ) {
    this.entryId = entryId;
    this.folderId = folderId;
    this.userId = userId;

    this.title = title;
    this.username = username;
    this.password = password;
    this.url = url;

    this.createdAt = createdAt;
    this.updatedAt = updatedAt;

    this.folder = folder;
    this.user = user;
  }

  public int getEntryId() { return entryId; }
  public int getFolderId() { return folderId; }
  public int getUserId() { return userId; }

  public String getTitle() { return title; }
  public String getUsername() { return username; }
  public String getPassword() { return password; }
  public String getUrl() { return url; }

  public long getCreatedAt() { return createdAt; }
  public long getUpdatedAt() { return updatedAt; }

  public Folder getFolder() { return folder; }
  public User getUser() { return user; }
}
