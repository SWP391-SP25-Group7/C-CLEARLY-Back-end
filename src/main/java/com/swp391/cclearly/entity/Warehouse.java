package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "Warehouses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "warehouse_id")
  private UUID warehouseId;

  @Column(name = "name", length = 255)
  private String name;

  @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
  private Set<InventoryStock> inventoryStocks = new HashSet<>();
}
