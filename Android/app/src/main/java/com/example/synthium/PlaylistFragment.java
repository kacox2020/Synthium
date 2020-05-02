package com.example.synthium;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PlaylistFragment extends Fragment implements OnListFragmentInteractionListener<Object> {

    @Override
    public void onListFragmentInteraction(Object playlistObject) {
        Playlist playlist = (Playlist) playlistObject;
        Bundle bundle = new Bundle();

        PlayerFragment playerFragment = new PlayerFragment();
        playerFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.playerContainer, playerFragment);

        fragmentTransaction.disallowAddToBackStack();
        fragmentTransaction.commit();
    }

    private int mColumnCount = 1;

    private OnListFragmentInteractionListener mListener = this;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlaylistFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_list, container, false);


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            final RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            final PlaylistModel model = new PlaylistModel();

            // Call the Database
            ServiceClient serviceClient = ServiceClient.getInstance(getContext());
            StringRequest request = new StringRequest(Request.Method.GET, "https://mopsdev.bw.edu/~kcox18/Synthium/WebService/rest.php/playlist", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray responseObject = new JSONObject(response).getJSONArray("responseObject");
                        for (int i = 0; i < responseObject.length(); i++) {
                            JSONObject playlistObject = responseObject.getJSONObject(i);
                            model.setPlaylist(playlistObject);
                        }
                        MyPlaylistRecyclerViewAdapter adapter = new MyPlaylistRecyclerViewAdapter(model.getPlaylists(), mListener);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
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

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
