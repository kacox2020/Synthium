package com.example.synthium;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyMusicRecyclerViewAdapter extends RecyclerView.Adapter<MyMusicRecyclerViewAdapter.ViewHolder> {

    private final List<Song> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyMusicRecyclerViewAdapter(List<Song> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mValues.size()) ? R.layout.add_song_button : R.layout.fragment_music;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == R.layout.fragment_music) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_music, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.add_song_button, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position == mValues.size()) {
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("IT Worked");
                }
            });
        } else {
            holder.mItem = mValues.get(position);
            String id = String.valueOf(mValues.get(position).songID);
            holder.mIdView.setText(id);
            holder.mContentView.setText(mValues.get(position).songTitle);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(holder.mItem);

                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (mValues.size() + 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Button button;
        public Song mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            button = (Button) view.findViewById(R.id.addSongButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


}
