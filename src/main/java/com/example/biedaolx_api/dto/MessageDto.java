package com.example.biedaolx_api.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MessageDto {
    private String creatorUsername;
    private String recipientUsername;
    private String body;
    private LocalDateTime dateOfCreation;
}
