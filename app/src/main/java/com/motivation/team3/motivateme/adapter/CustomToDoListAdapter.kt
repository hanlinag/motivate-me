package com.motivation.team3.motivateme.adapter

import android.content.Context
import android.database.Cursor
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.motivation.team3.motivateme.R

class CustomToDoListAdapter(var context: Context, var cursor: Cursor) :
    RecyclerView.Adapter<CustomToDoListAdapter.MyViewHolder>() {
    var id: String? = null
    var title: String? = null
    var time: String? = null
    var date: String? = null
    var subtitle: String? = null
    var count = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        cursor.moveToPosition(position)
        id = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(0)))
        count = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))).toInt()
        title = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)))
        time = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)))
        date = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5)))
        if (title.toString().length < 30) {
            subtitle = title?.substring(0, title.toString().length)
            holder.title.text = subtitle
        } else {
            subtitle = title?.substring(0, 30)
            subtitle = "$subtitle ..."
            holder.title.text = subtitle
        }
        holder.date.text = "$date  $time"
        if (position % 4 == 0) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#03a9f4"))
        } else if (position % 4 == 1) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffab40"))
        } else if (position % 4 == 2) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#e53935"))
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffe57f"))
        }
    }

    override fun getItemCount(): Int {
        return cursor.count
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var date: TextView
        var cardView: CardView

        init {
            title = view.findViewById<View>(R.id.task_tt) as TextView
            cardView = view.findViewById<View>(R.id.card_view) as CardView
            date = view.findViewById<View>(R.id.task_date) as TextView
        }
    }

    init {
        cursor.moveToFirst()
    }
}