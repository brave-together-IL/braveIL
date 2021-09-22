package com.brave.registration.regist.app.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.brave.registration.regist.app.MainActivity;
import com.brave.registration.regist.app.R;
import com.brave.registration.regist.app.db.entities.User;
import com.brave.registration.regist.app.db.entities.UserAndRole;
import com.brave.registration.regist.app.viewmodels.UserViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = LoginActivity.class.getSimpleName();


    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ProgressBar loadingProgressBar;

    private UserViewModel userViewModel;
    private LiveData<UserAndRole> ldLoggedUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        observeLoginChange();
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);


        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);

        String checkbox = preferences.getString("remember", "false");

        if (checkbox.equals("true")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (checkbox.equals("false")) {
            Toast.makeText(this, "אנא הרשמו", Toast.LENGTH_SHORT).show();
        }

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateCredentialsInput();
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login();
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                login();
            }
        });

        initUserViewModel();
    }

    private void initUserViewModel() {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<UserAndRole>>() {
            @Override
            public void onChanged(List<UserAndRole> users) {
                Log.v(TAG, users.toString());
            }
        });
    }

    private void login() {
//        loginViewModel.login(getUserText(), getPasswordText());
    }

    private void observeLoginChange() {
//        loginViewModel.getLDLogin().observe(this, new Observer<User>() {
//            @Override
//            public void onChanged(User user) {
//                loadingProgressBar.setVisibility(View.GONE);
//                if ( user !=null) {
//                    //todo open activity by user role
//                    welcomeUser(user.getFirstName());
//                    openMainActivity();
//                }
//            }
//        });
    }

    private void openMainActivity() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    private void welcomeUser(String username) {

        String welcome = "ברוך הבא " + username;
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome , Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private String getUserText() {
        return usernameEditText.getText().toString();
    }

    private String getPasswordText() {
        return passwordEditText.getText().toString();
    }

    private void validateCredentialsInput() {
        loginButton.setEnabled(false);

        String username = getUserText();
        String password = getPasswordText();

        if ( !isUserNameValid( username)) {
            usernameEditText.setError(getString(R.string.invalid_username));
            return;
        }

        if ( !isPasswordValid(password)) {
            passwordEditText.setError(getString(R.string.invalid_password));
            return;
        }

        loginButton.setEnabled(true);

    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() == 10;
    }


}