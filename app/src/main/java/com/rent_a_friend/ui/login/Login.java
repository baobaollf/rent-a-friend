package com.rent_a_friend.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rent_a_friend.MainActivity;
import com.rent_a_friend.R;
import com.rent_a_friend.ui.login.LoginViewModel;
import com.rent_a_friend.ui.login.LoginViewModelFactory;
import com.rent_a_friend.ui.register.Register;

public class Login extends AppCompatActivity  {

     LoginViewModel loginViewModel;
     EditText username;
     EditText password;
     Button login;
     TextView createBtn;
     FirebaseAuth fAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        createBtn = findViewById(R.id.createText);
        fAuth = FirebaseAuth.getInstance();

        //if user already login sent to login page
//        if(fAuth.getCurrentUser() == null){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            finish();
//        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString().trim();
                String upassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    username.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(upassword)) {
                    password.setError("Password is Required");
                    return;
                }

                if (upassword.length() < 6) {
                    password.setError("Password Must be At least 6 characters");
                    return;
                }

                //authenticate user
                fAuth.signInWithEmailAndPassword(email, upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }

            });

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
        }
    }

