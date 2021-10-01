package com.brave.registration.regist.app.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.brave.registration.regist.app.R;

public class AdminActivity extends AppCompatActivity {
    Button add_user_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


       add_user_btn = (Button) findViewById(R.id.add_user_btn);
       add_user_btn.setOnClickListener(new View.OnClickListener() {
       @Override
            public void onClick(View v){
                add_new_users();
            }
       });
    }

    public void add_new_users(){
        Intent intent = new Intent(this, Users_register_admin.class);
        startActivity(intent);
    }



}