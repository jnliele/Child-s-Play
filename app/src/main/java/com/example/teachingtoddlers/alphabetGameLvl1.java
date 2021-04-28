package com.example.teachingtoddlers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class alphabetGameLvl1 extends AppCompatActivity {

    alphabetQuestions questions = new alphabetQuestions();

    Button Ans1, Ans2, Ans3, Ans4;
    TextView Score, Question;

    String answer, id;
    long score = 0;
    long totalQuestions = 0;
    long tempScore, tempTQ, tempTP, highScore;

    int questionNum = questions.questionsLvl1.length;

    Random r;

    DatabaseReference reference;

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
                    Score.setText("Score: " + score);
                    Toast.makeText(alphabetGameLvl1.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(alphabetGameLvl1.this, "wrong", Toast.LENGTH_SHORT).show();
                }
                totalQuestions++;
                if(totalQuestions == 10){
                    gameEnd();
                }
                updateQuestion(r.nextInt(questionNum));
            }
        });

        Ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ans2.getText() == answer){
                    score++;
                    Score.setText("Score: " + score);
                    Toast.makeText(alphabetGameLvl1.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(alphabetGameLvl1.this, "wrong", Toast.LENGTH_SHORT).show();
                }
                totalQuestions++;
                if(totalQuestions == 10){
                    gameEnd();
                }
                updateQuestion(r.nextInt(questionNum));
            }
        });

        Ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ans3.getText() == answer){
                    score++;
                    Score.setText("Score: " + score);
                    Toast.makeText(alphabetGameLvl1.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(alphabetGameLvl1.this, "wrong", Toast.LENGTH_SHORT).show();
                }
                totalQuestions++;
                if(totalQuestions == 10){
                    gameEnd();
                }
                updateQuestion(r.nextInt(questionNum));
            }
        });

        Ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ans4.getText() == answer){
                    score++;
                    Score.setText("Score: " + score);
                    Toast.makeText(alphabetGameLvl1.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(alphabetGameLvl1.this, "wrong", Toast.LENGTH_SHORT).show();
                }
                totalQuestions++;
                if(totalQuestions == 10){
                    gameEnd();
                }
                updateQuestion(r.nextInt(questionNum));
            }
        });
    }

    public void gameEnd() {
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(userEmail)){
                        tempScore = ds.child("alphabetLevelOneCorrect").getValue(Long.class);
                        tempTQ = ds.child("alphabetLevelOneTotal").getValue(Long.class);
                        tempTP = ds.child("alphabetLevelOneTotalPlay").getValue(Long.class);
                        highScore = ds.child("alphabetLevelOneScore").getValue(Long.class);

                        reference.child(id).child("alphabetLevelOneCorrect").setValue(score+tempScore);
                        reference.child(id).child("alphabetLevelOneTotal").setValue(totalQuestions+tempTQ);
                        reference.child(id).child("alphabetLevelOneTotalPlay").setValue(tempTP+1);

                        if(score > highScore){
                            reference.child(id).child("alphabetLevelOneScore").setValue(score);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(alphabetGameLvl1.this);
        if(score > 6){
            alertDialogBuilder
                    .setMessage("Your final score " + score + "/10\nYou unlocked Level 2!")
                    .setCancelable(false)
                    .setPositiveButton("Next Level",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), alphabetGameLvl2.class));
                                }
                            })
                    .setNegativeButton("Back",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), alphabetGame.class));
                                }
                            });
        }else {
            alertDialogBuilder
                    .setMessage("Your final score " + score + "/10")
                    .setCancelable(false)
                    .setPositiveButton("Replay",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), alphabetGameLvl1.class));
                                }
                            })
                    .setNegativeButton("Back",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), alphabetGame.class));
                                }
                            });
        }
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