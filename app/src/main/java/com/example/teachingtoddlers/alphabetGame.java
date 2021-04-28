package com.example.teachingtoddlers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class alphabetGame extends AppCompatActivity {

    Button Lvl1, Lvl2, Lvl3;

    TextView Homepage;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_game);

        Homepage = (TextView) findViewById(R.id.goToHomepage);
        Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(alphabetGame.this, homePage.class));
            }
        });

        Lvl1 = (Button) findViewById(R.id.Level1);
        Lvl2 = (Button) findViewById(R.id.Level2);
        Lvl3 = (Button) findViewById(R.id.Level3);

        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    if(userEmail.equals(ds.child("email").getValue())) {
                        if(ds.child("alphabetLevelOneScore").getValue(Long.class) > 6){
                            Lvl2.setEnabled(true);
                        }
                        if(ds.child("alphabetLevelTwoScore").getValue(Long.class) > 6){
                            Lvl3.setEnabled(true);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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