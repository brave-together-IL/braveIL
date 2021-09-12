package ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.brave.registration.regist.app.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        final Button add_survivor = (Button) findViewById(R.id.survivor_btn);
        add_survivor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_survivor();
            }
        });
        final Button add_moderators = (Button) findViewById(R.id.moderator_btn);
        add_moderators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_moderators();
            }
        });

    }

    private void add_survivor() {
        Intent intent = new Intent(this, Register_admin.class);
        startActivity(intent);
    }


    private void add_moderators() {
        Intent intent = new Intent(this, Register_moderator.class);
        startActivity(intent);
    }

}