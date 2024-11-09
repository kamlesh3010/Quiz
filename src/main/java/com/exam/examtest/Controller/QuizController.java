package com.exam.examtest.Controller;

import com.exam.examtest.model.QueWrapper;
import com.exam.examtest.model.Response;
import com.exam.examtest.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Quiz")
public class QuizController {

    @Autowired
    private QuizService qbs;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(
            @RequestParam String category,
            @RequestParam int numQ,
            @RequestParam String title) {
        try {
            return qbs.createQuize(category, numQ, title);
        } catch (Exception e) {
            // Log the exception if necessary
            return new ResponseEntity<>("Error creating quiz: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getQuestions(@PathVariable int id) {
        try {
            ResponseEntity<List<QueWrapper>> quizQues = qbs.getQuizQues(id);
            if (quizQues.getBody() == null || quizQues.getBody().isEmpty()) {
                return new ResponseEntity<>("Quiz not found or no questions available.", HttpStatus.NOT_FOUND);
            }
            return quizQues;
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving questions: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<?> submitQuiz(@PathVariable int id, @RequestBody List<Response> response) {
        if (response == null || response.isEmpty()) {
            return new ResponseEntity<>("Response list cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        try {
            return qbs.calculateResult(id, response);
        } catch (Exception e) {
            return new ResponseEntity<>("Error calculating quiz result: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
