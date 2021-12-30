package com.example.biedaolx_api.dto;

import com.example.biedaolx_api.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendMessageDto {
    private String message;
    private String recipientUsername;
}
