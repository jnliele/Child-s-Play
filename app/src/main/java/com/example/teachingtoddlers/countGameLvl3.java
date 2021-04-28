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

public class countGameLvl3 extends AppCompatActivity {

    countGameQuestions questions = new countGameQuestions();

    Button Ans1, Ans2, Ans3, Ans4;
    TextView Score, Question;
    String answer;
    int score = 0;
    int questionCount=0;
    int questionNum = questions.Level3questions.length;
    Random ran;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_game_levels);

        ran = new Random();

        Ans1 = (Button) findViewById(R.id.A);
        Ans2 = (Button) findViewById(R.id.B);
        Ans3 = (Button) findViewById(R.id.C);
        Ans4 = (Button) findViewById(R.id.D);
        Question = (TextView) findViewById(R.id.Question1);
        Score = (TextView) findViewById(R.id.Score1);
        Score.setText("Score: " + score);

        updateQuestion(ran.nextInt(questionNum));
        questionCount++;

        Ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                if(Ans1.getText() == answer){
                    score++;
                    if(score == 10){
                        gameEnd();
                    }else{
                        Score.setText("Score: " + score);
                        Toast.makeText(countGameLvl3.this, "correct", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(countGameLvl3.this, "incorrect", Toast.LENGTH_SHORT).show();
                }
                questionCount++;
                updateQuestion(ran.nextInt(questionNum));
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
                        Toast.makeText(countGameLvl3.this, "correct", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(countGameLvl3.this, "incorrect", Toast.LENGTH_SHORT).show();
                }
                questionCount++;
                updateQuestion(ran.nextInt(questionNum));
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
                        Toast.makeText(countGameLvl3.this, "correct", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(countGameLvl3.this, "incorrect", Toast.LENGTH_SHORT).show();
                }
                questionCount++;
                updateQuestion(ran.nextInt(questionNum));
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
                        Toast.makeText(countGameLvl3.this, "correct", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(countGameLvl3.this, "incorrect", Toast.LENGTH_SHORT).show();
                }
                questionCount++;
                updateQuestion(ran.nextInt(questionNum));
            }
        });
    }

    private void gameEnd() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(countGameLvl3.this);
        if(questionCount < 15){
            alertDialogBuilder
                    .setMessage("Congratulations! You mastered counting! Your final score is " + score + "/"+questionCount)
                    .setCancelable(false)
                    .setPositiveButton("Back",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), countGame.class));
                                }
                            });
        }else {
            alertDialogBuilder
                    .setMessage("Your final score " + score + "/"+questionCount)
                    .setCancelable(false)
                    .setPositiveButton("Replay",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), countGameLvl3.class));
                                }
                            })
                    .setNegativeButton("Back",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), countGame.class));
                                }
                            });
        }
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void updateQuestion(int num) {
        Question.setText(questions.Level3getQuestion(num));

        Ans1.setText(questions.Level3getChoice1(num));
        Ans2.setText(questions.Level3getChoice2(num));
        Ans3.setText(questions.Level3getChoice3(num));
        Ans4.setText(questions.Level3getChoice4(num));

        answer = questions.Level3correct(num);
    }

}