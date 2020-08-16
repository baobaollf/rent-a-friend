package com.rent_a_friend.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rent_a_friend.MainActivity;
import com.rent_a_friend.R;
import com.rent_a_friend.ui.login.LoginViewModel;
import com.rent_a_friend.ui.login.LoginViewModelFactory;

public class Login extends AppCompatActivity implements View.OnClickListener {

     LoginViewModel loginViewModel;
     EditText username;
     EditText password;
     Button login;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);

        login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login:
                startActivity(new Intent(this, MainActivity.class));
                break;

            }

        }

}
