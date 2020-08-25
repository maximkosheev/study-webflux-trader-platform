package ru.monsterdev;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

  @Bean
  @Scope(value = "prototype")
  public WebClient webClient(@Value("${app.hub}") String hubUrl) {
    return WebClient.create(hubUrl);
  }
}
