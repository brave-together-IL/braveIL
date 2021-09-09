package com.brave.registration.regist.app;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.brave.registration.regist.app.ui.login.LoginActivity;
import static com.brave.registration.regist.app.TelegramHandler.sendTelegramMessage;

public class MainActivity extends AppCompatActivity {
    TextView id_1,phone_1;
    String ID, phone;
    Button newly;
    ImageButton accompany, meeting, delivery, call;
    Client client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accompany = (ImageButton) findViewById(R.id.accompanyButton);
        delivery = (ImageButton) findViewById(R.id.deliveryButton);
        meeting = (ImageButton) findViewById(R.id.meetingButton);
        call = (ImageButton) findViewById(R.id.callButton);
//      newly= (Button) findViewById(R.id.button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.thetoolbar);
        setSupportActionBar(toolbar);

        SharedPreferences preferences_2 = getSharedPreferences("Data", MODE_PRIVATE);
        ID = preferences_2.getString("id", ""); /*The ID of the HERO*/
        phone = preferences_2.getString("number", ""); /*The phone no. of the HERO*/
        client = new Client();

        try{
            client.refreshToken();
        }catch(Exception e){e.printStackTrace();}

        accompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "הודעתך נשלחה", Toast.LENGTH_LONG).show(); /* if the message was sent properly*/
                String message_text  ="שלום מתנדבים יקרים!" + "%0A" + "אצטרך את עזרתכם בליווי :)" + "%0A" + "אשמח אם תוכלו לחזור אליי לנייד: " + phone;
                sendTelegramMessage(ID,message_text);
                try {
                    client.createEvent(new String[]{"waiting","accompany"}, "ליווי", "התנדבות חדשה", "", "", 0, "");
                }catch(Exception e){e.printStackTrace();}

            }

        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "הודעתך נשלחה", Toast.LENGTH_SHORT).show();/* if the message was sent properly*/
                String message_text = "שלום מתנדבים יקרים!" + "%0A" + "אשמח לקיים שיחה טלפונית, אודה לעזרתכם :)" + "%0A" + "מספר הפלאפון שלי: " + phone;
                sendTelegramMessage(ID,message_text);
                try {
                    client.createEvent(new String[]{"waiting","call"}, "שיחה", "התנדבות חדשה", "", "", 0, "");
                }catch(Exception e){e.printStackTrace();}

            }

        });
        meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "הודעתך נשלחה", Toast.LENGTH_SHORT).show(); /* if the message was sent properly*/
                String message_text = "שלום מתנדבים יקרים!" + "%0A" + "אשמח אם נוכל לקבוע פגישה :)" + "%0A" + "מספר הפלאפון שלי לתיאום הפגישה: " + phone;
                sendTelegramMessage(ID,message_text);
                try {
                    client.createEvent(new String[]{"waiting","meeting"}, "פגישה", "התנדבות חדשה", "", "", 0, "");
                }catch(Exception e){e.printStackTrace();}}

        });


        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "הודעתך נשלחה", Toast.LENGTH_SHORT).show(); /* if the message was sent properly*/
                String message_text = "שלום מתנדבים יקרים!" + "%0A" + "אשמח לעזרה באיסוף תרופות/מצרכים :)" + "%0A" + "מספר הפלאפון שלי לפרטים נוספים: " + phone;
                sendTelegramMessage(ID,message_text);
                try {
                    client.createEvent(new String[]{"waiting","delivery"}, "איסוף מוצר", "התנדבות חדשה", "", "", 0, "");
                }catch(Exception e){e.printStackTrace();}}

        });

//        newly.setOnClickListener(new View.OnClickListener() { /* resign in*/
//            @Override
//            public void onClick(View v) {
//
//                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("remember", "false");
//                editor.apply();
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//            }

  //      });
    }



}