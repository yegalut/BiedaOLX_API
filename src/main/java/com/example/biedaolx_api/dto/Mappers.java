package com.example.biedaolx_api.dto;

import com.example.biedaolx_api.entity.Message;
import com.example.biedaolx_api.entity.Post;
import com.example.biedaolx_api.entity.User;

public class Mappers {
    private Mappers() {
    }

    public static PostDto mapToPostDto(Post post){
        return PostDto.builder()
                .creatorUsername(post.getUser().getUsername())
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .price(post.getPrice())
                .dateOfCreation(post.getDateOfCreation())
                .postCategory(post.getPostCategory().getName())
                .build();
    }

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }

    public static MessageDto mapToMessageDto(Message message) {
        return MessageDto.builder()
                .creatorUsername(message.getCreator().getUsername())
                .recipientUsername(message.getRecipientUsername())
                .body(message.getBody())
                .dateOfCreation(message.getDateOfCreation())
                .build();
    }
}
