package com.example.teachingtoddlers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class accountCreation extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    Button continueButton;

    private EditText childFirst, childLast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        // get id of child's first name and last name
        childFirst = (EditText) findViewById(R.id.childFirstName);
        childLast = (EditText) findViewById(R.id.childLastName);

        // get id for radio group
        radioGroup = findViewById(R.id.radioGroup);

        // determine action for clicking continue button
        continueButton = (Button)findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if all information is inputted
                String childfName = childFirst.getText().toString().trim();
                String childlName = childLast.getText().toString().trim();

                if(childfName.isEmpty()){
                    childFirst.setError("Enter child's first name!");
                    childFirst.requestFocus();
                    return;
                }
                if(childlName.isEmpty()){
                    childLast.setError("Enter child's last name!");
                    childLast.requestFocus();
                    return;
                }

                // get the value of selected radio button
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);

                // radioButton.getText()          <- this gets the value of the radio button i.e. "1-3 years old"

                // all information valid, go to admin setup
                startActivity(new Intent(accountCreation.this, newAdmin.class));
            }
        });
    }
}