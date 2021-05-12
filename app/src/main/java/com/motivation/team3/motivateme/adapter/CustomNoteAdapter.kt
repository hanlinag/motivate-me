package com.motivation.team3.motivateme.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.motivation.team3.motivateme.R;

public class CustomNoteAdapter extends RecyclerView.Adapter<CustomNoteAdapter.MyViewHolder> {
    Context context;
    Cursor cursor;
    String title,time,date;
    String subtitle;

    public CustomNoteAdapter(Context context, Cursor cursor)
    {
        this.context = context;
        this.cursor = cursor;
        this.cursor.moveToFirst();
    }

    public CustomNoteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new CustomNoteAdapter.MyViewHolder(itemView);
    }

    public void onBindViewHolder(CustomNoteAdapter.MyViewHolder holder, final int position)
    {
        cursor.moveToPosition(position);

        title = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)));
        time =  cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3)));
        date =  cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)));

        if(title.toString().length()<10)
        {
            subtitle=title.substring(0,title.toString().length());
            holder.ttitle.setText(subtitle);
        }
        else
        {
            subtitle=title.substring(0,10);
            subtitle=subtitle+" ...";
            holder.ttitle.setText(subtitle);
        }
        holder.time.setText(date+"  "+time);
        if((position % 4 == 0))
        {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#03a9f4"));
        }
        else if(position%4==1)
        {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffab40"));
        }
        else if(position%4==2)
        {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#e53935"));
        }
        else
        {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffe57f"));
        }
    }

    public int getItemCount() {
        return cursor.getCount();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView ttitle;
        public TextView time;
        public CardView cardView;
        public MyViewHolder(View view)
        {
            super(view);
            ttitle = (TextView) view.findViewById(R.id.task_tt);
            time=(TextView)view.findViewById(R.id.task_date) ;
            cardView=(CardView)view.findViewById(R.id.card_view);
        }
    }
}