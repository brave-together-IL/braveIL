package com.brave.registration.regist.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.brave.registration.regist.app.model.User;
import com.brave.registration.regist.app.services.ApiService;

import java.text.BreakIterator;

public class RegisterActivity extends AppCompatActivity {

    private Button btnAddHero;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText firstNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        emailInput = findViewById(R.id.etRegisterEmail);
        passwordInput = findViewById(R.id.etRegisterPassword);
        firstNameInput = findViewById(R.id.etRegisterUser);


        setupBtnAddHero();

    }

    private void setupBtnAddHero() {
        btnAddHero = findViewById(R.id.btnAddHero);
        btnAddHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString();
                String password = passwordInput.getText().toString();
                String firstName = firstNameInput.getText().toString();
                User user = new User( email, password, "", firstName, "");
//                ApiService.getUser(1);
                ApiService.saveUser(user);
            }
        });
    }
}