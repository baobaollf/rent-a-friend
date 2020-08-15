package com.rent_a_friend.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.rent_a_friend.R;

public class HomeFragment extends Fragment
        implements View.OnClickListener {
    //private Button mLogin;
    private HomeViewModel homeViewModel;
    public NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

       // inflate the layout for this fragment
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });
        return root;
    }

    @Override
    public void onViewCreated(View v,Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);
        navController = Navigation.findNavController(v);
        v.findViewById(R.id.login).setOnClickListener(this);
        v.findViewById(R.id.register).setOnClickListener(this);

    }

        @Override
    public void onClick(View view){
        switch (view.getId())
        {
            case R.id.login:
                Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_navigation_login);
                break;
            case R.id.register:
                Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_navigation_register);
                break;
        }
    }


}
