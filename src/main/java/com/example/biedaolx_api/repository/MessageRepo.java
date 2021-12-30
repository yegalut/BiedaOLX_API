package com.example.biedaolx_api.repository;

import com.example.biedaolx_api.entity.Message;
import com.example.biedaolx_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.*;
import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {

   /* @Query(value = "select * from Message where user_id=:id",
    nativeQuery = true)
    List<Message> findAllByCreator(@Param("id") Long id);*/

    List<Message> findAllByCreator(User creator);

    List<Message> findAllByRecipientUsername(String username);

   List<Message> findAllByCreatorAndRecipientUsername(User creator, String recipient);



}
