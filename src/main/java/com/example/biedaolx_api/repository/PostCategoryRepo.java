package com.example.biedaolx_api.repository;

import com.example.biedaolx_api.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCategoryRepo extends JpaRepository<PostCategory, Long> {

    PostCategory findByName(String name);

    @Query(nativeQuery = true,
            value = "SELECT name FROM post_category;"
    )
    List<String> getAllCategoryNames();


}
