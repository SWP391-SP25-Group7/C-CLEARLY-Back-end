package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Master_Lens_Technologies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterLensTechnology {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tech_id")
    private UUID techId;

    @Column(name = "name", length = 255)
    private String name;

    @ManyToMany(mappedBy = "technologies")
    private Set<ProductLens> productLenses = new HashSet<>();
}
