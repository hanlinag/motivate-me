package com.motivation.team3.motivateme.adapter

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.activity.UpDateNote
import com.motivation.team3.motivateme.database.TaskDbHelper
import com.motivation.team3.motivateme.model.Note

class CustomNoteAdapter(var context: Context, var cursor: Cursor) :
    RecyclerView.Adapter<CustomNoteAdapter.MyViewHolder>() {
    var title: String? = null
    private lateinit var time: String
    var date: String? = null
    var subtitle: String? = null
    private lateinit var db: TaskDbHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        db = TaskDbHelper(parent.context)
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        cursor.moveToPosition(position)
        title = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)))
        time = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3)))
        date = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)))
        if (title.toString().length < 10) {
            subtitle = title?.substring(0, title.toString().length)
            holder.ttitle.text = subtitle
        } else {
            subtitle = title?.substring(0, 10)
            subtitle = "$subtitle ..."
            holder.ttitle.text = subtitle
        }
        holder.time.text = "$date  $time"
        when {
            position % 4 == 0 -> {
                holder.cardView.setCardBackgroundColor(Color.parseColor("#03a9f4"))
            }
            position % 4 == 1 -> {
                holder.cardView.setCardBackgroundColor(Color.parseColor("#ffab40"))
            }
            position % 4 == 2 -> {
                holder.cardView.setCardBackgroundColor(Color.parseColor("#e53935"))
            }
            else -> {
                holder.cardView.setCardBackgroundColor(Color.parseColor("#ffe57f"))
            }
        }

        holder.itemView.setOnClickListener {
            val note: Note? = db!!.getNoteData(position)
            val `in` = Intent(holder.itemView.context, UpDateNote::class.java)
            `in`.putExtra("ID", note?.id.toString())
            `in`.putExtra("TITLE", note?.title)
            `in`.putExtra("BODY", note?.body)
            `in`.putExtra("TIME", note?.time)
            `in`.putExtra("DATE", note?.date)
            holder.itemView.context.startActivity(`in`)
        }
    }

    override fun getItemCount(): Int {
        return cursor.count
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ttitle: TextView
        var time: TextView
        var cardView: CardView

        init {
            ttitle = view.findViewById<View>(R.id.task_tt) as TextView
            time = view.findViewById<View>(R.id.task_date) as TextView
            cardView = view.findViewById<View>(R.id.card_view) as CardView
        }
    }

    init {
        cursor.moveToFirst()
    }
}