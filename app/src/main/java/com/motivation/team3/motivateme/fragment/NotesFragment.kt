package com.motivation.team3.motivateme.fragment

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.activity.UpDateNote
import com.motivation.team3.motivateme.adapter.CustomNoteAdapter
import com.motivation.team3.motivateme.database.TaskDbHelper
import com.motivation.team3.motivateme.listener.RecyclerItemClickListener
import com.motivation.team3.motivateme.model.Note
import java.lang.String


class NotesFragment : Fragment() {
    private var db: TaskDbHelper? = null
    private lateinit var customNoteAdapter: CustomNoteAdapter
    private var recyclerView: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.notes_layout, container, false)
        super.onCreate(savedInstanceState)
        db = TaskDbHelper(activity)
        recyclerView = view.findViewById<View>(R.id.recycler_view_node) as RecyclerView
        customNoteAdapter = CustomNoteAdapter(requireContext(), db!!.allNoteData)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10), true))
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = customNoteAdapter
        onResume()

        /*recyclerView!!.addOnItemTouchListener(
            RecyclerItemClickListener(
                requireContext(),
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        val note: Note? = db!!.getNoteData(position)
                        val `in` = Intent(activity, UpDateNote::class.java)
                        `in`.putExtra("ID", String.valueOf(note?.id))
                        `in`.putExtra("TITLE", note?.title)
                        `in`.putExtra("BODY", note?.body)
                        `in`.putExtra("TIME", note?.time)
                        `in`.putExtra("DATE", note?.date)
                        startActivity(`in`)
                    }
                })
        )*/

        return view
    }

    private fun moveItem(oldpos: Int, newpos: Int) {
        customNoteAdapter!!.notifyItemMoved(oldpos, newpos)
    }

    private fun deleteItem(position: Int) {
        val note = db!!.getNoteData(position)
        val id = note!!.id
        db!!.deleteNoteData(id)
    }

    override fun onResume() {
        super.onResume()
        Handler().post {
            customNoteAdapter = CustomNoteAdapter(requireContext(), db!!.allNoteData)
            recyclerView!!.adapter = customNoteAdapter
        }
    }

    inner class GridSpacingItemDecoration(
        private val spanCount: Int,
        private val spacing: Int,
        private val includeEdge: Boolean
    ) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount
                if (position < spanCount) {
                    outRect.top = spacing
                }
                outRect.bottom = spacing
            } else {
                outRect.left = column * spacing / spanCount
                outRect.right = spacing - (column + 1) * spacing / spanCount
                if (position >= spanCount) {
                    outRect.top = spacing
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                r.displayMetrics
            )
        )
    }
}