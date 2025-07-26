package it.shrink.server.controllers;

import it.shrink.server.dtos.UserDTO;
import it.shrink.server.dtos.request.ApiSingleRequest;
import it.shrink.server.dtos.request.UserCreationRequestDTO;
import it.shrink.server.dtos.response.ApiSingleResponse;
import it.shrink.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "users")
@RequiredArgsConstructor
public class UserController {

  final UserService userService;

  @PostMapping
  public ResponseEntity<ApiSingleResponse<UserDTO>> createUser(
      @RequestBody ApiSingleRequest<UserCreationRequestDTO> userCreationRequestDTO) {
    UserDTO user = userService.createUser(userCreationRequestDTO.getRequest());
    return new ResponseEntity<>(new ApiSingleResponse<>(user), HttpStatus.CREATED);
  }

  @PostMapping("register")
  public ResponseEntity<ApiSingleResponse<UserDTO>> registerUser(
      @RequestBody ApiSingleRequest<UserCreationRequestDTO> userCreationRequestDTO) {
    UserDTO response = userService.registerUser(userCreationRequestDTO.getRequest());
    return new ResponseEntity<>(new ApiSingleResponse<>(response), HttpStatus.OK);
  }
}
