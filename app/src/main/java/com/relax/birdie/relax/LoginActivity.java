package com.relax.birdie.relax;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity{
    private EditText password, email;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Button signIn, signUp;
    private ImageView relaxImage;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        password = findViewById(R.id.passwordText);
        email = findViewById(R.id.mailText);
        relaxImage = findViewById(R.id.relaxImage);
        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.signUp);
        relaxImage = findViewById(R.id.relaxImage);

        progressDialog = new ProgressDialog(this);

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(email.getText().toString())) {
                        // Text is empty
                        Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(password.getText().toString())) {
                        // Password is empty
                        Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // If validations are OK, it will first show a progress bar
                    progressDialog.setMessage("Signing up...");
                    progressDialog.show();


                    mAuth.createUserWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("loginpage", "signUpWithEmail:onComplete:" + task.isSuccessful());

                                    if (!task.isSuccessful()) {
                                        Log.w("loginpage", "signUpWithEmail:failed", task.getException());
                                        Toast.makeText(LoginActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        // Create user object
                                        User newUser = new User(email.getText().toString().trim());

                                        // Get unique database key
                                        String key = databaseReference.child("users").push().getKey();

                                        // Add the user to database
                                        databaseReference.child("users").child(Objects.requireNonNull(key)).setValue(newUser);

                                        // In case of success, continue
                                        Toast.makeText(getApplicationContext(),"User Signed Up ",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                        startActivity(intent);
                                    }

                                } });
//                    // will open registration activity here
//                    finish();
//                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                }
            });
            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (TextUtils.isEmpty(email.getText().toString())) {
                        // Text is empty
                        Toast.makeText(LoginActivity.this, "Please enter email", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(password.getText().toString())) {
                        // Password is empty
                        Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // If validations are OK, it will first show a progress bar
                    progressDialog.setMessage("Logging in...");
                    progressDialog.show();


                    mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("loginpage", "signInWithEmail:onComplete:" + task.isSuccessful());

                                    if (!task.isSuccessful()) {
                                        Log.w("loginpage", "signInWithEmail:failed", task.getException());
                                        Toast.makeText(LoginActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText( getApplicationContext(),"User SIGNED IN ",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), SignUp.class);
                                        startActivity(intent);
                                    }

                                } });
                }});
        }
    }


