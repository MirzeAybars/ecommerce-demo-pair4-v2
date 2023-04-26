package com.etiya.ecommercedemopair4.business.dtos.responses.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListProductResponse {
    private int id;
    private String name;
    private String description;
    private String supplierName;
}
