package it.shrink.server.dtos;

import it.shrink.server.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

  String id;

  UserRole role;

  String name;

  String username;
}
