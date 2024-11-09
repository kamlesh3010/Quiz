package com.exam.examtest.dao;

import com.exam.examtest.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Qdao extends JpaRepository<Questions, Integer> {

    List<Questions> findByCategory(String category);

    @Query(value = "SELECT * FROM mcq_questions q WHERE q.category = :category ORDER BY RAND() LIMIT :num",
            nativeQuery = true)
    List<Questions> findRandomQueByCategory(@Param("category") String category, @Param("num") int num);
}
