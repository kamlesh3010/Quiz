package com.exam.examtest.service;

import com.exam.examtest.dao.Qdao;
import com.exam.examtest.dao.Quizdao;
import com.exam.examtest.model.QueWrapper;
import com.exam.examtest.model.Questions;
import com.exam.examtest.model.Quiz;
import com.exam.examtest.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private Quizdao quizDao;

    @Autowired
    private Qdao questionDao;

    public ResponseEntity<String> createQuize(String category, int num, String title) {
        Quiz quiz = new Quiz();

        // Fetch random questions by category and number
        List<Questions> questions = questionDao.findRandomQueByCategory(category, num);

        // Set questions and title in the quiz object
        quiz.setQuestions(questions);
        quiz.setTitle(title);

        // Save the quiz to the database
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QueWrapper>> getQuizQues(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);


        List<Questions> questionFromDB = quiz.get().getQuestions();
        List<QueWrapper> QueUser = new ArrayList<>();

        for (Questions q : questionFromDB) {
            QueWrapper qw = new QueWrapper(q.getId(), q.getQuestion(),
                    q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD());
            QueUser.add(qw);
        }

        return new ResponseEntity<>(QueUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> response) {
        Optional<Quiz> quizOptional = quizDao.findById(id);

        if (!quizOptional.isPresent()) {
            // If the quiz is not found, return a 404 NOT FOUND status
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Quiz q = quizOptional.get();
        List<Questions> que = q.getQuestions();

        if (response.size() != que.size()) {
            // Return a 400 BAD REQUEST if the number of responses does not match the number of questions
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        int right = 0;
        for (int i = 0; i < response.size(); i++) {
            if (response.get(i).getResponse().equals(que.get(i).getCorrectAnswer())) {
                right++;
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
