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


public class LoginActivity extends AppCompatActivity{
    private EditText password, email;
    private FirebaseAuth mAuth;
    private Button signIn, signUp;
    private ImageView relaxImage;
    private ProgressDialog progressDialog;

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

            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // will open registration activity here
                    finish();
                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
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


                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d("loginpage", "signInWithEmail:onComplete:" + task.isSuccessful());

                                    if (!task.isSuccessful()) {
                                        Log.w("loginpage", "signInWithEmail:failed", task.getException());
                                        Toast.makeText(LoginActivity.this, "hata", Toast.LENGTH_SHORT).show();
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


