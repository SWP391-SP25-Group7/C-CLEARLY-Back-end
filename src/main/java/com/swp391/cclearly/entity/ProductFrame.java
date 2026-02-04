package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "Product_Frames")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductFrame {

  @Id
  @Column(name = "product_id")
  private UUID productId;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(name = "material", length = 100)
  private String material;

  @Column(name = "shape", length = 50)
  private String shape;

  @Column(name = "total_width_mm")
  private Integer totalWidthMm;

  @Column(name = "lens_width_mm")
  private Integer lensWidthMm;

  @Column(name = "bridge_width_mm")
  private Integer bridgeWidthMm;
}
