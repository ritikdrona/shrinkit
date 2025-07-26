package it.shrink.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  //  final AuthenticationProvider authenticationProvider;

  //  final JwtAuthorizationFilter jwtAuthorizationFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //    http.csrf(AbstractHttpConfigurer::disable)
    //        .authorizeHttpRequests(
    //            auth ->
    // auth.requestMatchers("/authenticate").permitAll().anyRequest().authenticated())
    //        .sessionManagement(
    //            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    //        .authenticationProvider(authenticationProvider)
    //        .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
    return http.build();
  }

  //  @Bean
  //  public AuthenticationProvider authenticationProvider() {
  //    DaoAuthenticationProvider authenticationProvider =
  //        new DaoAuthenticationProvider();
  //    authenticationProvider.setUserDetailsPasswordService(userDetailsService());
  //    authenticationProvider.setPasswordEncoder(passwordEncoder());
  //    return new CustomAuthenticationProvider();
  //  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
