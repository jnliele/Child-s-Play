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

    //empty constructor
    public  additonGameCode (){

    }

    //sets the presets of the game and the upper limit(how high the numbers go to add) for level one
    public void additonGameCode1(){

        numberCorrect = 0;
        numberIncorrect =0;
        totalQuestions = 0;
        score=0;
        currentQuestion = new addQuestions(9);
        questions = new ArrayList<>();

    }
    //sets a new question for level one
    public void makeNewQuestion(){

        currentQuestion= new addQuestions(9);
        totalQuestions++;
        questions.add(currentQuestion);

    }

    //sets the presets of the game and the upper limit(how high the numbers go to add) for level two
    public void additonGameCode2(){

        numberCorrect = 0;
        numberIncorrect =0;
        totalQuestions = 0;
        score=0;
        currentQuestion = new addQuestions(99);
        questions = new ArrayList<>();

    }
    //sets a new question for level one
    public void makeNewQuestion2(){

        currentQuestion= new addQuestions(99);
        totalQuestions++;
        questions.add(currentQuestion);

    }

    //sets the presets of the game and the upper limit(how high the numbers go to add) for level three
    public void additonGameCode3(){

        numberCorrect = 0;
        numberIncorrect =0;
        totalQuestions = 0;
        score=0;
        currentQuestion = new addQuestions(999);
        questions = new ArrayList<>();

    }
    //sets a new question for level one
    public void makeNewQuestion3(){
        currentQuestion= new addQuestions(999);
        totalQuestions++;
        questions.add(currentQuestion);

    }




    //checks to see if the answer the user chose was correct
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
