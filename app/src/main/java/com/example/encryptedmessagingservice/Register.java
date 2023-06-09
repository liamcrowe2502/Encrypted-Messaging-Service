package com.example.encryptedmessagingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class Register extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);

        Button regButton = findViewById(R.id.regButton);
        Button switchButton = findViewById(R.id.switchButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegistration();
            }
        });

        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));
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

    private void attemptRegistration() {
        String usernames = username.getText().toString();
        String emails = email.getText().toString();
        String password = pass.getText().toString();

        if (TextUtils.isEmpty(usernames) || TextUtils.isEmpty(emails) || TextUtils.isEmpty(password)) {
            Snackbar mySnackbar = Snackbar.make(email, "Please fill in all fields", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else if (!Validation.isValidEmail(emails)) {
            Snackbar mySnackbar = Snackbar.make(email, "Invalid email format", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else if (!Validation.isValidPassword(password)) {
            Snackbar mySnackbar = Snackbar.make(pass, "Invalid password format", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else {
            startActivity(new Intent(Register.this, MainPage.class));
        }
    }
}
