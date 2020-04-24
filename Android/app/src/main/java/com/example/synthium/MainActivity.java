package com.example.synthium;

import android.content.res.ColorStateList;
import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.homeFragment:
                    //mTextMessage.setText("HOME");
                    return true;
                case R.id.musicFragment:
                    //mTextMessage.setText("MUSIC");
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.homeFragment);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);
        NavigationUI.setupWithNavController(navView, navHostFragment.getNavController());
//       mTextMessage = findViewById(R.id.message);
//       navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
