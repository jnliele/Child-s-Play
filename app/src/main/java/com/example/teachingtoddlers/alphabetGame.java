package com.example.teachingtoddlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class alphabetGame extends AppCompatActivity {

    Button Lvl1, Lvl2, Lvl3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_game);

        Lvl1 = (Button) findViewById(R.id.Level1);
        Lvl2 = (Button) findViewById(R.id.Level2);
        Lvl3 = (Button) findViewById(R.id.Level3);

        Lvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(alphabetGame.this, alphabetGameLvl1.class));
            }
        });

        Lvl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(alphabetGame.this, alphabetGameLvl2.class));
            }
        });

        Lvl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(alphabetGame.this, alphabetGameLvl3.class));
            }
        });
    }
}