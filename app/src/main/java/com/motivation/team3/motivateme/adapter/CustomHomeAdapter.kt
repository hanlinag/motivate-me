package com.motivation.team3.motivateme.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.motivation.team3.motivateme.MainActivity
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.fragment.*
import com.motivation.team3.motivateme.model.Home

class CustomHomeAdapter(var context: Context, var list: List<Home>) :
    RecyclerView.Adapter<CustomHomeAdapter.MyViewHolder>() {

    private lateinit var activityTitles: Array<String>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         activityTitles = context.resources.getStringArray(R.array.nav_item_activity_titles)
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.home_card_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var song = Home()
        song = list[position]
        holder.image.setImageResource(song.image)
        holder.text.text = song.titleHome
        holder.itemView.setOnClickListener {
            when (position) {
                0 -> {
                    val toDoListFragment = ToDoListFragment()
                    fragmentTransition(toDoListFragment, position)
                }
                1 -> {
                    val notesFragment = NotesFragment()
                    fragmentTransition(notesFragment, position)
                }
                2 -> {
                    val quotesFragment = QuotesFragment()
                    fragmentTransition(quotesFragment, position)
                }
                3 -> {
                    val songFragment = SongFragment()
                    fragmentTransition(songFragment, position)
                }
                4 -> {
                    val tellBeadsFragment = TellBeadsFragment()
                    fragmentTransition(tellBeadsFragment, position)
                }
            }
        }
    }

    fun fragmentTransition(fragment: Fragment?, position: Int) {
        MainActivity.instance?.supportActionBar!!.title = activityTitles[position + 1]
        MainActivity.navItemIndex = position + 1
        MainActivity.instance?.setupPageDetectableFAB()
        val fragmentTransaction = (context as AppCompatActivity).supportFragmentManager?.beginTransaction()
        fragmentTransaction?.setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        val replace = fragmentTransaction?.replace(R.id.frame, fragment!!, HomeFragment.TAG_HOME)
        replace?.commit()
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