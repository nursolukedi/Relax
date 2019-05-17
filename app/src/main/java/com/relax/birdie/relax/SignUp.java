package com.relax.birdie.relax;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SignUp extends AppCompatActivity {

    Button signUp;
    EditText mailText, passwordText, nameSurname, ageText;
    RadioGroup genderButtons;
    RadioButton man, woman;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference dRef;
    int gender = -1;
    int age = -1;
    User newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUp = findViewById(R.id.signUp);
        mailText = findViewById(R.id.mailText);
        passwordText = findViewById(R.id.passwordText);
        ageText = findViewById(R.id.age);
        nameSurname = findViewById(R.id.nameSurname);
        man = findViewById(R.id.man);
        woman = findViewById(R.id.woman);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        dRef = database.getReference();
        // Choose gender when respective buttons are pressed
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = 1;
            }
        });
        woman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = 2;
            }
        });
        // Sign up
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (gender == -1) {
                    Toast.makeText(getApplicationContext(), "Please choose gender.", Toast.LENGTH_SHORT).show();
                }
                else if (mailText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid email.", Toast.LENGTH_SHORT).show();
                }
                else if (passwordText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter a password.", Toast.LENGTH_SHORT).show();
                }
                else if (ageText.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter your age.", Toast.LENGTH_SHORT).show();
                }
                else if (nameSurname.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please enter your name.", Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.createUserWithEmailAndPassword(mailText.getText().toString(), passwordText.getText().toString())
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("tag", "createUserWithEmail:success");
                                        dRef = FirebaseDatabase.getInstance().getReference();

                                        // Create the new user that will be added to the database
                                        newUser = new User(nameSurname.getText().toString());

                                        newUser.setEmail(mailText.getText().toString());
                                        newUser.setGender(gender);
                                        newUser.setAge(Integer.parseInt(ageText.getText().toString()));
                                        newUser.setName(nameSurname.getText().toString());
                                        writeToDb(newUser);
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w( "log" ,"createUserWithEmail:failure", task.getException() );
                                        Toast.makeText(getApplicationContext() , Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });
    }

    /*
    *
    *  // After adding the new user, switch back to sign in page
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        finish();
    *
    *
    *
    * */

    public void writeToDb(User user){

        FirebaseUser newUser =FirebaseAuth.getInstance().getCurrentUser();
        String userid = newUser.getUid();
        dRef.child("users").setValue(userid);
        dRef.child("users").child(userid).setValue(user);

    }
    }

