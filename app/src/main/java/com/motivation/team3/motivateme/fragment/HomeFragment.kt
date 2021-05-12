package com.motivation.team3.motivateme.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.motivation.team3.motivateme.MainActivity
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.adapter.CustomHomeAdapter
import com.motivation.team3.motivateme.listener.RecyclerItemClickListener
import com.motivation.team3.motivateme.model.Home
import java.util.*


class HomeFragment : Fragment() {
    private lateinit var activityTitles: Array<String>
    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomHomeAdapter
    private lateinit var list: MutableList<Home>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.song_layout, container, false)
        recyclerView = view.findViewById<View>(R.id.my_cycler_view) as RecyclerView
        list = ArrayList()
        prepareHome()
        adapter = CustomHomeAdapter(requireContext(), list!!)
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = adapter
        activityTitles = resources.getStringArray(R.array.nav_item_activity_titles)

        recyclerView.setOnClickListener {

        }
        /*recyclerView!!.addOnItemTouchListener(
            RecyclerItemClickListener(
                requireContext(),
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        collapsingToolbar = view?.findViewById(R.id.collapsing_toolbar) as CollapsingToolbarLayout
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
                })
        )*/

        return view
    }

    fun fragmentTransition(fragment: Fragment?, position: Int) {
        MainActivity.instance?.supportActionBar!!.title = activityTitles[position + 1]
        MainActivity.navItemIndex = position + 1
        MainActivity.instance?.setupPageDetectableFAB()
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        val replace = fragmentTransaction?.replace(R.id.frame, fragment!!, TAG_HOME)
        replace?.commit()
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
        const val TAG_HOME = "Home"
    }
}