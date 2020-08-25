package ru.monsterdev.tools;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.monsterdev.model.TradeInfo;

@Slf4j
@Service
@Scope(value = "prototype")
@RequiredArgsConstructor
public class WorkThread extends Thread {

  @Value("${app.hub}")
  private String hubUrl;

  private final RestTemplate client;

  @Override
  public void run() {
    try {
      long before = System.currentTimeMillis();
      ResponseEntity<TradeInfo[]> tradeInfos = client.getForEntity(hubUrl, TradeInfo[].class);
      System.out.println(String.format("Thread %s, wait time %02f(s): ", getName(), (System.currentTimeMillis() - before) / 1000.0));
    } catch (Exception ex) {
      log.error("", ex);
    }
  }
}
