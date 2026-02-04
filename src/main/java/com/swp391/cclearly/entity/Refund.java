package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Refunds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refund {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "refund_id")
  private UUID refundId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @Column(name = "amount", precision = 19, scale = 2)
  private BigDecimal amount;

  @Column(name = "reason", columnDefinition = "TEXT")
  private String reason;

  @Column(name = "status", length = 50)
  private String status;

  @Column(name = "created_at")
  private Instant createdAt;
}
