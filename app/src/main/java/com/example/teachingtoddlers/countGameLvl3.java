package com.example.teachingtoddlers;

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

public class countGameLvl3 extends AppCompatActivity {

    countGameQuestions questions = new countGameQuestions();

    Button Ans1, Ans2, Ans3, Ans4;
    TextView Score, Question;
    String answer, Id;
    int score = 0;
    long questionCount=0, temp=0;
    int questionNum = questions.Level3questions.length;
    long levelThreeTotalPlayCount=0,levelThreeTotalCorrect=0,levelThreeTotalquestions=0;
    long levelThreeHighScore =0;
    Random ran;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_game_levels);
        temp++;
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
                    Score.setText("Score: " + score);
                    Toast.makeText(countGameLvl3.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(countGameLvl3.this, "incorrect", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(countGameLvl3.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(countGameLvl3.this, "incorrect", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(countGameLvl3.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(countGameLvl3.this, "incorrect", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(countGameLvl3.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(countGameLvl3.this, "incorrect", Toast.LENGTH_SHORT).show();
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

                        levelThreeTotalCorrect = ds.child("countingLevelThreeCorrect").getValue(Long.class);
                        levelThreeTotalPlayCount = ds.child("countingLevelThreeTotalPlay").getValue(Long.class);
                        levelThreeTotalquestions = ds.child("countingLevelThreeTotal").getValue(Long.class);

                        levelThreeTotalPlayCount = levelThreeTotalPlayCount + temp;
                        levelThreeTotalquestions = levelThreeTotalquestions + questionCount;
                        levelThreeTotalCorrect = levelThreeTotalCorrect + score;

                        reference.child(Id).child("countingLevelThreeCorrect").setValue(levelThreeTotalCorrect);
                        reference.child(Id).child("countingLevelThreeTotalPlay").setValue(levelThreeTotalPlayCount);
                        reference.child(Id).child("countingLevelThreeTotal").setValue(levelThreeTotalquestions);

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(countGameLvl3.this);
        if(score == 10){
            alertDialogBuilder
                    .setMessage("Congratulations! You mastered counting! Your final score is " + score + "/10")
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
        }else {
            alertDialogBuilder
                    .setMessage("Your final score is " + score + "/10")
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