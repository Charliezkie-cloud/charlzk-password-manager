package org.charlzk.Models;

public class User {
  private final int userId;
  private final String email;
  private final String username;
  private final String passwordHash;
  private final long createdAt;
  private final long updatedAt;

  public User(int userId, String email, String username, String passwordHash, long createdAt, Long updatedAt) {
    this.userId = userId;
    this.email = email;
    this.username = username;
    this.passwordHash = passwordHash;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public int getUserId() { return userId; }
  public String getEmail() { return email; }
  public String getUsername() { return username; }
  public String getPasswordHash() { return passwordHash; }
  public long getCreatedAt() { return createdAt; }
  public Long getUpdatedAt() { return updatedAt; }
}