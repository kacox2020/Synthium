package com.example.synthium;

import android.os.Bundle;

import com.example.synthium.dummy.DummyContent;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements MusicFragment.OnListFragmentInteractionListener{
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.homeFragment:
                    mTextMessage.setText("HOME");
                    return true;
                case R.id.musicFragment:
                    mTextMessage.setText("MUSIC");
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
