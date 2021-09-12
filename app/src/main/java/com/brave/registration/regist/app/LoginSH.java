package com.brave.registration.regist.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginSH extends AppCompatActivity {

    Button btnOpenRegisterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sh);
        setupBtnOpenRegisterActivity();
    }

    private void setupBtnOpenRegisterActivity() {
        btnOpenRegisterActivity = findViewById(R.id.btnNewLoginOpenRegisterActivity);
        btnOpenRegisterActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginSH.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}