package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "role_id")
  private UUID roleId;

  @Column(name = "role_name", length = 50)
  private String roleName;

  @Column(name = "description", length = 255)
  private String description;

  @OneToMany(mappedBy = "role")
  private Set<User> users = new HashSet<>();

  @ManyToMany
  @JoinTable(
      name = "Role_Permissions",
      joinColumns = @JoinColumn(name = "role_id"),
      inverseJoinColumns = @JoinColumn(name = "permission_id"))
  private Set<Permission> permissions = new HashSet<>();
}
