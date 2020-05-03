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
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddSong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);
        Button addSongButton = findViewById(R.id.addbtn);

        addSongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceClient serviceClient = ServiceClient.getInstance(getApplicationContext());
                StringRequest request = new StringRequest(Request.Method.POST, "https://mopsdev.bw.edu/~kcox18/Synthium/WebService/rest.php/song", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                    }
                }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        // Find Strings for request
                        EditText songName = findViewById(R.id.songName);
                        EditText songArtist = findViewById(R.id.songArtist);
                        EditText songMin = findViewById(R.id.lengthMinutes);
                        EditText songSec = findViewById(R.id.lengthSeconds);
                        EditText songURL = findViewById(R.id.songURL);

                        int songMinutes = Integer.parseInt(songMin.getText().toString()) * 60;
                        int songSeconds = Integer.parseInt(songSec.getText().toString());
                        int totalSongLength = songMinutes + songSeconds;

                        Map<String, String>  params = new HashMap<>();
                        params.put("songTitle", songName.getText().toString());
                        params.put("songArtist", songArtist.getText().toString());
                        params.put("songLength", String.valueOf(totalSongLength));
                        params.put("songURL", songURL.getText().toString());

                        return params;
                    }
                };
                serviceClient.addRequest(request);
            }
        });

    }
}
