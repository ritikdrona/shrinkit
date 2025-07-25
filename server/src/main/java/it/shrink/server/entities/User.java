package it.shrink.server.entities;

import it.shrink.server.enums.UserRole;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.Collections;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(generator = "uuid")
  @UuidGenerator
  String id;

  @Enumerated(EnumType.STRING)
  UserRole role;

  String name;

  String username;

  String password;

  public User(UserRole role, String name, String username, String password) {
    this.role = role;
    this.name = name;
    this.username = username;
    this.password = password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(() -> role.getValue());
  }
}
