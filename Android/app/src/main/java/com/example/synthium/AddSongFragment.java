package com.example.synthium;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddSongFragment extends Fragment {
    private TextView songName;
    private TextView songArtist;
   private TextView songMin;
   private TextView songSec;
    private TextView songURL;
    private Button addBtn;
    public AddSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_song, container, false);

        view.findViewById(R.id.addbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceClient serviceClient = ServiceClient.getInstance(getContext());
                StringRequest request = new StringRequest(Request.Method.POST, "https://mopsdev.bw.edu/~kcox18/Synthium/WebService/rest.php/song", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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

                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("songTitle", songName.getText().toString());
                        params.put("songArtist", songArtist.getText().toString());
                        params.put("songLength", songMin.getText().toString()+":"+songSec.getText().toString());
                        params.put("songURL", songURL.getText().toString());

                        return params;
                    }
                };
                serviceClient.addRequest(request);
            }
        });


        songName = view.findViewById(R.id.songName);
        songArtist = view.findViewById(R.id.songArtist);
        songMin = view.findViewById(R.id.lengthMinutes);
        songSec = view.findViewById(R.id.lengthSeconds);
        songURL = view.findViewById(R.id.songURL);
        return view;
    }


}
