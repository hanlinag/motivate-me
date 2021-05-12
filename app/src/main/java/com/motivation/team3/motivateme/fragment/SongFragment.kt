package com.motivation.team3.motivateme.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.adapter.CustomSongAdapter
import com.motivation.team3.motivateme.listener.RecyclerItemClickListener
import com.motivation.team3.motivateme.model.Song
import java.util.*

class SongFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapter: CustomSongAdapter? = null
    private var list: MutableList<Song>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.song_layout, container, false)
        recyclerView = view.findViewById<View>(R.id.my_cycler_view) as RecyclerView
        list = ArrayList()
        prepareSong()
        adapter = CustomSongAdapter(requireContext(), list!!)
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = adapter
        /*recyclerView!!.addOnItemTouchListener(
            RecyclerItemClickListener(
                requireContext(),
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        when {
                            position === 0 -> {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=xo1VInw-SKc")
                                )
                                startActivity(intent)
                            }
                            position === 1 -> {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=HHP5MKgK0o8")
                                )
                                startActivity(intent)
                            }
                            position === 2 -> {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=j5-yKhDd64s")
                                )
                                startActivity(intent)
                            }
                            position === 3 -> {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=CevxZvSJLk8")
                                )
                                startActivity(intent)
                            }
                            position === 4 -> {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=VBmMU_iwe6U")
                                )
                                startActivity(intent)
                            }
                            position === 5 -> {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=NG2zyeVRcbs")
                                )
                                startActivity(intent)
                            }
                            position === 6 -> {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=GKSRyLdjsPA")
                                )
                                startActivity(intent)
                            }
                            position === 7 -> {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=hT_nvWreIhg&list=PLhGO2bt0EkwvRUioaJMLxrMNhU44lRWg8")
                                )
                                startActivity(intent)
                            }
                            position === 8 -> {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=Qt2mbGP6vFI&list=PLhGO2bt0EkwvRUioaJMLxrMNhU44lRWg8&index=8")
                                )
                                startActivity(intent)
                            }
                            position === 9 -> {
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.youtube.com/watch?v=O1-4u9W-bns&list=PLhGO2bt0EkwvRUioaJMLxrMNhU44lRWg8&index=14")
                                )
                                startActivity(intent)
                            }
                        }
                    }
                }))*/

        return view
    }

    fun prepareSong() {
        val nameArray = arrayOf(
            "Fight Song",
            "Kill EM with kindness",
            "Not Afraid",
            "Roar",
            "Run the World",
            "The Climb",
            "The Greatest",
            "Counting Stars",
            "Another  days in paradise",
            "I won't give up"
        )
        val artist = arrayOf(
            "Rachel Platten",
            "Selena Gomez",
            "Eminem",
            "Katy Perry",
            "Beyonce",
            "Miley Cyrus",
            "Sia",
            "OneRepublic",
            "Phil Collins",
            "Jason Marz"
        )
        val drawableArray = arrayOf(
            R.drawable.songblah,
            R.drawable.songblah1,
            R.drawable.songblah2,
            R.drawable.songblah3,
            R.drawable.songblah4,
            R.drawable.songblah,
            R.drawable.songblah3,
            R.drawable.songblah1,
            R.drawable.songblah2,
            R.drawable.songblah4
        )
        var song: Song
        for (i in nameArray.indices) {
            song = Song()
            song = Song(
                nameArray[i], artist[i],
                drawableArray[i]
            )
            list!!.add(song)
        }
    }
}