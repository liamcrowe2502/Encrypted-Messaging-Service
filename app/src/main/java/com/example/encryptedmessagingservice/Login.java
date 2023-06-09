package com.example.encryptedmessagingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.encryptedmessagingservice.Validation;


import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {

    private EditText email;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.loginButton);
        Button switchButton = findViewById(R.id.switchButton);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    Snackbar mySnackbar = Snackbar.make(pass, "Password must be 8-32 characters long with at least one uppercase letter, one lowercase letter, one number, and one special character.", Snackbar.LENGTH_LONG);
                    mySnackbar.show();
                }
            }
        });

    }

    private void attemptLogin() {
        String emails = email.getText().toString();
        String password = pass.getText().toString();

        if (TextUtils.isEmpty(emails) || TextUtils.isEmpty(password)) {
            Snackbar mySnackbar = Snackbar.make(email, "Please fill in all fields", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        }else if (!Validation.isValidEmail(emails)) {
            Snackbar mySnackbar = Snackbar.make(email, "Invalid email format", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else if (!Validation.isValidPassword(password)) {
            Snackbar mySnackbar = Snackbar.make(pass, "Invalid password format", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else {
            startActivity(new Intent(Login.this, MainPage.class));
        }
    }
}
