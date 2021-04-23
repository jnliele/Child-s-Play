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

import org.w3c.dom.Text;

import java.util.Random;

public class alphabetGameLvl1 extends AppCompatActivity {

    alphabetQuestions questions = new alphabetQuestions();

    Button Ans1, Ans2, Ans3, Ans4;
    TextView Score, Question;

    String answer;
    int score = 0;
    int questionNum = questions.questionsLvl1.length;

    Random r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_game_lvl1);

        r = new Random();

        Ans1 = (Button) findViewById(R.id.Ans1);
        Ans2 = (Button) findViewById(R.id.Ans2);
        Ans3 = (Button) findViewById(R.id.Ans3);
        Ans4 = (Button) findViewById(R.id.Ans4);

        Question = (TextView) findViewById(R.id.Question);
        Score = (TextView) findViewById(R.id.Score);
        Score.setText("Score: " + score);

        updateQuestion(r.nextInt(questionNum));

        Ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ans1.getText() == answer){
                    score++;
                    if(score == 10){
                        gameEnd();
                    }else{
                        Score.setText("Score: " + score);
                        updateQuestion(r.nextInt(questionNum));
                        Toast.makeText(alphabetGameLvl1.this, "correct", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(alphabetGameLvl1.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion(r.nextInt(questionNum));
                }
            }
        });

        Ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ans2.getText() == answer){
                    score++;
                    if(score == 10){
                        gameEnd();
                    }else{
                        Score.setText("Score: " + score);
                        updateQuestion(r.nextInt(questionNum));
                        Toast.makeText(alphabetGameLvl1.this, "correct", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(alphabetGameLvl1.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion(r.nextInt(questionNum));
                }
            }
        });

        Ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ans3.getText() == answer){
                    score++;
                    if(score == 10){
                        gameEnd();
                    }else{
                        Score.setText("Score: " + score);
                        updateQuestion(r.nextInt(questionNum));
                        Toast.makeText(alphabetGameLvl1.this, "correct", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(alphabetGameLvl1.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion(r.nextInt(questionNum));
                }
            }
        });

        Ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ans4.getText() == answer){
                    score++;
                    if(score == 10){
                        gameEnd();
                    }else{
                        Score.setText("Score: " + score);
                        updateQuestion(r.nextInt(questionNum));
                        Toast.makeText(alphabetGameLvl1.this, "correct", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(alphabetGameLvl1.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion(r.nextInt(questionNum));
                }
            }
        });
    }

    private void gameEnd() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(alphabetGameLvl1.this);
        alertDialogBuilder
                .setMessage("You reached a score of 10!")
                .setCancelable(false)
                .setPositiveButton("Finish",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), alphabetGame.class));
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void updateQuestion(int num) {
        Question.setText(questions.getQuestionLvl1(num));

        Ans1.setText(questions.getChoice1Lvl1(num));
        Ans2.setText(questions.getChoice2Lvl1(num));
        Ans3.setText(questions.getChoice3Lvl1(num));
        Ans4.setText(questions.getChoice4Lvl1(num));

        answer = questions.correctLvl1(num);
    }
}