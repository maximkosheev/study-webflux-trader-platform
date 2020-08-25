package ru.monsterdev.services.impl;

import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.monsterdev.domain.Trade;
import ru.monsterdev.model.TradeInfo;
import ru.monsterdev.repositories.TradeRepository;
import ru.monsterdev.services.TradeService;

@Service
public class TradeServiceImpl implements TradeService {

  @Autowired
  private TradeRepository tradeRepository;

  @Value("${app.services.remote}")
  private String tradeInfoUrl;

  private WebClient client;

  @Override
  public Flux<TradeInfo> findAll() {
    client = WebClient.create(tradeInfoUrl);

    List<Mono<TradeInfo>> list = tradeRepository.findAll().stream()
        .map(this::getTradeInfo)
        .collect(Collectors.toList());

    return Flux.merge(list);
  }

  private Mono<TradeInfo> getTradeInfo(Trade trade) {
    return client.get()
        .uri(uriBuilder -> uriBuilder.path("/trade/{id}").build(trade.getTradeId()))
        .accept(MediaType.APPLICATION_STREAM_JSON)
        .exchange()
        .flatMap(response -> response.bodyToMono(TradeInfo.class));
  }
}
