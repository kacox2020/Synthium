package com.example.synthium;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddSong extends AppCompatActivity {
    private String websiteURL = "https://mopsdev.bw.edu/~kcox18/Synthium/WebService/rest.php/song";
    private String websiteAudioURL = "https://mopsdev.bw.edu/~kcox18/Synthium/AudioFiles/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        Button addSongButton = findViewById(R.id.addbtn);
        final EditText songURL = findViewById(R.id.songURL);
        songURL.setText(websiteAudioURL);

        addSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText songName = findViewById(R.id.songName);
                EditText songArtist = findViewById(R.id.songArtist);
                EditText songMin = findViewById(R.id.lengthMinutes);
                EditText songSec = findViewById(R.id.lengthSeconds);
                if(songName.length() == 0){
                    Toast.makeText(getApplicationContext(), R.string.EnterInValues, Toast.LENGTH_SHORT).show();

                }
                else if(songArtist.length()== 0){
                    Toast.makeText(getApplicationContext(), R.string.EnterInValues, Toast.LENGTH_SHORT).show();

                }
                else if(songMin.length() == 0){
                    Toast.makeText(getApplicationContext(), R.string.EnterInValues, Toast.LENGTH_SHORT).show();


                }
                else if (songSec.length() == 0){
                    Toast.makeText(getApplicationContext(), R.string.EnterInValues, Toast.LENGTH_SHORT).show();

                }
                else if(songURL.length() == 0) {
                    Toast.makeText(getApplicationContext(), R.string.EnterInValues, Toast.LENGTH_SHORT).show();


                }
                else{
                int songMinutes = Integer.parseInt(songMin.getText().toString()) * 60;
                int songSeconds = Integer.parseInt(songSec.getText().toString());
                int totalSongLength = songMinutes + songSeconds;

                JSONObject songObject = new JSONObject();
                try {
                    songObject.put("songTitle", songName.getText().toString());
                    songObject.put("songArtist", songArtist.getText().toString());
                    songObject.put("songLength", totalSongLength);
                    songObject.put("songURL", songURL.getText().toString());
                } catch (JSONException je) {
                    je.printStackTrace();
                }

                ServiceClient serviceClient = ServiceClient.getInstance(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, websiteURL, songObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), R.string.Created, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), R.string.An_error, Toast.LENGTH_SHORT).show();
                    }
                });
                serviceClient.addRequest(request);
                }
            }
        });

    }
}
