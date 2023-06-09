package com.example.encryptedmessagingservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    }

    private void attemptLogin() {
        String emails = email.getText().toString();
        String password = pass.getText().toString();

        if (TextUtils.isEmpty(emails) || TextUtils.isEmpty(password)) {
            Snackbar mySnackbar = Snackbar.make(email, "Please fill in all fields", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else {
            startActivity(new Intent(Login.this, MainPage.class));
        }
    }
}
