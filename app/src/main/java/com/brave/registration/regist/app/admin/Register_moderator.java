package com.brave.registration.regist.app.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.brave.registration.regist.app.Client;
import com.brave.registration.regist.app.R;
import com.brave.registration.regist.app.ui.login.User;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;


public class Register_moderator extends AppCompatActivity {
    Button registerMod;
    Client client;
    User moderator;
    TextInputLayout passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moderator);
        Client moderator = new Client();

        client = new Client();
        registerMod = (Button) findViewById(R.id.sendNewMod);
        EditText usernameEditText = findViewById(R.id.username);
        EditText passwordEditText = findViewById(R.id.password);
        ProgressBar loadingProgressBar = findViewById(R.id.loading);
        registerMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
              //  moderator = new User(username, password);
               // Toast.makeText(Register_moderator.this, "המשתמש נוצר", Toast.LENGTH_LONG).show();
                try {
                    moderator.createUser(username, "", "", "", password);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void back_homepage(View v) {
        startActivity(new Intent(getApplicationContext(), AdminActivity.class));
    }
}