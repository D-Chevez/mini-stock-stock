package org.kodigo.mini_stock_sytem.web.dto;

import jakarta.persistence.*;
import lombok.*;
import org.kodigo.mini_stock_sytem.model.Category;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String sku;
    private String name;
    private Category category;
    private Integer priceCents;
    private Integer minStock;
    private Integer stock;
    private boolean active;
}
