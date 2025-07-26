package it.shrink.server.controllers;

import it.shrink.server.dtos.request.ApiSingleRequest;
import it.shrink.server.dtos.request.AuthenticationRequestDTO;
import it.shrink.server.dtos.response.ApiSingleResponse;
import it.shrink.server.dtos.response.AuthenticationResponseDTO;
import it.shrink.server.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

  final AuthService authService;

  @PostMapping("/authenticate")
  public ResponseEntity<ApiSingleResponse<AuthenticationResponseDTO>> authenticateUser(
      @RequestBody ApiSingleRequest<AuthenticationRequestDTO> authenticationRequestDTO) {
    AuthenticationResponseDTO response =
        authService.authenticate(authenticationRequestDTO.getRequest());
    return new ResponseEntity<>(new ApiSingleResponse<>(response), HttpStatus.OK);
  }
}
