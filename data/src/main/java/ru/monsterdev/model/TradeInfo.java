package ru.monsterdev.model;

import lombok.Data;

@Data
public class TradeInfo {
  private Long id;
  private String name;
  private Integer minPrice;
  private Integer applicantsCount;
}

