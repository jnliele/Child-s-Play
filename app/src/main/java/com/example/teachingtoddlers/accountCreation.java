package com.example.teachingtoddlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class accountCreation extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_set_up);

        radioGroup = findViewById(R.id.radioGroup);
        continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(accountCreation.this, AdminSetup.class));
            }
        });
    }

    public void checkButton(View view) {

        //  int radioID= radioGroup.getCheckedRadioButtonId();
        //  radioButton=findViewById(radioID);

    }
}