package com.motivation.team3.motivateme.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.motivation.team3.motivateme.R;
import com.motivation.team3.motivateme.model.Quote;
import java.util.List;


public class CustomQuoteAdapter extends RecyclerView.Adapter<CustomQuoteAdapter.MyViewHolder>
{
    Context context;
    String quote,autor;
    List<Quote> list;

    public CustomQuoteAdapter(Context context, List<Quote>  list)
    {
        this.context = context;
        this.list=list;
    }

    public CustomQuoteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_card_layout, parent, false);
        return new CustomQuoteAdapter.MyViewHolder(itemView);
    }

    public void onBindViewHolder(CustomQuoteAdapter.MyViewHolder holder, final int position)
    {
        Quote quote=list.get(position);
        holder.author.setText(quote.getAutor());
        holder.quote.setText(quote.getQuote());
    }

    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView quote;
        public TextView author;
        public CardView cardView;
        public MyViewHolder(View view)
        {
            super(view);
            quote = (TextView) view.findViewById(R.id.quote_task_quote);
            author=(TextView)view.findViewById(R.id.quote_task_author) ;
            cardView=(CardView)view.findViewById(R.id.card_view);
        }
    }
}
