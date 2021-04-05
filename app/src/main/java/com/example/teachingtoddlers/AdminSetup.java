package com.example.teachingtoddlers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class AdminSetup extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText emailUsername, firstName, lastName, UTAID, password, profession, childfirstName, childlastName;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private ImageView profilePic;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private RadioGroup radioGroup;
    private RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_setup);

        auth = FirebaseAuth.getInstance();

        register = (Button) findViewById(R.id.registeruser);
        register.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        emailUsername = (EditText) findViewById(R.id.textEmail);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        UTAID = (EditText) findViewById(R.id.utaid);
        password = (EditText) findViewById(R.id.PW);
        profession = (EditText) findViewById(R.id.profession);

        childfirstName = (EditText) findViewById(R.id.childFirstName);
        childlastName = (EditText) findViewById(R.id.childLastName);

        profilePic = findViewById(R.id.profilePic);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePicture();
            }
        });

        radioGroup = findViewById(R.id.radioGroup);
        int radioID= radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioID);
    }

    private void choosePicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            profilePic.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image...");
        pd.show();

        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/");

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content), "Image Uploaded.", Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed to Upload", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        pd.setMessage("Progress: " + (int) progressPercent + "%");
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registeruser:
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

        String cfname = childfirstName.getText().toString().trim();
        String clname = childlastName.getText().toString().trim();

        String ageRange = radioButton.getText().toString();

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

        if(cfname.isEmpty()){
            childfirstName.setError("Child's First Name is required!");
            childfirstName.requestFocus();
            return;
        }
        if(clname.isEmpty()){
            childlastName.setError("Child's Last Name is required!");
            childlastName.requestFocus();
            return;
        }

        if(ageRange.isEmpty()){
            radioButton.setError("Age range is required!");
            radioButton.requestFocus();
            return;
        }


        auth.createUserWithEmailAndPassword(email, pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(email, fname, lname, utaID, prof, pw, cfname, clname, ageRange);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(AdminSetup.this, "You have been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.VISIBLE);
                                    }else{
                                        Toast.makeText(AdminSetup.this, "Failed to register!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(AdminSetup.this, "Failed to register!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}

