package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Inventory_Stock")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryStock {

  @EmbeddedId private InventoryStockId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("warehouseId")
  @JoinColumn(name = "warehouse_id")
  private Warehouse warehouse;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("variantId")
  @JoinColumn(name = "variant_id")
  private ProductVariant variant;

  @Column(name = "quantity_on_hand")
  private Integer quantityOnHand;

  @Column(name = "location_code", length = 255)
  private String locationCode;
}
