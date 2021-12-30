package com.example.biedaolx_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
    @OneToMany(mappedBy = "creator",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Post> posts;


    public User(String name, String username, String password) {
        this.id = 1L;
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.posts = new ArrayList<>();
    }
}
