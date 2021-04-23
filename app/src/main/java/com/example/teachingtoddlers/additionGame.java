package com.example.teachingtoddlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class additionGame extends AppCompatActivity {
    Button levelOneButton,levelTwoButton,levelThreeButton;
    TextView goToHome;
    //additonGameCode g = new additonGameCode();

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
           levelTwoButton.setEnabled(true);
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
        }
    }
