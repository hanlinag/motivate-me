package com.motivation.team3.motivateme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.model.Quote

class CustomQuoteAdapter(var context: Context, var list: List<Quote>) :
    RecyclerView.Adapter<CustomQuoteAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.quote_card_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val quote = list[position]
        holder.author.text = quote.autor
        holder.quote.text = quote.quote
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var quote: TextView = view.findViewById<View>(R.id.quote_task_quote) as TextView
        var author: TextView = view.findViewById<View>(R.id.quote_task_author) as TextView
//        var cardView: CardView = view.findViewById<View>(R.id.card_view) as CardView

    }
}