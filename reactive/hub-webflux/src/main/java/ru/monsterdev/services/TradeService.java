package ru.monsterdev.services;

import reactor.core.publisher.Flux;
import ru.monsterdev.model.TradeInfo;

public interface TradeService {
  /**
   * Возвращает список всех закупок
   * @return
   */
  Flux<TradeInfo> findAll();
}
