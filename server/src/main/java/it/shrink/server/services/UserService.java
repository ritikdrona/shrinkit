package it.shrink.server.services;

import com.fasterxml.jackson.core.type.TypeReference;
import it.shrink.server.dtos.UserDTO;
import it.shrink.server.dtos.request.UserCreationRequestDTO;
import it.shrink.server.entities.User;
import it.shrink.server.enums.UserRole;
import it.shrink.server.repositories.UserRepository;
import it.shrink.server.utils.Converter;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  final UserRepository userRepository;

  final PasswordEncoder passwordEncoder;

  final Converter converter;

  public UserDTO createUser(UserCreationRequestDTO userCreationRequestDTO) {
    Optional<User> existingUser =
        userRepository.getUserByUsername(userCreationRequestDTO.getUsername());
    if (existingUser.isPresent()) {
      throw new RuntimeException("User already exists with username");
    }

    String hashedPassword = passwordEncoder.encode(userCreationRequestDTO.getPassword());

    User newUser =
        new User(
            userCreationRequestDTO.getRole(),
            userCreationRequestDTO.getName(),
            userCreationRequestDTO.getUsername(),
            hashedPassword);
    userRepository.save(newUser);

    return converter.convert(newUser, new TypeReference<>() {});
  }

  public UserDTO registerUser(UserCreationRequestDTO userCreationRequestDTO) {
    userCreationRequestDTO.setRole(UserRole.NORMAL_USER);
    return createUser(userCreationRequestDTO);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.getUserByUsername(username);
    if (user.isPresent()) {
      return user.get();
    } else {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
  }
}
