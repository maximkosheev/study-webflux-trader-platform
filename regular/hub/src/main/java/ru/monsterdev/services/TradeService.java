package ru.monsterdev.services;

import java.util.List;
import ru.monsterdev.domain.Trade;

public interface TradeService {

  /**
   * Возвращает список всех закупок
   * @return
   */
  List<Trade> findAll();
}
