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

public class Register extends AppCompatActivity {
    private RegisterViewModel registerViewModel;
    private static EditText name;
    private static EditText password;
    private static EditText phonenumber;
    private static Button register_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        RegisterButton();
    }

    private void RegisterButton() {
        name = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        phonenumber = (EditText)findViewById(R.id.phone);
        register_btn = (Button)findViewById(R.id.register);

        register_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(name.getText().toString().equals("user") &&
                                password.getText().toString().equals("pass") &&
                                    phonenumber.getText().toString().equals("2345"))  {
                            Toast.makeText(Register.this,"User and Password is correct",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent("com.rent_a_person.Login");
                            startActivity(intent);
                        }
                    }
                }
        );
    }


}
