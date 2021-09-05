package com.brave.registration.regist.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class Register_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        String str_username =((EditText)findViewById(R.id.user_name_input)).getText().toString();
        String str_id =((EditText)findViewById(R.id.user_name_input)).getText().toString();
    }
}