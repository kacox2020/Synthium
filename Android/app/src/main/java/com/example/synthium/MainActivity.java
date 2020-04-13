package com.example.synthium;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.synthium.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements MusicFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
