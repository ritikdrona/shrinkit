package it.shrink.server.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwe;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import it.shrink.server.config.properties.JwtConfigProperties;
import it.shrink.server.dtos.UserDTO;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

  final JwtConfigProperties jwtConfigProperties;

  public String generateToken(UserDTO userDTO) {
    Instant now = Instant.now();

    Map<String, Object> claims = new HashMap<>();
    claims.put("user", userDTO);

    return Jwts.builder()
        .header()
        .add("issuer", "ShrinkIt")
        .and()
        .subject(userDTO.getId())
        .claims(claims)
        .issuedAt(Date.from(now))
        .expiration(Date.from(now.plusSeconds(jwtConfigProperties.getExpiration() * 3600L)))
        .encryptWith(getEncryptionKey(), Jwts.ENC.A128CBC_HS256)
        .compact();
  }

  public String extractUserId(String token) {
    Jwe<Claims> jwe =
        Jwts.parser().decryptWith(getEncryptionKey()).build().parseEncryptedClaims(token);
    return jwe.getPayload().getSubject();
  }

  private SecretKey getEncryptionKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtConfigProperties.getSecretKey());
    // TODO: remove this after testing
    log.debug("Secret Key Byte Length: {}", keyBytes.length);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
