package com.example.biedaolx_api.repository;

import com.example.biedaolx_api.entity.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

    @Query("select t from Test t")
    List<Test> findAllTest(Pageable pageable);

    @Query("SELECT t from Test t where t.id = :id")
    Test lel(@Param("id") int id);

}
