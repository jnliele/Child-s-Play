package com.example.teachingtoddlers;

import java.util.ArrayList;
import java.util.List;

public class additonGameCode {

    private List<addQuestions> questions;
    private int numberCorrect;
    private int numberIncorrect;
    private int totalQuestions;
    private int score;
    private addQuestions currentQuestion;


    public additonGameCode(){

        numberCorrect = 0;
        numberIncorrect =0;
        totalQuestions = 0;
        score=0;
        currentQuestion = new addQuestions(9);
        questions = new ArrayList<>();

    }

    public void makeNewQuestion(){

        currentQuestion= new addQuestions(9);
        totalQuestions++;
        questions.add(currentQuestion);

    }

    public boolean checkAnswer(int submittedAnswer){
        boolean isCorrect;
        if(currentQuestion.getAnswer()==submittedAnswer){
            numberCorrect++;
            isCorrect=true;
        }else{
            numberIncorrect++;
            isCorrect = false;
        }
        score = numberCorrect * 10 -numberIncorrect *30;
        return isCorrect;

    }


    public List<addQuestions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<addQuestions> questions) {
        this.questions = questions;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberIncorrect() {
        return numberIncorrect;
    }

    public void setNumberIncorrect(int numberIncorrect) {
        this.numberIncorrect = numberIncorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public addQuestions getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(addQuestions currentQuestion) {
        this.currentQuestion = currentQuestion;
    }



}
