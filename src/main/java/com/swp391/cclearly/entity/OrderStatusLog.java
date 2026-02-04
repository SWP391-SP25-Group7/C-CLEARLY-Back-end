package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Order_Status_Logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusLog {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "log_id")
  private UUID logId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "new_status", length = 50)
  private String newStatus;

  @Column(name = "note", columnDefinition = "TEXT")
  private String note;
}
