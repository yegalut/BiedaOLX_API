package com.example.biedaolx_api.service;

import com.example.biedaolx_api.entity.Message;
import com.example.biedaolx_api.repository.MessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepo messageRepo;


}
