package ru.monsterdev.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.monsterdev.model.TradeInfo;

@RestController
@RequestMapping
@Slf4j
public class MainController {

  @GetMapping(value = "/trade/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Mono<TradeInfo> getTradeInfo(@PathVariable(name = "id") Long tradeId)  {
    try {
      log.info("Request for trade {} info", tradeId);
      TradeInfo tradeInfo = new TradeInfo();
      tradeInfo.setId(tradeId);
      tradeInfo.setName(String.format("TradeName-%d", tradeId));
      tradeInfo.setMinPrice(10000);
      tradeInfo.setApplicantsCount(5);
      Thread.sleep(1000);
      return Mono.just(tradeInfo);
    } catch (Exception ex) {
      log.error("", ex);
      return Mono.error(ex);
    }
  }
}
