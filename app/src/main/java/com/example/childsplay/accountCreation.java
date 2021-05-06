package com.example.childsplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class accountCreation extends AppCompatActivity {

    // variables to help pass input information from child setup to admin setup
    public static final String EXTRA_FIRST = "com.example.teachingtoddlers.EXTRA_FIRST";
    public static final String EXTRA_LAST = "com.example.teachingtoddlers.EXTRA_LAST";
    public static final String EXTRA_AGE = "com.example.teachingtoddlers.EXTRA_AGE";

    // variables to obtain the choice selected for child age range
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
                String ageRange = radioButton.getText().toString().trim(); // 1-3 years old, 3-5 years old, 5-7 years old

                // all information valid, pass child info to admin setup, and go to admin setup
                Intent intent = new Intent(accountCreation.this, newAdmin.class);
                intent.putExtra(EXTRA_FIRST, childfName);
                intent.putExtra(EXTRA_LAST, childlName);
                intent.putExtra(EXTRA_AGE, ageRange);
                startActivity(intent);
            }
        });
    }
}