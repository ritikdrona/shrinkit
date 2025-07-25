package it.shrink.server.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
  NORMAL_USER("NORMAL_USER"),
  ADMIN("ADMIN");

  final String value;
}
