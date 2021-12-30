package com.example.biedaolx_api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditPostDto {
    private Long id;
    private String title;
    private String body;
    private Integer price;
    private String postCategory;

}

