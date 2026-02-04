package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Promotions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Promotion {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "promotion_id")
  private UUID promotionId;

  @Column(name = "code", length = 255, unique = true)
  private String code;

  @Column(name = "discount_type", length = 50)
  private String discountType;

  @Column(name = "value", precision = 19, scale = 2)
  private BigDecimal value;

  @OneToMany(mappedBy = "coupon")
  private Set<Order> orders = new HashSet<>();
}
