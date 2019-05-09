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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    Button signUp;
    EditText mailText, passwordText, nameSurname, ageText;
    RadioGroup genderButtons;
    RadioButton man, woman;
    private FirebaseAuth mAuth;
    int gender = -1;
    int age = -1;



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
        //genderButtons.set
        //TODO : set database tables

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
                else {
                    mAuth.createUserWithEmailAndPassword(mailText.getText().toString(), passwordText.getText().toString())
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("tag", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                        // DatabaseReference myRef = database.getReference(user.getUid());
                                        // myRef.setValue(uobj);
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
    }

