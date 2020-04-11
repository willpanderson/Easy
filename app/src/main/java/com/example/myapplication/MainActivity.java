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

public class MainActivity extends AppCompatActivity {

    EditText email, passwod;
    Button btnSignUp;
    TextView text;
    FirebaseAuth mfirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    Toast.makeText(MainActivity.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                }
                else if (!(email2.isEmpty() && pass.isEmpty())){
                    mfirebaseAuth.createUserWithEmailAndPassword(email2,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                         if(!task.isSuccessful()){
                             Toast.makeText(MainActivity.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                         }
                         else {
                            Intent s = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(s);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });
     text.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent test = new Intent(MainActivity.this, LoginActivity.class);
             startActivity(test);
         }
     });
    }

}
