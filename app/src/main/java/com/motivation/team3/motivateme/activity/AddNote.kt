package com.motivation.team3.motivateme.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.database.TaskDbHelper
import com.readystatesoftware.systembartint.SystemBarTintManager
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {
    var text: EditText? = null
    var body: EditText? = null
    var stext: String? = null
    var sbody: String? = null
    var db: TaskDbHelper? = null
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.node_add_layout)
        val toolbar = findViewById(R.id.note_appToolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        text = findViewById(R.id.note_title) as EditText
        body = findViewById(R.id.note_body) as EditText
        db = TaskDbHelper(this)
        val tintManager = SystemBarTintManager(this)
        tintManager.isStatusBarTintEnabled = true
        tintManager.setStatusBarTintColor(Color.parseColor("#0288d1"))
        tintManager.setNavigationBarTintEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_task -> {
                stext = text!!.text.toString()
                sbody = body!!.text.toString()
                if (stext!!.length != 0 || sbody!!.length != 0) {
                    val now = Calendar.getInstance()
                    val dt = Date(
                        0,
                        0,
                        0,
                        now[Calendar.HOUR_OF_DAY],
                        now[Calendar.MINUTE],
                        now[Calendar.SECOND]
                    )
                    val sdf = SimpleDateFormat("hh:mm a")
                    val timeText = sdf.format(dt)
                    val dateText =
                        now[Calendar.DAY_OF_MONTH].toString() + "/" + (now[Calendar.MONTH] + 1) + "/" + now[Calendar.YEAR]
                    db!!.noteInsertData(stext, sbody, timeText, dateText)
                }
                finish()
                return true
            }
        }
        finish()
        return true
    }
}