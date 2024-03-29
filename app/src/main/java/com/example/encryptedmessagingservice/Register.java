package com.example.encryptedmessagingservice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private EditText username, regEmail, regPass;
    Button regButton;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainPage.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPass = findViewById(R.id.pass);
        mAuth = FirebaseAuth.getInstance();
        Button switchButton = findViewById(R.id.switchButton);
        regButton= findViewById(R.id.regButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptValidation();

                String email, password;
                email = String.valueOf(regEmail.getText());
                password = String.valueOf(regPass.getText());

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Authentication successful.",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
//Ggthy146_1
        regPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Snackbar mySnackbar = Snackbar.make(regPass, "Password must be 8-32 characters long with at least one uppercase letter, one lowercase letter, one number, and one special character.", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                }
            }
        });
    }

    private void attemptValidation() {
        String usernames = username.getText().toString();
        String emails = regEmail.getText().toString();
        String password = regPass.getText().toString();

        if (TextUtils.isEmpty(usernames) || TextUtils.isEmpty(emails) || TextUtils.isEmpty(password)) {
            Snackbar mySnackbar = Snackbar.make(regEmail, "Please fill in all fields", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else if (!Validation.isValidEmail(emails)) {
            Snackbar mySnackbar = Snackbar.make(regEmail, "Invalid email format", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else if (!Validation.isValidPassword(password)) {
            Snackbar mySnackbar = Snackbar.make(regPass, "Invalid password format", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else {
            startActivity(new Intent(Register.this, MainPage.class));
        }
    }
}
