package com.example.biedaolx_api.repository;

import com.example.biedaolx_api.entity.Post;
import com.example.biedaolx_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Long>, QuerydslPredicateExecutor<Post> {
    List<Post> findAllByUser(User user);
}
