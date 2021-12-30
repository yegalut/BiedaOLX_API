package com.example.biedaolx_api.repository;

import com.example.biedaolx_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
