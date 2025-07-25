package it.shrink.server.controllers;

import it.shrink.server.dtos.UserDTO;
import it.shrink.server.dtos.request.UserCreationRequestDTO;
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
  public ResponseEntity<UserDTO> createUser(
      @RequestBody UserCreationRequestDTO userCreationRequestDTO) {
    UserDTO user = userService.createUser(userCreationRequestDTO);
    return new ResponseEntity<>(user, HttpStatus.CREATED);
  }

  @PostMapping("register")
  public ResponseEntity<UserDTO> registerUser(
      @RequestBody UserCreationRequestDTO userCreationRequestDTO) {
    UserDTO response = userService.registerUser(userCreationRequestDTO);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
