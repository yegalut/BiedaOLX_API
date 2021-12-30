package com.example.biedaolx_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String body;
    private Integer price;
    private LocalDateTime dateOfCreation = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    private PostCategory postCategory;

    public Post(String title, String body, Integer price, PostCategory postCategory) {
        this.title = title;
        this.body = body;
        this.price = price;
        this.postCategory = postCategory;
    }
}
