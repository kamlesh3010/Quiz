package com.exam.examtest.model;


import lombok.Data;

@Data
public class QueWrapper {

    private int id;
    private String category;

    public QueWrapper(int id, String question, String optionA, String optionB, String optionC ,String optionD) {
        this.id = id;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
    }


    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}
