package com.rent_a_friend.ui.home;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.rent_a_friend.R;
import com.rent_a_friend.ui.login.Login;
import com.rent_a_friend.ui.register.Register;

public class Home extends AppCompatActivity {

    private HomeViewModel homeViewModel;
    private Button login, register;
    SharedPreferences sp, rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        login = (Button) findViewById(R.id.login);

       // FirebaseAuth.getInstance().signOut();


       //sp = getSharedPreferences("login",MODE_PRIVATE);
       //rg = getSharedPreferences("register", MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show activity page if not logged in before

                Intent myIntent = new Intent(v.getContext(), Login.class);
                startActivityForResult(myIntent, 0);
            }

        });

        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to register page
                    Intent myIntent = new Intent(v.getContext(), Register.class);
                    startActivityForResult(myIntent, 1);
            }

        });

    }
}




