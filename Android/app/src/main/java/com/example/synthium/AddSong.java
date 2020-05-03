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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        Button addSongButton = findViewById(R.id.addbtn);

        addSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText songName = findViewById(R.id.songName);
                EditText songArtist = findViewById(R.id.songArtist);
                EditText songMin = findViewById(R.id.lengthMinutes);
                EditText songSec = findViewById(R.id.lengthSeconds);
                EditText songURL = findViewById(R.id.songURL);

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
                        Toast.makeText(getApplicationContext(), "Created!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), "An Error has occurred.", Toast.LENGTH_SHORT).show();
                    }
                });
                serviceClient.addRequest(request);
            }
        });

    }
}
