package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Product_Variants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "variant_id")
    private UUID variantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "sku", length = 50)
    private String sku;

    @Column(name = "color_name", length = 50)
    private String colorName;

    @Column(name = "sale_price", precision = 19, scale = 2)
    private BigDecimal salePrice;

    @Column(name = "is_preorder")
    private Boolean isPreorder;

    @Column(name = "expected_availability")
    private LocalDate expectedAvailability;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private Set<ProductImage> images = new HashSet<>();

    @OneToMany(mappedBy = "variant")
    private Set<CartItem> cartItems = new HashSet<>();

    @OneToMany(mappedBy = "variant")
    private Set<InventoryStock> inventoryStocks = new HashSet<>();

    @OneToMany(mappedBy = "variant")
    private Set<StockMovement> stockMovements = new HashSet<>();
}
