package org.kodigo.mini_stock_sytem.web.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private boolean active;


}
