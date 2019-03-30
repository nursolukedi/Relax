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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private EditText password , email ;
    private FirebaseAuth mAuth;
    private Button signIn, signUp;
    private ImageView relaxImage;
    private ProgressDialog progressDialog;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        password = findViewById(R.id.passwordText);
        email = findViewById(R.id.mailText);
        relaxImage = findViewById(R.id.relaxImage);
        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.signUp);
        relaxImage = findViewById(R.id.relaxImage);

        progressDialog = new ProgressDialog(this);

        if(mAuth.getCurrentUser() != null) {
            // start profile activity
            System.out.println("Email of the current user: "+ mAuth.getCurrentUser().getEmail());
            finish();
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
        }

        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == signIn) {
            userLogin();
        }
        if (v == signUp) {
            // will open registration activity here
            finish();
            startActivity(new Intent(this, Dashboard.class));
        }
    }

    private void userLogin() {
        String textEmail = email.getText().toString().trim();
        String textPassword = password.getText().toString().trim();

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
        mAuth.signInWithEmailAndPassword(textEmail, textPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // start profile activity
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
