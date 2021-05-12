package com.motivation.team3.motivateme.fragment

import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.motivation.team3.motivateme.MainActivity
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.adapter.CustomHomeAdapter
import com.motivation.team3.motivateme.listener.RecyclerItemClickListener
import com.motivation.team3.motivateme.model.Home
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var activityTitles: Array<String>
    private var collapsingToolbar: CollapsingToolbarLayout? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: CustomHomeAdapter? = null
    private var list: MutableList<Home>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.song_layout, container, false)
        recyclerView = view.findViewById<View>(R.id.my_cycler_view) as RecyclerView
        list = ArrayList()
        prepareHome()
        adapter = CustomHomeAdapter(context, list!!)
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = adapter
        activityTitles = resources.getStringArray(R.array.nav_item_activity_titles)

        recyclerView!!.addOnItemTouchListener(
            RecyclerItemClickListener(
                activity,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        collapsingToolbar =
                            view!!.findViewById<View>(R.id.collapsing_toolbar) as CollapsingToolbarLayout
                        if (position == 0) {
                            val toDoListFragment = ToDoListFragment()
                            fragmentTransition(toDoListFragment, position)
                        } else if (position == 1) {
                            val notesFragment = NotesFragment()
                            fragmentTransition(notesFragment, position)
                        } else if (position == 2) {
                            val quotesFragment = QuotesFragment()
                            fragmentTransition(quotesFragment, position)
                        } else if (position == 3) {
                            val songFragment = SongFragment()
                            fragmentTransition(songFragment, position)
                        } else if (position == 4) {
                            val tellBeadsFragment = TellBeadsFragment()
                            fragmentTransition(tellBeadsFragment, position)
                        }
                    }
                })
        )

        return view
    }

    fun fragmentTransition(fragment: Fragment?, position: Int) {
        MainActivity.instance?.supportActionBar!!.title = activityTitles[position + 1]
        MainActivity.navItemIndex = position + 1
        MainActivity.instance?.setupPageDetectableFAB()
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        val replace = fragmentTransaction.replace(R.id.frame, fragment, TAG_HOME)
        replace.commit()
    }

    fun prepareHome() {
        val drawableArray = arrayOf(
            R.drawable.todolist,
            R.drawable.notes,
            R.drawable.quotes,
            R.drawable.songs,
            R.drawable.tellbeads
        )
        val titleHome = arrayOf(
            "To Do List",
            "Notes",
            "Motivational Quotes",
            "Motivational Songs",
            "Tell Beads"
        )
        var h: Home? = null
        var i = 0
        while (i < drawableArray.size && i < titleHome.size) {
            h = Home(titleHome[i], drawableArray[i])
            list!!.add(h)
            i++
        }
    }

    companion object {
        private const val TAG_HOME = "Home"
    }
}