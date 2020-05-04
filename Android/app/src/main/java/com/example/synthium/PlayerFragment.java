package com.example.synthium;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private ImageView imagePlayPause;
    private TextView textCurrentTime, textTotalDuration;
    private SeekBar playerSeekBar;
    private Handler handler = new Handler();



    public PlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Proper handling of media player when starting new song or leaving app
        if (mediaPlayer != null) {
            handler.removeCallbacks(updater);
            mediaPlayer.pause();
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        Song selectedSong = new Song(bundle.getString("songTitle"), bundle.getString("songArtist"), bundle.getInt("songId"), bundle.getInt("songLength"), bundle.getString("songURL"));
        ArrayList<String> songUrlList = bundle.getStringArrayList("songUrlList");

        View view = inflater.inflate(R.layout.fragment_player, container, false);

        imagePlayPause = view.findViewById(R.id.imagePlayPause);
        textCurrentTime = view.findViewById(R.id.testCurrentTime);
        textTotalDuration = view.findViewById(R.id.testTotalDuration);
        playerSeekBar = view.findViewById(R.id.playerSeekBar);
        mediaPlayer = new MediaPlayer();



        playerSeekBar.setMax(100);

        imagePlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    imagePlayPause.setImageResource(R.drawable.ic_play);

                }
                else {
                    mediaPlayer.start();
                    imagePlayPause.setImageResource(R.drawable.ic_pause);
                    updateSeekBar();
                }
            }
        });
        prepareMediaPlayer(selectedSong);
        return view;


    }

    private void prepareMediaPlayer(Song song){




        String url = song.songURL;

        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            textTotalDuration.setText(millisecondsToTimer(mediaPlayer.getDuration()));


        }catch (Exception e){
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();


        }


    }

    private  Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            textCurrentTime.setText(millisecondsToTimer(currentDuration));
        }
    };

    private void updateSeekBar() {
        playerSeekBar.setProgress((int)(((float)mediaPlayer.getCurrentPosition()/mediaPlayer.getDuration() )* 100));
        handler.postDelayed(updater,1000);
    }

    private String millisecondsToTimer(long milliSecond){
        String timerString = "";
        String secondsString;

        int hours = (int)(milliSecond/(1000 * 60 * 60));
        int minutes = (int)(milliSecond % (1000 * 60 * 60))/ (1000 * 60);
        int seconds = (int)(milliSecond % (1000 * 60 * 60)) % (1000 * 60)/1000;

        if (hours > 0){
            timerString = hours + ":";

        }
        if(seconds > 0){
            secondsString = "0"+ seconds;
            if(seconds >= 10){
                secondsString = ""+ seconds;

            }

        }
        else if(seconds == 0){
            secondsString = "0"+ seconds;
        }

        else {
            secondsString = "" + seconds;
        }
        timerString = timerString + minutes + ":" + secondsString;
        return  timerString;
    }




    }

