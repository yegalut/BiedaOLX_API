package com.example.biedaolx_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateUserDto {
    private String name;
    private String username;
    private String password;
}
