package ru.monsterdev.mappers;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ru.monsterdev.domain.Trade;
import ru.monsterdev.model.TradeInfo;

@Component
public class TradeMapper {

  public TradeInfo mapToModel(@NonNull Trade trade) {
    TradeInfo tradeInfo = new TradeInfo();
    tradeInfo.setId(trade.getTradeId());
    tradeInfo.setName(trade.getName());
    tradeInfo.setMinPrice(trade.getMinPrice());
    tradeInfo.setApplicantsCount(trade.getApplicantsCount());
    return tradeInfo;
  }
}
