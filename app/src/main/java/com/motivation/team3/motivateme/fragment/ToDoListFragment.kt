package com.motivation.team3.motivateme.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.activity.UpdateToDoList
import com.motivation.team3.motivateme.adapter.CustomToDoListAdapter
import com.motivation.team3.motivateme.database.TaskDbHelper
import com.motivation.team3.motivateme.listener.RecyclerItemClickListener
import com.motivation.team3.motivateme.service.AlarmReceiver
import java.lang.String


class ToDoListFragment : Fragment() {
    private var db: TaskDbHelper? = null
    var customAdapter: CustomToDoListAdapter? = null
    private var recyclerView: RecyclerView? = null
    var click = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.todolist_layout, container, false)
        super.onCreate(savedInstanceState)
        db = TaskDbHelper(activity)
        recyclerView = view.findViewById<View>(R.id.recycler_view) as RecyclerView
        customAdapter = CustomToDoListAdapter(activity, db!!.allData)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(activity, 1)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = customAdapter
        onResume()
        recyclerView!!.addOnItemTouchListener(
            RecyclerItemClickListener(
                activity,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        val contract = db!!.getData(position)
                        val `in` = Intent(activity, UpdateToDoList::class.java)
                        `in`.putExtra("ID", String.valueOf(contract!!.id))
                        `in`.putExtra("COUNT", String.valueOf(contract!!.count))
                        `in`.putExtra("NAME", contract!!.title.toString())
                        `in`.putExtra("BODY", contract!!.body.toString())
                        `in`.putExtra("TIME", contract!!.time.toString())
                        `in`.putExtra("DATE", contract!!.date.toString())

                        startActivity(`in`)
                    }
                })
        )

        val simpleItemTouchCallBack: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    val fromPos = viewHolder.adapterPosition
                    val toPos = viewHolder.adapterPosition
                    moveItem(fromPos, toPos)
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    deleteItem(viewHolder.position)
                    onResume()
                }
            }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        return view
    }

    private fun moveItem(oldpos: Int, newpos: Int) {
        customAdapter!!.notifyItemMoved(oldpos, newpos)
    }

    private fun deleteItem(position: Int) {
        val contact = db!!.getData(position)
        val id = contact!!.id
        val intent = Intent(activity, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(activity, id, intent, 0)
        val alarmManager = activity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
        db!!.deleteData(id.toString())
    }

    override fun onResume() {
        super.onResume()
        Handler().post {
            customAdapter = CustomToDoListAdapter(activity, db!!.allData)
            recyclerView!!.adapter = customAdapter
        }
    }
}