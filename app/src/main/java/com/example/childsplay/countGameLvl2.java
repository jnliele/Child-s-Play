package com.example.childsplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class countGameLvl2 extends AppCompatActivity {

    countGameQuestions questions = new countGameQuestions();

    Button Ans1, Ans2, Ans3, Ans4;
    TextView Score, Question;
    String answer,Id;
    int score = 0;
    long questionCount=0, temp=0;
    int questionNum = questions.Level2questions.length;
    long levelTwoTotalPlayCount=0,levelTwoTotalCorrect=0,levelTwoTotalquestions=0;
    long levelTwoHighScore =0;
    Random ran;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_game_levels);
        temp++;
        ran = new Random();

        //grabbing the id's from the xml
        Ans1 = (Button) findViewById(R.id.A);
        Ans2 = (Button) findViewById(R.id.B);
        Ans3 = (Button) findViewById(R.id.C);
        Ans4 = (Button) findViewById(R.id.D);
        Question = (TextView) findViewById(R.id.Question1);
        Score = (TextView) findViewById(R.id.Score1);
        Score.setText("Score: " + score);

        updateQuestion(ran.nextInt(questionNum));

        //Setting up the answer choices to create a the game
        //Game ends after 10 questions
        Ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                if(Ans1.getText() == answer){
                    score++;
                    Score.setText("Score: " + score);
                    Toast.makeText(countGameLvl2.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(countGameLvl2.this, "incorrect", Toast.LENGTH_SHORT).show();
                }
                questionCount++;
                if(questionCount == 10) {
                    gameEnd();
                }
                updateQuestion(ran.nextInt(questionNum));
            }
        });
        Ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                if(Ans2.getText() == answer){
                    score++;
                    Score.setText("Score: " + score);
                    Toast.makeText(countGameLvl2.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(countGameLvl2.this, "incorrect", Toast.LENGTH_SHORT).show();
                }
                questionCount++;
                if(questionCount == 10) {
                    gameEnd();
                }
                updateQuestion(ran.nextInt(questionNum));
            }
        });
        Ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                if(Ans3.getText() == answer){
                    score++;
                    Score.setText("Score: " + score);
                    Toast.makeText(countGameLvl2.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(countGameLvl2.this, "incorrect", Toast.LENGTH_SHORT).show();
                }
                questionCount++;
                if(questionCount == 10) {
                    gameEnd();
                }
                updateQuestion(ran.nextInt(questionNum));
            }
        });
        Ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                if(Ans4.getText() == answer){
                    score++;
                    Score.setText("Score: " + score);
                    Toast.makeText(countGameLvl2.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(countGameLvl2.this, "incorrect", Toast.LENGTH_SHORT).show();
                }
                questionCount++;
                if(questionCount == 10) {
                    gameEnd();
                }
                updateQuestion(ran.nextInt(questionNum));
            }
        });
    }

    private void gameEnd() {
        //setting up the firebase connection to update the information in the database for this account for this level.
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        rootNode= FirebaseDatabase.getInstance();
        reference =  rootNode.getReference("Users");
        Id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        reference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("email").getValue().equals(userEmail)) {

                        //grabbing the current information in the database and updated the information
                        levelTwoTotalCorrect = ds.child("countingLevelTwoCorrect").getValue(Long.class);
                        levelTwoTotalPlayCount = ds.child("countingLevelTwoTotalPlay").getValue(Long.class);
                        levelTwoTotalquestions = ds.child("countingLevelTwoTotal").getValue(Long.class);
                        levelTwoHighScore = ds.child("countingLevelTwoScore").getValue(Long.class);

                        levelTwoHighScore = score;
                        levelTwoTotalPlayCount = levelTwoTotalPlayCount +temp;
                        levelTwoTotalquestions = levelTwoTotalquestions + questionCount;
                        levelTwoTotalCorrect = levelTwoTotalCorrect + score;

                        reference.child(Id).child("countingLevelTwoCorrect").setValue(levelTwoTotalCorrect);
                        reference.child(Id).child("countingLevelTwoTotalPlay").setValue(levelTwoTotalPlayCount);
                        reference.child(Id).child("countingLevelTwoTotal").setValue(levelTwoTotalquestions);
                        reference.child(Id).child("countingLevelTwoScore").setValue(levelTwoHighScore);

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        //creating a dialog box once the game ends to give the user options to how they want to proceed
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(countGameLvl2.this);
        //having a passing score will pop up a dialog box that allows the user to either go back to level page, replay, or go to the next level
        if(score > 6){
            alertDialogBuilder
                    .setMessage("Your final score is " + score + "/10\nYou unlocked Level 3!")
                    .setCancelable(false)
                    .setPositiveButton("Next Level",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), countGameLvl3.class));
                                }
                            })
                    .setNegativeButton("Replay",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), countGameLvl2.class));
                                }
                            })
                    .setNeutralButton("Back",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), countGame.class));
                                }
                            });
        }else {
            //having a failing score will create a dialog box that only let's the user go back to level page or replay the level
            alertDialogBuilder
                    .setMessage("Your final score is " + score + "/10")
                    .setCancelable(false)
                    .setPositiveButton("Replay",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), countGameLvl2.class));
                                }
                            })
                    .setNeutralButton("Back",
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
        Question.setText(questions.Level2getQuestion(num));

        Ans1.setText(questions.Level2getChoice1(num));
        Ans2.setText(questions.Level2getChoice2(num));
        Ans3.setText(questions.Level2getChoice3(num));
        Ans4.setText(questions.Level2getChoice4(num));

        answer = questions.Level2correct(num);
    }

}