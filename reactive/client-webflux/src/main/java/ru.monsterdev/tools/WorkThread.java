package ru.monsterdev.tools;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.monsterdev.model.TradeInfo;

@Slf4j
@Service
@Scope(value = "prototype")
@RequiredArgsConstructor
public class WorkThread extends Thread {

  private final WebClient webClient;

  @Override
  public void run() {
    try {
      long before = System.currentTimeMillis();
      webClient.get().uri(uriBuilder -> uriBuilder.path("/all")
          .build())
          .exchange()
          .flatMapMany(res -> res.bodyToFlux(TradeInfo.class))
          .blockLast();

      System.out.println(String.format("Thread %s, wait type %02f(s): ", getName(), (System.currentTimeMillis() - before) / 1000.0));
    } catch (Exception ex) {
      log.error("", ex);
    }
  }
}
