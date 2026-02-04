package com.swp391.cclearly.entity;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "Prescriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "prescription_id")
    private UUID prescriptionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(name = "sph_od")
    private Float sphOd;

    @Column(name = "validation_status", length = 50)
    private String validationStatus;

    @Column(name = "sales_note", columnDefinition = "TEXT")
    private String salesNote;
}
