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

public class alphabetGameLvl3 extends AppCompatActivity {

    alphabetQuestions questions = new alphabetQuestions();

    Button Ans1, Ans2, Ans3, Ans4;
    TextView Score, Question;

    String answer, id;
    long score = 0;
    long totalQuestions = 0;
    long tempScore, tempTQ, tempTP;

    int questionNum = questions.questionsLvl3.length;

    Random r;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_game_lvl1);

        r = new Random();

        //grabbing the id's from the xml
        Ans1 = (Button) findViewById(R.id.Ans1);
        Ans2 = (Button) findViewById(R.id.Ans2);
        Ans3 = (Button) findViewById(R.id.Ans3);
        Ans4 = (Button) findViewById(R.id.Ans4);

        Question = (TextView) findViewById(R.id.Question);
        Score = (TextView) findViewById(R.id.Score);
        Score.setText("Score: " + score);

        updateQuestion(r.nextInt(questionNum));

        //Setting up the answer choices to create a multiple choice quiz
        Ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ans1.getText() == answer){
                    //increasing the score count when this button is the correct answer
                    score++;
                    Score.setText("Score: " + score);
                    Toast.makeText(alphabetGameLvl3.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(alphabetGameLvl3.this, "wrong", Toast.LENGTH_SHORT).show();
                }
                totalQuestions++;
                if(totalQuestions == 10){
                    //once 10 questions have been asked, the game will end
                    gameEnd();
                }
                //updates the question to a random question
                updateQuestion(r.nextInt(questionNum));
            }
        });

        Ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Ans2.getText() == answer){
                    score++;
                    Score.setText("Score: " + score);
                    Toast.makeText(alphabetGameLvl3.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(alphabetGameLvl3.this, "wrong", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(alphabetGameLvl3.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(alphabetGameLvl3.this, "wrong", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(alphabetGameLvl3.this, "correct", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(alphabetGameLvl3.this, "wrong", Toast.LENGTH_SHORT).show();
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
        //setting up the firebase connection to update the information in the database for this account for this level.
        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(ds.child("email").getValue().equals(userEmail)){
                        //grabbing the current information in the database
                        tempScore = ds.child("alphabetLevelThreeCorrect").getValue(Long.class);
                        tempTQ = ds.child("alphabetLevelThreeTotal").getValue(Long.class);
                        tempTP = ds.child("alphabetLevelThreeTotalPlay").getValue(Long.class);

                        //adding to the current database and updating the information
                        reference.child(id).child("alphabetLevelThreeCorrect").setValue(score+tempScore);
                        reference.child(id).child("alphabetLevelThreeTotal").setValue(totalQuestions+tempTQ);
                        reference.child(id).child("alphabetLevelThreeTotalPlay").setValue(tempTP+1);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //creating a dialog box once the game ends to give the user options to how they want to proceed
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(alphabetGameLvl3.this);
        //having a perfect score will create a dialog box congratulating the user and giving them a replay and back to level menu option
        if(score > 6){
            alertDialogBuilder
                    .setMessage("Your final score is " + score + "/10\nCongratulations, you have learned the alphabet!")
                    .setCancelable(false)
                    .setPositiveButton("Replay",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), alphabetGameLvl3.class));
                                }
                            })
                    .setNeutralButton("Back",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(getApplicationContext(), alphabetGame.class));
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
                                    startActivity(new Intent(getApplicationContext(), alphabetGameLvl3.class));
                                }
                            })
                    .setNeutralButton("Back",
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
        Question.setText(questions.getQuestionLvl3(num));

        Ans1.setText(questions.getChoice1Lvl3(num));
        Ans2.setText(questions.getChoice2Lvl3(num));
        Ans3.setText(questions.getChoice3Lvl3(num));
        Ans4.setText(questions.getChoice4Lvl3(num));

        answer = questions.correctLvl3(num);
    }
}