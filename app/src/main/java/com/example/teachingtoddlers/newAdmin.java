package com.example.teachingtoddlers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class newAdmin extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText emailUsername, firstName, lastName, UTAID, password, profession;
    private FirebaseAuth auth;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_admin);

        auth = FirebaseAuth.getInstance();

        register = (Button) findViewById(R.id.button2);
        register.setOnClickListener(this);

        emailUsername = (EditText) findViewById(R.id.editTextTextEmailAddress);
        firstName = (EditText) findViewById(R.id.editTextTextPersonName2);
        lastName = (EditText) findViewById(R.id.editTextTextPersonName3);
        UTAID = (EditText) findViewById(R.id.editTextTextPersonName4);
        password = (EditText) findViewById(R.id.editTextTextPassword);
        profession = (EditText) findViewById(R.id.editTextTextPersonName5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button2:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = emailUsername.getText().toString().trim();
        String fname = firstName.getText().toString().trim();
        String lname = lastName.getText().toString().trim();
        String utaID = UTAID.getText().toString().trim();
        String prof = profession.getText().toString().trim();
        String pw = password.getText().toString().trim();

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

        if(prof.isEmpty()){
            profession.setError("A profession is required!");
            profession.requestFocus();
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
                            User user = new User(email, fname, lname, utaID, prof, pw);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(newAdmin.this, "You have been registered successfully!", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(newAdmin.this, "Failed to register!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(newAdmin.this, "Email is already registered!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}