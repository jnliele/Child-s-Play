package com.example.teachingtoddlers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class countGameLvl2 extends AppCompatActivity {

    countGameQuestions questions = new countGameQuestions();

    Button Ans1, Ans2, Ans3, Ans4;
    TextView Score, Question;
    String answer;
    int score = 0;
    int questionNum = questions.Level2questions.length;
    Random ran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_game_lvl2);

        ran = new Random();

        Ans1 = (Button) findViewById(R.id.A);
        Ans2 = (Button) findViewById(R.id.B);
        Ans3 = (Button) findViewById(R.id.C);
        Ans4 = (Button) findViewById(R.id.D);
        Question = (TextView) findViewById(R.id.Question1);
        Score = (TextView) findViewById(R.id.Score1);
        Score.setText("Score: " + score);

        updateQuestion(ran.nextInt(questionNum));

        Ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                if(Ans1.getText() == answer){
                    score++;
                    if(score == 10){
                        gameEnd();
                    }else{
                        Score.setText("Score: " + score);
                        updateQuestion(ran.nextInt(questionNum));
                        Toast.makeText(countGameLvl2.this, "correct", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(countGameLvl2.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion(ran.nextInt(questionNum));
                }
            }
        });

        Ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                if(Ans2.getText() == answer){
                    score++;
                    if(score == 10){
                        gameEnd();
                    }else{
                        Score.setText("Score: " + score);
                        updateQuestion(ran.nextInt(questionNum));
                        Toast.makeText(countGameLvl2.this, "correct", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(countGameLvl2.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion(ran.nextInt(questionNum));
                }
            }
        });
        Ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                if(Ans3.getText() == answer){
                    score++;
                    if(score == 10){
                        gameEnd();
                    }else{
                        Score.setText("Score: " + score);
                        updateQuestion(ran.nextInt(questionNum));
                        Toast.makeText(countGameLvl2.this, "correct", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(countGameLvl2.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion(ran.nextInt(questionNum));
                }
            }
        });
        Ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                if(Ans4.getText() == answer){
                    score++;
                    if(score == 10){
                        gameEnd();
                    }else{
                        Score.setText("Score: " + score);
                        updateQuestion(ran.nextInt(questionNum));
                        Toast.makeText(countGameLvl2.this, "correct", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(countGameLvl2.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion(ran.nextInt(questionNum));
                }
            }
        });
    }

    private void gameEnd() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(countGameLvl2.this);
        alertDialogBuilder
                .setMessage("You reached a score of 10!")
                .setCancelable(false)
                .setPositiveButton("Finish",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), countGame.class));
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void updateQuestion(int num) {
        Question.setText(questions.Level2getQuestion(num));

        Ans1.setText(questions.Level2getChoice1(num));
        Ans2.setText(questions.Level2getChoice2(num));
        Ans3.setText(questions.Level2getChoice3(num));
        Ans4.setText(questions.Level2getChoice4(num));

        answer = questions.Level2correct(num);
    }
}