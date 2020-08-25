package ru.monsterdev.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.monsterdev.mappers.TradeMapper;
import ru.monsterdev.model.TradeInfo;
import ru.monsterdev.services.TradeService;

@RestController
@RequestMapping
public class MainController {

  @Autowired
  private TradeService tradeService;
  @Autowired
  private TradeMapper tradeMapper;

  @GetMapping("/all")
  public ResponseEntity<List<TradeInfo>> findAllTrades() {
    return ResponseEntity.ok(tradeService.findAll().stream()
        .map(trade -> tradeMapper.mapToModel(trade))
        .collect(Collectors.toList()));
  }
}
