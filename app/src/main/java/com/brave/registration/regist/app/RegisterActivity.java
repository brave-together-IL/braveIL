package com.brave.registration.regist.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.brave.registration.regist.app.model.User;
import com.brave.registration.regist.app.services.ApiService;
import com.brave.registration.regist.app.viewmodels.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    private Button btnAddHero;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText firstNameInput;

    // ViewModel
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        emailInput = findViewById(R.id.etRegisterEmail);
        passwordInput = findViewById(R.id.etRegisterPassword);
        firstNameInput = findViewById(R.id.etRegisterUser);

        setupBtnAddHero();

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        observeAnyChange();
    }

    //Observing any data change
    private void observeAnyChange() {
        userViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                //Observing for any data change
                Log.d("OBSERVE", "Change");
            }
        });
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
                userViewModel.registerUser(user);
            }
        });
    }
}