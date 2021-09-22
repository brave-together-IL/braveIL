package com.brave.registration.regist.app;

import static com.brave.registration.regist.app.TelegramHandler.sendTelegramMessage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.brave.registration.regist.app.viewmodels.TokenViewModel;


public class MainActivity extends AppCompatActivity {
    TextView id_1, phone_1;
    static String ID, phone;
    Button newly;
    ImageButton accompany, meeting, delivery, call;
    static Client client;

    TokenViewModel tokenViewModel;
    String token;


    // this class handle the dialogs in the hero's activity
    public static class HeroDialogs extends AppCompatDialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogCustom);

            String[] split = getTag().split(",");
            String buttonType = split[0];
            String dialogMessage = split[1];
            String type = split[2];

            String dialogMessageFormat = String.format(getString(R.string.dialogMessage), dialogMessage, buttonType);

            builder.setTitle(buttonType)
                    .setIcon(getResources().getIdentifier(type , "drawable", "com.brave.registration.regist.app"))
                    .setMessage(dialogMessageFormat)
                    .setPositiveButton("כן", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast toast = Toast.makeText(getActivity(),"בקשתך\n" + "ל" + buttonType +"\nהתקבלה"  , Toast.LENGTH_LONG); /* if the message was sent properly*/
                            ViewGroup group = (ViewGroup) toast.getView();
                            TextView messageTextView = (TextView) group.getChildAt(0);
                            messageTextView.setTextSize(70);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();

                            String message_text = String.format(getString(R.string.botMessage), buttonType, phone);
                            sendTelegramMessage(ID, message_text);
                            try {
                                client.createEvent(new String[]{"waiting", type}, buttonType, "התנדבות חדשה", "", "", 0, "");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    })
                    .setNegativeButton("לא", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

            return builder.create();
        }
    } //end of class HeroDialogs


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
//        client = new Client();
//
//        try {
//            client.refreshToken();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        accompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.accompany));
            }

        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.call));
            }

        });
        meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.meeting));
            }

        });


        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getString(R.string.delivery));
            }

        });

        initTokenViewModel();

        tokenViewModel.getTokenApi(getAuth());


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

    // this function operates the dialogs
    public void openDialog(String buttonType) {
        HeroDialogs heroDialogs = new HeroDialogs();
        getSupportFragmentManager().beginTransaction().add(heroDialogs, buttonType).commitNow();
        AlertDialog dialog= (AlertDialog)((HeroDialogs)getSupportFragmentManager().findFragmentByTag(buttonType)).getDialog();
        ((TextView)dialog.findViewById(android.R.id.message)).setTextSize(30);

//        heroDialogs.show(getSupportFragmentManager(), buttonType);
//        System.out.println("AAA" + heroDialogs.getParentFragmentManager().getFragments());
    }

    private void initTokenViewModel() {
        tokenViewModel = new ViewModelProvider(this).get(TokenViewModel.class);
        tokenViewModel.getLDToken().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String token) {
                setToken(token);
            }
        });
    }

    private String getAuth() {
        String auth = "dummy";
        return auth;
    }

    private void setToken(String token) {
        this.token = token;
    }
}
