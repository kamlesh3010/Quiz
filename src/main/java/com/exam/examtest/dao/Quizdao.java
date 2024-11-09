package com.exam.examtest.dao;

import com.exam.examtest.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Quizdao extends JpaRepository<Quiz,Integer> {


}
