package com.motivation.team3.motivateme.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
        holder.itemView.setOnClickListener {
            when {
                position === 0 -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=xo1VInw-SKc")
                    )
                    holder.itemView.context.startActivity(intent)
                }
                position === 1 -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=HHP5MKgK0o8")
                    )
                    holder.itemView.context.startActivity(intent)
                }
                position === 2 -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=j5-yKhDd64s")
                    )
                    holder.itemView.context.startActivity(intent)
                }
                position === 3 -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=CevxZvSJLk8")
                    )
                    holder.itemView.context.startActivity(intent)
                }
                position === 4 -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=VBmMU_iwe6U")
                    )
                    holder.itemView.context.startActivity(intent)
                }
                position === 5 -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=NG2zyeVRcbs")
                    )
                    holder.itemView.context.startActivity(intent)
                }
                position === 6 -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=GKSRyLdjsPA")
                    )
                    holder.itemView.context.startActivity(intent)
                }
                position === 7 -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=hT_nvWreIhg&list=PLhGO2bt0EkwvRUioaJMLxrMNhU44lRWg8")
                    )
                    holder.itemView.context.startActivity(intent)
                }
                position === 8 -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=Qt2mbGP6vFI&list=PLhGO2bt0EkwvRUioaJMLxrMNhU44lRWg8&index=8")
                    )
                    holder.itemView.context.startActivity(intent)
                }
                position === 9 -> {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=O1-4u9W-bns&list=PLhGO2bt0EkwvRUioaJMLxrMNhU44lRWg8&index=14")
                    )
                    holder.itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById<View>(R.id.songtitle) as TextView
        var artist: TextView = view.findViewById<View>(R.id.artist) as TextView
        var image: ImageView = view.findViewById<View>(R.id.img) as ImageView

    }
}