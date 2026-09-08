package org.charlzk.Models;

public class Folder {
  private final int folderId;
  private final int userId;
  private final String name;
  private final long createdAt;

  // Relationships
  private final User user;

  public Folder(int folderId, int userId, String name, long createdAt, User user) {
    this.folderId = folderId;
    this.userId = userId;
    this.name = name;
    this.createdAt = createdAt;
    this.user = user;
  }

  public int getFolderId() { return folderId; }
  public int getUserId() { return userId; }
  public String getName() { return name; }
  public long getCreatedAt() { return createdAt; }
  public User getUser() { return user; }
}
