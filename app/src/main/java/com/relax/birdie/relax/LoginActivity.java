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

/**
 * Sign in activity
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize widget objects
        editTextEmail = findViewById(R.id.mailText);
        editTextPassword = findViewById(R.id.passwordText);
        buttonSignIn = findViewById(R.id.signIn);
        textViewSignUp = findViewById(R.id.signUp);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        // Go directly into profile page if user has been logged in
        if(firebaseAuth.getCurrentUser() != null) {
            // start profile activity
            System.out.println("Email of the current user: "+ firebaseAuth.getCurrentUser().getEmail());
        }

        buttonSignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonSignIn) {
            userLogin();
        }
        if (v == textViewSignUp) {
            // will open registration activity here
            userSignUp();
        }
    }

    private void userSignUp() {
        String textEmail = editTextEmail.getText().toString().trim();
        String textPassword = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(textEmail)) {
            // Text is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(textPassword)) {
            // Password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase registration step
        firebaseAuth.createUserWithEmailAndPassword(textEmail, textPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void userLogin() {
        String textEmail = editTextEmail.getText().toString().trim();
        String textPassword = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(textEmail)) {
            // Text is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(textPassword)) {
            // Password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        // If validations are OK, it will first show a progress bar
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        // Firebase sign in step
        firebaseAuth.signInWithEmailAndPassword(textEmail, textPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // start profile activity
//                            finish();
//                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            Toast.makeText(LoginActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
