package com.example.synthium;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class MusicFragment extends Fragment implements OnListFragmentInteractionListener{

    // TODO: Customize parameters
    private int mColumnCount = 1;

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MusicFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Call the Database
        ServiceClient serviceClient = ServiceClient.getInstance(getContext());
        StringRequest request = new StringRequest(Request.Method.GET, "https://mopsdev.bw.edu/~kcox18/Synthium/WebService/rest.php/song", new Response.Listener<String>() {
            // onClient Error
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject musicListObject = new JSONObject(response);

                } catch (JSONException je) {
                    je.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            // onServer Error
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getContext(), "An Error has occurred.", Toast.LENGTH_SHORT).show();
            }
        });
        serviceClient.addRequest(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            SongModel model = new SongModel();
            recyclerView.setAdapter(new MyMusicRecyclerViewAdapter(model.getMusic(), mListener));
        }

        return view;
    }


//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            mListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListFragmentInteraction(Song item) {
        Toast.makeText(getContext(), "click worked", Toast.LENGTH_SHORT).show();

    }
}
