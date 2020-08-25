package ru.monsterdev.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.monsterdev.model.TradeInfo;

@RestController
@RequestMapping
@Slf4j
public class MainController {

  @GetMapping("/trade/{id}")
  public ResponseEntity<TradeInfo> getTradeInfo(@PathVariable(name = "id") Long tradeId)  {
    try {
      TradeInfo tradeInfo = new TradeInfo();
      tradeInfo.setId(tradeId);
      tradeInfo.setName(String.format("TradeName-%d", tradeId));
      tradeInfo.setMinPrice(10000);
      tradeInfo.setApplicantsCount(5);
      Thread.sleep(5000);
      return ResponseEntity.ok(tradeInfo);
    } catch (Exception ex) {
      log.error("", ex);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
