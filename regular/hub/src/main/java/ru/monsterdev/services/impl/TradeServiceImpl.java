package ru.monsterdev.services.impl;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.monsterdev.domain.Trade;
import ru.monsterdev.model.TradeInfo;
import ru.monsterdev.repositories.TradeRepository;
import ru.monsterdev.services.TradeService;

@Service
@RequiredArgsConstructor
public class TradeServiceImpl implements TradeService {
  private final TradeRepository tradeRepository;
  private final RestTemplate restTemplate;

  @Value("${app.services.remote}")
  private String tradeInfoUrl;

  @Override
  public List<Trade> findAll() {
    List<Trade> trades = tradeRepository.findAll();
    for (Trade trade : trades) {
      TradeInfo tradeInfo = restTemplate.getForObject(tradeInfoUrl, TradeInfo.class, trade.getTradeId());
      if (Objects.nonNull(tradeInfo)) {
        trade.setName(tradeInfo.getName());
        trade.setMinPrice(tradeInfo.getMinPrice());
        trade.setApplicantsCount(tradeInfo.getApplicantsCount());
      }
    }
    return tradeRepository.findAll();
  }
}
