package com.example.biedaolx_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostFilters {
    private String keyword ="";
    private String category ="";
    private String maxPrice = "" ;
    private String minPrice = "";
}
