package com.motivation.team3.motivateme.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.model.Quote

class CustomQuoteAdapter(var context: Context, var list: List<Quote>) :
    RecyclerView.Adapter<CustomQuoteAdapter.MyViewHolder>() {
    var quote: String? = null
    var autor: String? = null
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
        var quote: TextView
        var author: TextView
        var cardView: CardView

        init {
            quote = view.findViewById<View>(R.id.quote_task_quote) as TextView
            author = view.findViewById<View>(R.id.quote_task_author) as TextView
            cardView = view.findViewById<View>(R.id.card_view) as CardView
        }
    }
}