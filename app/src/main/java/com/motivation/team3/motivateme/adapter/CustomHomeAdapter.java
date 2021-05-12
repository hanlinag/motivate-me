package com.motivation.team3.motivateme.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.motivation.team3.motivateme.R;
import com.motivation.team3.motivateme.model.Home;
import java.util.List;

public class CustomHomeAdapter extends RecyclerView.Adapter<CustomHomeAdapter.MyViewHolder>
{
    Context context;
    List<Home> list;

    public CustomHomeAdapter(Context context, List<Home>  list)
    {
        this.context = context;
        this.list=list;
    }

    public CustomHomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card_layout, parent, false);
        return new CustomHomeAdapter.MyViewHolder(itemView);
    }

    public void onBindViewHolder(CustomHomeAdapter.MyViewHolder holder,final int position)
    {
        Home song = new Home();
        song = list.get(position);
        holder.image.setImageResource(song.getImage());
        holder.text.setText(song.getTitleHome());
    }

    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView image;
        public TextView text;
        public MyViewHolder(View view)
        {
            super(view);
            text=(TextView)view.findViewById(R.id.titletextView) ;
            image=(ImageView) view.findViewById(R.id.home_img);
        }
    }

}
