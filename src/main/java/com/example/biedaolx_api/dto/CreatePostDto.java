package com.example.biedaolx_api.dto;

import com.example.biedaolx_api.entity.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePostDto {
    private String title;
    private String body;
    private Integer price;
    private String postCategory;
}
