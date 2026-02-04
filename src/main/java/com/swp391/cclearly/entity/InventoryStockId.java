package com.swp391.cclearly.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class InventoryStockId implements Serializable {

    @Column(name = "warehouse_id")
    private UUID warehouseId;

    @Column(name = "variant_id")
    private UUID variantId;
}
