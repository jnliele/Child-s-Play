package com.example.teachingtoddlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class countGame extends AppCompatActivity {

    Button levelOne, levelTwo, levelThree;
    TextView goToHomepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_game);

        goToHomepage = (TextView) (findViewById(R.id.goToHomepage));
        goToHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                startActivity(new Intent(countGame.this, homePage.class));
            }
        });

        levelOne = (Button) findViewById(R.id.Level1c);
        levelTwo = (Button) findViewById(R.id.Level2c);
        levelThree = (Button) findViewById(R.id.Level3c);

        levelOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                startActivity(new Intent(countGame.this, countGameLvl1.class));
            }
        });

        levelTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                startActivity(new Intent(countGame.this, countGameLvl2.class));
            }
        });

        levelThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                startActivity(new Intent(countGame.this, countGameLvl3.class));
            }
        });

    }


}