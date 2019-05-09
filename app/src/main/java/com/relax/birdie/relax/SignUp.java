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

public class SignUp extends AppCompatActivity {

    Button signUp;
    EditText mailText, passwordText , nameSurname ;
    RadioGroup genderButtons;
    RadioButton man, woman;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUp = findViewById(R.id.signUp);
        mailText = findViewById(R.id.mailText);
        passwordText = findViewById(R.id.passwordText);
        nameSurname = findViewById(R.id.nameSurname);
        man = findViewById(R.id.man);
        woman = findViewById(R.id.woman);
        mAuth = FirebaseAuth.getInstance();
        //genderButtons.set
        //TODO : set database tables

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                                    Toast.makeText(getApplicationContext() , "Something went wrong :( ", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });
    }
    }

