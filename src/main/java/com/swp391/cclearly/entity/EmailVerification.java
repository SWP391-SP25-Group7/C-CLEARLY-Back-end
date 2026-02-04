package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Email_Verifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailVerification {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "verification_id")
  private UUID verificationId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "otp_code", length = 10)
  private String otpCode;

  @Column(name = "expired_at")
  private Instant expiredAt;

  @Column(name = "verified")
  private Boolean verified;
}
