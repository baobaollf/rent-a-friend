package com.rent_a_friend.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rent_a_friend.MainActivity;
import com.rent_a_friend.R;
import com.rent_a_friend.ui.login.Login;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private RegisterViewModel registerViewModel;
    EditText fullName, username;
    EditText password;
    EditText phone;
    Button register;
    FirebaseAuth fAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName  = (EditText) findViewById(R.id.fullName);
        username = (EditText)findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
        register = (Button) findViewById(R.id.register);
        fAuth = FirebaseAuth.getInstance();

        //if user already registered sent to login page
//        if(fAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(),Login.class));
//            finish();
//        }

        register.setOnClickListener(this);

        }
            @Override
            public void onClick(View v) {
                String email = username.getText().toString().trim();
                String upassword = password.getText().toString().trim();
                String Phone = phone.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    username.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(upassword)){
                    password.setError("Password is Required");
                    return;
                }

                if(upassword.length() < 6){
                    password.setError("Password Must be At least 6 characters");
                    return;
                }

                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,upassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }else {
                            Toast.makeText(Register.this, "Error! "  + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

}
