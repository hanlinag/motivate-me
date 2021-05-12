package com.motivation.team3.motivateme.listener

import android.content.Intent
import android.support.design.widget.Snackbar
import android.view.View
import com.motivation.team3.motivateme.MainActivity
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.activity.AddNewToDoList
import com.motivation.team3.motivateme.activity.AddNote

class FABOnClickListener : View.OnClickListener {
    override fun onClick(view: View) {
        when (view.id) {
            R.id.floating_todolist -> floatingToDoListOnClick(view)
            R.id.floating_menu_home -> floatingHomeOnClick(view)
            R.id.floating_addtodolist -> floatingToDoListOnClick(view)
            R.id.floating_addnote -> floatingNote(view)
            R.id.floating_note -> floatingNote(view)
        }
    }

    private fun floatingHomeOnClick(view: View) {
        Snackbar.make(view, "FH", Snackbar.LENGTH_INDEFINITE).show()
    }

    private fun floatingToDoListOnClick(view: View) {
        val intent =
            Intent(MainActivity.instance?.applicationContext, AddNewToDoList::class.java)
        MainActivity.instance?.startActivity(intent)
    }

    private fun floatingNote(view: View) {
        val intent = Intent(MainActivity.instance?.applicationContext, AddNote::class.java)
        MainActivity.instance?.startActivity(intent)
    }
}