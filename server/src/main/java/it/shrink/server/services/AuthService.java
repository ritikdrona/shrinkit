package it.shrink.server.services;

import com.fasterxml.jackson.core.type.TypeReference;
import it.shrink.server.dtos.request.AuthenticationRequestDTO;
import it.shrink.server.dtos.response.AuthenticationResponseDTO;
import it.shrink.server.entities.User;
import it.shrink.server.repositories.UserRepository;
import it.shrink.server.utils.Converter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  final UserRepository userRepository;

  final PasswordEncoder passwordEncoder;

  public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDTO) {
    Optional<User> user = userRepository.getUserByUsername(authenticationRequestDTO.getUsername());

    if (user.isEmpty()) {
      return new AuthenticationResponseDTO(false, null, null);
    }

    String hashedInputPassword = passwordEncoder.encode(authenticationRequestDTO.getPassword());

    if (user.get().getPassword().equals(hashedInputPassword)) {
      return new AuthenticationResponseDTO(
          true, Converter.convert(user.get(), new TypeReference<>() {}), null);
    }

    return new AuthenticationResponseDTO(false, null, null);
  }
}
