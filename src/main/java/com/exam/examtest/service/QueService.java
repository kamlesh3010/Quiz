package com.exam.examtest.service;

import com.exam.examtest.dao.Qdao;
import com.exam.examtest.model.Questions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueService {

    @Autowired
    Qdao qdao;

    public List<Questions> getAllQuestions() {
        try {
            return qdao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Questions> getQueCategory(String category) {
        try {
            return qdao.findByCategory(category);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateQuestion(int id, Questions questions) {
        try {
            qdao.save(questions);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    public String deleteQuestion(int id) {
        try {
            qdao.deleteById(id);
            return "done";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    public String addQuestions(Questions questions) {
        try {
            qdao.save(questions);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }


}
