package com.motivation.team3.motivateme.activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.database.TaskDbHelper
import com.readystatesoftware.systembartint.SystemBarTintManager
import java.text.SimpleDateFormat
import java.util.*

class UpDateNote : AppCompatActivity() {
    var title: EditText? = null
    var body: EditText? = null
    var id: String? = null
    var stitle: String? = null
    var sbody: String? = null
    var time: String? = null
    var date: String? = null
    var db: TaskDbHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.node_add_layout)
        val toolbar = findViewById(R.id.note_appToolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        title = findViewById(R.id.note_title) as EditText
        body = findViewById(R.id.note_body) as EditText
        db = TaskDbHelper(this)
        val intent = intent
        id = intent.getSerializableExtra("ID") as String?
        stitle = intent.getSerializableExtra("TITLE") as String?
        sbody = intent.getSerializableExtra("BODY") as String?
        time = intent.getSerializableExtra("TIME") as String?
        date = intent.getSerializableExtra("DATE") as String?
        title!!.setText(stitle)
        val textlength = title!!.text.length
        body!!.setText(sbody)
        val bodylength = body!!.text.length
        if (textlength != 0) {
            title!!.setSelection(textlength, textlength)
        } else if (textlength == 0 && bodylength != 0) {
            body!!.setSelection(bodylength, bodylength)
        }
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
                val stext = title!!.text.toString()
                val sbody = body!!.text.toString()
                if (title!!.text.toString().length != 0 || body!!.text.toString().length != 0) {
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
                        now[Calendar.DAY_OF_MONTH].toString() + "/" + (now[Calendar.MONTH] + 1).toString() + "/" + now[Calendar.YEAR].toString()
                    db!!.updateNoteData(
                        id!!.toInt(),
                        title!!.text.toString(),
                        body!!.text.toString(),
                        timeText,
                        dateText
                    )
                } else if (stext.length == 0 || sbody.length == 0) {
                    db!!.deleteNoteData(id!!.toInt())
                }
                finish()
                return true
            }
        }
        finish()
        return true
    }
}