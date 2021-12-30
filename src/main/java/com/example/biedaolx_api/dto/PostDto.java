package com.example.biedaolx_api.dto;

import com.example.biedaolx_api.entity.PostCategory;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDto {
    private Long id;
    private String creatorUsername;
    private String title;
    private String body;
    private Integer price;
    private LocalDateTime dateOfCreation;
    private String postCategory;
}
