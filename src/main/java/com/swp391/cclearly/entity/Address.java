package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "address_id")
  private UUID addressId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "street", length = 255)
  private String street;

  @Column(name = "city", length = 100)
  private String city;

  @Column(name = "is_default")
  private Boolean isDefault;

  @OneToMany(mappedBy = "address")
  private Set<Order> orders = new HashSet<>();
}
