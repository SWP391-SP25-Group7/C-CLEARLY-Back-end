package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "System_Configs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemConfig {

  @Id
  @Column(name = "config_key", length = 100)
  private String configKey;

  @Column(name = "config_value", columnDefinition = "TEXT")
  private String configValue;

  @Column(name = "config_group", length = 50)
  private String configGroup;
}
