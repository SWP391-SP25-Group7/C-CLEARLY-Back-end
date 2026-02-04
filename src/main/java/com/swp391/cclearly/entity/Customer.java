package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

  @Id
  @Column(name = "user_id")
  private UUID userId;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "loyalty_points")
  private Integer loyaltyPoints;

  @Column(name = "membership_level", length = 50)
  private String membershipLevel;

  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;
}
