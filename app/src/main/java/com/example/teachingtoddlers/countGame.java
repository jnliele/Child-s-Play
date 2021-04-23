package com.example.teachingtoddlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Button;

public class countGame extends AppCompatActivity {

    Button levelOne, levelTwo, levelThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_game);

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