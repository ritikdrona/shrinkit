package it.shrink.server.dtos.request;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {

  String username;

  String password;
}
