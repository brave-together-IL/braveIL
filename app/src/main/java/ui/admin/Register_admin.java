package ui.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.brave.registration.regist.app.MainActivity;
import com.brave.registration.regist.app.ui.login.LoginActivity;

import com.brave.registration.regist.app.R;

public class Register_admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.thetoolbar);
        setSupportActionBar(toolbar);

        String str_username =((EditText)findViewById(R.id.user_name_input)).getText().toString();
        String str_id =((EditText)findViewById(R.id.user_name_input)).getText().toString();
    }
    public void back_homepage(View view){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}