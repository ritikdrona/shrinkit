package it.shrink.server.dtos.response;

import it.shrink.server.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponseDTO {

  boolean success;

  UserDTO user;

  String token;
}
