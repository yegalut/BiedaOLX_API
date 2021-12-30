package com.example.biedaolx_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.LocalDateTimeType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User creator;
    private String recipientUsername;
    private String body;
    private LocalDateTime dateOfCreation = LocalDateTime.now();

    public Message(String body) {
        this.body = body;
    }

    public Message(User creator, String recipientUsername, String body) {
        this.creator = creator;
        this.recipientUsername = recipientUsername;
        this.body = body;
    }
}
