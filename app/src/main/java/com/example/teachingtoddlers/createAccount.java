package com.example.teachingtoddlers;

import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class createAccount extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText emailUsername, firstName, lastName, UTAID, password;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //register = (TextView) findViewById(R.id.register);
        //register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getID()){
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = emailUsername.getText().toString().trim();
        String fname = firstName.getText().toString().trim();
        String lname = lastName.getText().toString().trim();
        String utaID = UTAID.getText().toString().trim();
        String pw = password.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        if(email.isEmpty()){
            emailUsername.setError("Email is required!");
            emailUsername.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailUsername.setError("Please provide a valid email!");
            emailUsername.requestFocus();
            return;
        }

        if(fname.isEmpty()){
            firstName.setError("First Name is required!");
            firstName.requestFocus();
            return;
        }

        if(lname.isEmpty()){
            lastName.setError("Last Name is required!");
            lastName.requestFocus();
            return;
        }

        if(utaID.isEmpty()){
            UTAID.setError("A UTA ID is required!");
            UTAID.requestFocus();
            return;
        }

        if(pw.isEmpty()){
            password.setError("A Password is required!");
            password.requestFocus();
            return;
        }
        if(pw.length() > 20){
            password.setError("Password must not exceed 20 characters!");
            password.requestFocus();
            return;
        }

        auth.createUserWithEmailAndPassword(email, pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(email, fname, lname, utaID);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this, "You have been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.VISIBLE);
                                    }else{
                                        Toast.makeText(RegisterUser.this, "Failed to register!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterUser.this, "Failed to register!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}

