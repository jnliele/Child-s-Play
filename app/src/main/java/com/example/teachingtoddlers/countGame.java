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

public class countGame extends AppCompatActivity {

    Button levelOne, levelTwo, levelThree;
    TextView goToHomepage;
    long levelOneHighScore, levelTwoHighScore;
    String Id;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    long passing = 7;

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

                        levelOneHighScore = ds.child("countingLevelOneScore").getValue(Long.class);
                        levelTwoHighScore = ds.child("countingLevelTwoScore").getValue(Long.class);

                        if (levelOneHighScore >= passing) {
                            levelTwo.setEnabled(true);
                        }
                        if (levelTwoHighScore >= passing) {
                            levelThree.setEnabled(true);
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