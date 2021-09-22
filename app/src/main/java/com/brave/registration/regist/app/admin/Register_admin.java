package com.brave.registration.regist.app.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.brave.registration.regist.app.Client;

import com.brave.registration.regist.app.R;

public class Register_admin extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);
        Button send_hero = (Button) findViewById(R.id.send_detail);
        Client hero = new Client();

        String str_username =((EditText)findViewById(R.id.user_name_input)).getText().toString();
        String str_birthday =((EditText)findViewById(R.id.user_birthday)).getText().toString();
        String str_id = ((EditText)findViewById(R.id.user_id_input)).getText().toString();
        String str_phone = ((EditText)findViewById(R.id.user_phone)).getText().toString();

        send_hero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    hero.createUser(str_username, str_birthday, str_id,"",str_phone);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void back_homepage(View view){
        startActivity(new Intent(getApplicationContext(), AdminActivity.class));
    }





}