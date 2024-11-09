package com.exam.examtest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
//@Table(name = "questions")
@Table(name = "mcq_questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Questions {
    @Id
    private int id;
    private String category;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
}
