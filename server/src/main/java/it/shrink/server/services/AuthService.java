package it.shrink.server.services;

import com.fasterxml.jackson.core.type.TypeReference;
import it.shrink.server.dtos.request.AuthenticationRequestDTO;
import it.shrink.server.dtos.response.AuthenticationResponseDTO;
import it.shrink.server.entities.User;
import it.shrink.server.repositories.UserRepository;
import it.shrink.server.services.security.JwtService;
import it.shrink.server.utils.Converter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

  final UserRepository userRepository;

  final PasswordEncoder passwordEncoder;

  final JwtService jwtService;

  final Converter converter;

  public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDTO) {
    Optional<User> user = userRepository.getUserByUsername(authenticationRequestDTO.getUsername());

    if (user.isEmpty()) {
      return new AuthenticationResponseDTO(false, null, null);
    }

    if (passwordEncoder.matches(authenticationRequestDTO.getPassword(), user.get().getPassword())) {
      return new AuthenticationResponseDTO(
          true,
          converter.convert(user.get(), new TypeReference<>() {}),
          jwtService.generateToken(converter.convert(user.get(), new TypeReference<>() {})));
    }

    return new AuthenticationResponseDTO(false, null, null);
  }
}
