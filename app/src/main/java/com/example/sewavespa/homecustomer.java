package com.example.sewavespa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.sewavespa.ui.home.HomeFragment;
import com.example.sewavespa.ui.order.OrderFragment;
import com.example.sewavespa.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homecustomer extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homecustomer);

        navigationView = findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(selectedListener);

        Fragment fg = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fg, "");
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener selectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home_nav:
                    HomeFragment fragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, fragment, "");
                    fragmentTransaction.commit();
                    return true;

                case R.id.history_nav:
                    OrderFragment orderFragment = new OrderFragment();
                    FragmentTransaction orderFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    orderFragmentTransaction.replace(R.id.content, orderFragment, "");
                    orderFragmentTransaction.commit();
                    return true;

                case R.id.profle_nav:
                    ProfileFragment profileFragment = new ProfileFragment();
                    FragmentTransaction profileFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    profileFragmentTransaction.replace(R.id.content, profileFragment, "");
                    profileFragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };
}