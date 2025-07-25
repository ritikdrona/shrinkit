package it.shrink.server.dtos.request;

import it.shrink.server.dtos.UserDTO;
import lombok.Data;

@Data
public class UserCreationRequestDTO extends UserDTO {

  String password;
}
