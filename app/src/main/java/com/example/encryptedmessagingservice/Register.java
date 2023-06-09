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
    }

    private void attemptRegistration() {
        String usernames = username.getText().toString();
        String emails = email.getText().toString();
        String password = pass.getText().toString();

        if (TextUtils.isEmpty(usernames) || TextUtils.isEmpty(emails) || TextUtils.isEmpty(password)) {
            Snackbar mySnackbar = Snackbar.make(email, "Please fill in all fields", Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else {
            startActivity(new Intent(Register.this, MainPage.class));
        }
    }
}
