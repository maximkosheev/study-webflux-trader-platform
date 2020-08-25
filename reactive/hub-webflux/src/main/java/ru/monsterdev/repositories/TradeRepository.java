package ru.monsterdev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.monsterdev.domain.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {

}
