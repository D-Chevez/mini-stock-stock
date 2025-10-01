package org.kodigo.mini_stock_sytem.web.dto.supplier;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequest {
    private String name;
    private String phone;
    private String email;
}

