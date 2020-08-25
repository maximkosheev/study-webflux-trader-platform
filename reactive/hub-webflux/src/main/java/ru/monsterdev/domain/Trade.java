package ru.monsterdev.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "trade")
@Getter
@Setter
public class Trade {
  @Id
  private Long id;

  @Column(name = "tradeId")
  private Long tradeId;

  @Transient
  private String name;

  @Transient
  private Integer minPrice;

  @Transient
  private Integer applicantsCount;
}
