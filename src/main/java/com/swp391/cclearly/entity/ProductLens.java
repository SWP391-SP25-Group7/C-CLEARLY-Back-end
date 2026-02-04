package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Product_Lenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLens {

    @Id
    @Column(name = "product_id")
    private UUID productId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "refractive_index")
    private Float refractiveIndex;

    @Column(name = "lens_type", length = 50)
    private String lensType;

    @ManyToMany
    @JoinTable(
            name = "Product_Lens_Tech_Map",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tech_id")
    )
    private Set<MasterLensTechnology> technologies = new HashSet<>();
}
