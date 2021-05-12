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

public class CustomToDoListAdapter extends RecyclerView.Adapter<CustomToDoListAdapter.MyViewHolder> {
    Context context;
    Cursor cursor;
    String id,title,time,date,subtitle;
    int count;
    public CustomToDoListAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
        this.cursor.moveToFirst();
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, final int position)
    {
        cursor.moveToPosition(position);

        id = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)));
        count=Integer.parseInt(cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
        title = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)));
        time = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)));
        date = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5)));

        if(title.toString().length()<30)
        {
            subtitle=title.substring(0,title.toString().length());
            holder.title.setText(subtitle);
        }
        else
        {
            subtitle=title.substring(0,30);
            subtitle=subtitle+" ...";
            holder.title.setText(subtitle);
        }
        holder.date.setText(date+ "  " + time);

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
        public TextView title;
        public TextView date;
        public CardView cardView;
        public MyViewHolder(View view)
        {
            super(view);
            title = (TextView) view.findViewById(R.id.task_tt);
            cardView=(CardView)view.findViewById(R.id.card_view);
            date=(TextView)view.findViewById(R.id.task_date);
        }
    }
}


