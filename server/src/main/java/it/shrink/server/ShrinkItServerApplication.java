package it.shrink.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class ShrinkItServerApplication {

  public static void main(String[] args) {
    // Not passing args for security concerns
    SpringApplication.run(ShrinkItServerApplication.class);
  }
}
