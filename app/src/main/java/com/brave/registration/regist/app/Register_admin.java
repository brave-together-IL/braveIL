package com.brave.registration.regist.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.brave.registration.regist.app.ui.login.LoginActivity;

public class Register_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        String str_username =((EditText)findViewById(R.id.user_name_input)).getText().toString();
        String str_id =((EditText)findViewById(R.id.user_name_input)).getText().toString();
    }
    public void back_homepage(View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}