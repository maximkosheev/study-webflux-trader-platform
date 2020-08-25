package ru.monsterdev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

  @Bean
  @Scope(value = "prototype")
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
