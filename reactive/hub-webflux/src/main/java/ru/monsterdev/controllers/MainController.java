package ru.monsterdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.monsterdev.model.TradeInfo;
import ru.monsterdev.services.TradeService;

@RestController
@RequestMapping()
public class MainController {

  @Autowired
  private TradeService tradeService;

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
  public Flux<TradeInfo> getAll() {
    return tradeService.findAll();
  }

  @GetMapping("/test")
  public Flux<String> getTest() {
    return Flux.just("First", "Second", "Third", "Forth");
  }
}
