package com.example.library.constants;

public enum UserRole {
  USER("USER"),
  ADMIN("ADMIN");

  private String value;

  UserRole(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}