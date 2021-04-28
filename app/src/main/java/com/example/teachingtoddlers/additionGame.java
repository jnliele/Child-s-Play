package com.example.teachingtoddlers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class additionGame extends AppCompatActivity {
    Button levelOneButton,levelTwoButton,levelThreeButton;
    TextView goToHome;
    long levelOneTotalCorrect, levelOneTotalquestions;
    String Id, levelOneTotalCorrectStr;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    double passing = .70;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_game);



        goToHome = (TextView) (findViewById(R.id.backToHome));

        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(additionGame.this, homePage.class));
            }
        });


        levelOneButton = (Button) (findViewById(R.id.levelOne));
        levelOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(additionGame.this, additionLevelOne.class));
            }
        });


        //level two buttons
           levelTwoButton = (Button) (findViewById(R.id.levelTwo));
           levelTwoButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   startActivity(new Intent(additionGame.this, additionLevelTwo.class));
               }
           });



       //level three buttons
        levelThreeButton = (Button) (findViewById(R.id.levelThree));
            levelThreeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(additionGame.this, additionLevelThree.class));
                }
            });





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

                        levelOneTotalCorrect = ds.child("additionLevelOneCorrect").getValue(Long.class);
                        levelOneTotalquestions = ds.child("additionLevelOneTotal").getValue(Long.class);


                        if((levelOneTotalCorrect!=0) && (levelOneTotalquestions!=0)){

                            if(((double)(levelOneTotalCorrect/levelOneTotalquestions)>=passing)&& (levelOneTotalCorrect>5))
                            {
                                levelTwoButton.setEnabled(true);
                            }else {

                                levelTwoButton.setEnabled(false);

                            }

                        }

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });







    }
    }
