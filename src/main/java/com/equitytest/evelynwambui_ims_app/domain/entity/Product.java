package com.equitytest.evelynwambui_ims_app.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;
    private String tag;
    private boolean deleted = false; // For soft delete

}
