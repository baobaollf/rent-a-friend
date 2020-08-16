package com.rent_a_friend.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.rent_a_friend.MainActivity;
import com.rent_a_friend.R;
import com.rent_a_friend.ui.home.HomeViewModel;
import com.rent_a_friend.ui.home.HomeFragment;
import com.rent_a_friend.ui.login.Login;
import com.rent_a_friend.ui.login.LoginFragment;
import com.rent_a_friend.ui.register.Register;
import com.rent_a_friend.ui.register.RegisterFragment;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private HomeViewModel homeViewModel;
    private Button login, register;
    SharedPreferences sp, rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        login = (Button) findViewById(R.id.login);

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




