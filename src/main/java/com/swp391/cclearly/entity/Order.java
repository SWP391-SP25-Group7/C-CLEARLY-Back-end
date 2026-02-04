package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "order_id")
  private UUID orderId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "code", length = 20)
  private String code;

  @Column(name = "status", length = 50)
  private String status;

  @Column(name = "final_amount", precision = 19, scale = 2)
  private BigDecimal finalAmount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "coupon_code", referencedColumnName = "code")
  private Promotion coupon;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "address_id")
  private Address address;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private Set<OrderItem> orderItems = new HashSet<>();

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private Set<Payment> payments = new HashSet<>();

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private Set<Refund> refunds = new HashSet<>();

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private Set<OrderStatusLog> statusLogs = new HashSet<>();
}
