package com.motivation.team3.motivateme.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.model.Home

class CustomHomeAdapter(var context: Context, var list: List<Home>) :
    RecyclerView.Adapter<CustomHomeAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.home_card_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var song = Home()
        song = list[position]
        holder.image.setImageResource(song.image)
        holder.text.text = song.titleHome
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var text: TextView

        init {
            text = view.findViewById<View>(R.id.titletextView) as TextView
            image = view.findViewById<View>(R.id.home_img) as ImageView
        }
    }
}