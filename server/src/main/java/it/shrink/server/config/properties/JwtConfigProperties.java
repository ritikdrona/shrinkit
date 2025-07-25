package it.shrink.server.config.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationPropertiesScan
@ConfigurationProperties("jwt")
public class JwtConfigProperties {
  @NotBlank
  @Length(min = 32, max = 64)
  String secretKey;

  int expiration;
}
