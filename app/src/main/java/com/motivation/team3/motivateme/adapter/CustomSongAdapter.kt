package com.motivation.team3.motivateme.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.model.Song

class CustomSongAdapter(var context: Context, var list: List<Song>) :
    RecyclerView.Adapter<CustomSongAdapter.MyViewHolder>() {
    var title: String? = null
    var name: String? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.song_card_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var song = Song()
        song = list[position]
        Log.i("holder : ", holder.toString())
        Log.i("Position : ", position.toString())
        holder.name.text = song.name
        holder.artist.text = song.artist
        holder.image.setImageResource(song.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView
        var artist: TextView
        var image: ImageView

        init {
            name = view.findViewById<View>(R.id.songtitle) as TextView
            artist = view.findViewById<View>(R.id.artist) as TextView
            image = view.findViewById<View>(R.id.img) as ImageView
        }
    }
}