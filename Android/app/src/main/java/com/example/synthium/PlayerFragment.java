package com.example.synthium;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends Fragment {

    public PlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        Song selectedSong = new Song(bundle.getString("songTitle"), bundle.getString("songArtist"), bundle.getInt("songId"), bundle.getInt("songLength"));

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_player, container, false);

        TextView test = view.findViewById(R.id.superTestString);
        test.setText(selectedSong.songTitle);

        return view;
    }
}
