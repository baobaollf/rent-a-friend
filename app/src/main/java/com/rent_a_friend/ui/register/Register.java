package com.rent_a_friend.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rent_a_friend.MainActivity;
import com.rent_a_friend.R;
import com.rent_a_friend.ui.login.Login;

public class Register extends AppCompatActivity  implements View.OnClickListener{
    private RegisterViewModel registerViewModel;
    EditText name;
    EditText password;
    EditText phone;
    Button register;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        phone = (EditText) findViewById(R.id.phone);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.register:
                startActivity(new Intent(this, Login.class));
                break;
        }

    }


}
