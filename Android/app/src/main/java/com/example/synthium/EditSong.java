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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditSong extends AppCompatActivity {
    private String websiteURL = "https://mopsdev.bw.edu/~kcox18/Synthium/WebService/rest.php/song";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);

        Bundle extras = getIntent().getExtras();
        final Song selectedSong = new Song(extras.getString("songTitle"), extras.getString("songArtist"), extras.getInt("songID"), extras.getInt("songLength"), extras.getString("songURL"));

        final EditText songName = findViewById(R.id.songName);
        songName.setText(selectedSong.songTitle);

        final EditText songArtist = findViewById(R.id.songArtist);
        songArtist.setText(selectedSong.songArtist);

        final EditText songMin = findViewById(R.id.lengthMinutes);
        final EditText songSec = findViewById(R.id.lengthSeconds);

        // Calculate Song Length
        int songMinutes = selectedSong.songLength/60;
        int songSeconds = selectedSong.songLength%60;

        songMin.setText(String.valueOf(songMinutes));
        songSec.setText(String.valueOf(songSeconds));

        final EditText songURL = findViewById(R.id.songURL);
        songURL.setText(selectedSong.songURL);

        final String completedURL = websiteURL + "/" + selectedSong.songID;

        // Update Button and Functions
        Button updateBtn = findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int songMinutes = Integer.parseInt(songMin.getText().toString()) * 60;
                int songSeconds = Integer.parseInt(songSec.getText().toString());
                int totalSongLength = songMinutes + songSeconds;

                JSONObject songObject = new JSONObject();
                try {
                    songObject.put("songID", selectedSong.songID);
                    songObject.put("songTitle", songName.getText().toString());
                    songObject.put("songArtist", songArtist.getText().toString());
                    songObject.put("songLength", totalSongLength);
                    songObject.put("songURL", songURL.getText().toString());
                } catch (JSONException je) {
                    je.printStackTrace();
                }

                ServiceClient serviceClient = ServiceClient.getInstance(getApplicationContext());
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, websiteURL, songObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), R.string.Updated, Toast.LENGTH_SHORT).show();
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
        });

        // Delete Button and Functions
        Button deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceClient serviceClient = ServiceClient.getInstance(getApplicationContext());
                StringRequest request = new StringRequest(Request.Method.DELETE, completedURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), R.string.Deleted, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    // onServer Error
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), R.string.An_error, Toast.LENGTH_SHORT).show();
                    }
                });
                serviceClient.addRequest(request);
            }
        });
    }
}
