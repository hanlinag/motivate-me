package com.motivation.team3.motivateme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.motivation.team3.motivateme.R;
import com.motivation.team3.motivateme.model.Song;
import java.util.List;

public class CustomSongAdapter extends RecyclerView.Adapter<CustomSongAdapter.MyViewHolder>
{
    Context context;
    String title,name;
    List<Song> list;

    public CustomSongAdapter(Context context, List<Song>  list)
    {
        this.context = context;
        this.list=list;
    }

    public CustomSongAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_card_layout, parent, false);
        return new CustomSongAdapter.MyViewHolder(itemView);
    }

    public void onBindViewHolder(CustomSongAdapter.MyViewHolder holder,final int position)
    {
        Song song = new Song();
        song = list.get(position);
        Log.i("holder : ",String.valueOf(holder));
        Log.i("Position : ",String.valueOf(position));
        holder.name.setText(song.getName());
        holder.artist.setText(song.getArtist());
        holder.image.setImageResource(song.getImage());
    }

    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name;
        public TextView artist;
        public ImageView image;
        public MyViewHolder(View view)
        {
            super(view);
            name = (TextView) view.findViewById(R.id.songtitle);
            artist=(TextView)view.findViewById(R.id.artist) ;
            image=(ImageView) view.findViewById(R.id.img);
        }
    }
}
