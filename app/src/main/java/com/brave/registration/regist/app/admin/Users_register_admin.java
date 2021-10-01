package com.brave.registration.regist.app.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.brave.registration.regist.app.Client;

import com.brave.registration.regist.app.R;

public class Users_register_admin extends AppCompatActivity {
    Spinner spinn;
    String[] user_type = {"גיבור","אדמין","מודרייטור"};//{ "0","1","2","3" };
    /* TODO- decide a name for '0' */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        Button add_users = (Button) findViewById(R.id.send_new_user);
        Client hero = new Client();
        Client moderator = new Client();
        //spinn.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        String str_username = ((TextView) findViewById(R.id.user_name_input)).getText().toString();
        String str_city = ((TextView) findViewById(R.id.city_input)).getText().toString();
        String str_birthday = ((TextView) findViewById(R.id.user_birthday)).getText().toString();
        String str_id = ((TextView) findViewById(R.id.user_id_input)).getText().toString();
        String str_password = ((TextView) findViewById(R.id.password)).getText().toString();
        String str_phone = ((TextView) findViewById(R.id.phone_no)).getText().toString();
        spinn = (Spinner) findViewById(R.id.user_type);
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_item, user_type);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinn.setAdapter(ad);

        add_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = spinn.getSelectedItem().toString();
         //       if (item == "אדמין") {
        //        }
      //                  else if (item =="גיבור"){
       //         }
                //else{
              //  }
        //           try {
       //                hero.createUser(str_username, str_city, str_birthday, str_id, str_password, str_phone);
       //            } catch (Exception e) {
        //               e.printStackTrace();
       //            }
            //    add_users();
            }
    //    });


    //private void add_users() {
      //  Intent intent = new Intent(this, Users_register_admin.class);
        //startActivity(intent);

     });
    }

    public void back_homepage(View view) {
        startActivity(new Intent(getApplicationContext(), AdminActivity.class));
    }
}