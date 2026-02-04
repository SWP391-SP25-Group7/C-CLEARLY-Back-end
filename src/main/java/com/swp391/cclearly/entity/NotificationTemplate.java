package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Notification_Templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationTemplate {

  @Id
  @Column(name = "template_code", length = 255)
  private String templateCode;

  @Column(name = "body_html", columnDefinition = "TEXT")
  private String bodyHtml;
}
