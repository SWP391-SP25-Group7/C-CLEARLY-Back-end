package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "email", length = 255)
  private String email;

  @Column(name = "password_hash", length = 255)
  private String passwordHash;

  @Column(name = "full_name", length = 100)
  private String fullName;

  @Column(name = "phone_number", length = 20)
  private String phoneNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id")
  private Role role;

  @Column(name = "status", length = 50)
  private String status;

  @Column(name = "is_email_verified")
  private Boolean isEmailVerified;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "last_login")
  private Instant lastLogin;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Customer customer;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private StaffProfile staffProfile;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Address> addresses = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<EmailVerification> emailVerifications = new HashSet<>();

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Cart cart;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<Order> orders = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<OrderStatusLog> orderStatusLogs = new HashSet<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private Set<AuditLog> auditLogs = new HashSet<>();
}
