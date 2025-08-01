package com.emarket.product_service.product;

import com.emarket.product_service.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String description;

    private int availableQuantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    /*
        @CreatedDate
        @Column(nullable = false, updatable = false)
        private LocalDateTime createdAt;
    */

}
