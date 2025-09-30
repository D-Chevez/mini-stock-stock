package org.kodigo.mini_stock_sytem.web.dto;

import jakarta.persistence.*;
import lombok.*;
import org.kodigo.mini_stock_sytem.model.Category;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private boolean active;
}
