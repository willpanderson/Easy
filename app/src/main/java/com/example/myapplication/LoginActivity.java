package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText email, passwod;
    Button btnSignUp;
    TextView text;
    FirebaseAuth mfirebaseAuth;
    private FirebaseAuth.AuthStateListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mfirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editText);
        passwod = findViewById(R.id.editText2);
        btnSignUp = findViewById(R.id.button);
        text = findViewById(R.id.textView);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2 = email.getText().toString();
                String pass = passwod.getText().toString();
                if (email2.isEmpty()){
                    email.setError("Enter a email");
                    email.requestFocus();
                }
                else if (pass.isEmpty()){
                    passwod.setError("Enter a password");
                    passwod.requestFocus();
                }
                else if (email2.isEmpty() && pass.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                }
                else if (! (email2.isEmpty() && pass.isEmpty())){
                    mfirebaseAuth.signInWithEmailAndPassword(email2,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login ", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Intent p = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(p);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(LoginActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mListener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser mUser = mfirebaseAuth.getCurrentUser();

            @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mUser != null){
                    Toast.makeText(LoginActivity.this, "Logged in",Toast.LENGTH_SHORT).show();
                    Intent move = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(move);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Log in Failed",Toast.LENGTH_SHORT).show();
                }
                }
        };
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent test = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(test);
            }
            });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(signup);
            }
        });

        }
        @Override
        protected void onStart() {
        super.onStart();
        mfirebaseAuth.addAuthStateListener(mListener);
        }}



