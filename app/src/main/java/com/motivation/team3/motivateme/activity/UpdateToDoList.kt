package com.motivation.team3.motivateme.activity

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.database.TaskDbHelper
import com.motivation.team3.motivateme.service.AlarmReceiver
import com.readystatesoftware.systembartint.SystemBarTintManager
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.text.SimpleDateFormat
import java.util.*

class UpdateToDoList : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    var db: TaskDbHelper? = null
    private lateinit var id: String
    var title: String? = null
    var body: String? = null
    var stime: String? = null
    var sdate: String? = null
    var alarmManager: AlarmManager? = null
    var pendingIntent: PendingIntent? = null
    private lateinit var calNow: Calendar
    var calSet: Calendar? = null
    var count = 0
    var scount = 0
    var txtbody: EditText? = null
    var txttitle: EditText? = null
    var time: Button? = null
    var date: Button? = null
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newnote_layout)
        val toolbar = findViewById<Toolbar>(R.id.appToolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        val intent = intent
        db = TaskDbHelper(this)
        calNow = Calendar.getInstance()
        calSet = calNow.clone() as Calendar
        id = intent!!.getSerializableExtra("ID") as String
        scount = (intent.getSerializableExtra("COUNT") as Int?)!!
        title = intent.getSerializableExtra("NAME") as String?
        body = intent.getSerializableExtra("BODY") as String?
        stime = intent.getSerializableExtra("TIME") as String?
        sdate = intent.getSerializableExtra("DATE") as String?
        txtbody = findViewById(R.id.new_body) as EditText
        txttitle = findViewById(R.id.new_title) as EditText
        time = findViewById(R.id.new_time) as Button
        date = findViewById(R.id.new_date) as Button
        txttitle!!.setText(title)
        txtbody!!.setText(body)
        time!!.text = stime
        date!!.text = sdate
        val textlength = txttitle!!.text.length
        txttitle!!.setSelection(textlength, textlength)
        time!!.setOnClickListener { openTimePickerDialog() }
        date!!.setOnClickListener { openDatePickerDialog() }
        val tintManager = SystemBarTintManager(this)
        tintManager.isStatusBarTintEnabled = true
        tintManager.setStatusBarTintColor(Color.parseColor("#0288d1"))
        tintManager.setNavigationBarTintEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_task -> {
                val txttitle = findViewById(R.id.new_title) as EditText
                val title = txttitle.text.toString()
                val txtbody = findViewById(R.id.new_body) as EditText
                val body = txtbody.text.toString()
                setAlarm(calSet)
                db!!.updateData(id, scount, title, body, stime, sdate)
                finish()
                return true
            }
        }
        finish()
        return true
    }

    fun openTimePickerDialog() {
        val now = Calendar.getInstance()
        val timepickerdialog = TimePickerDialog.newInstance(
            this@UpdateToDoList,
            now[Calendar.HOUR_OF_DAY],
            now[Calendar.MINUTE], false
        )
        timepickerdialog!!.isThemeDark = false
        timepickerdialog.vibrate(true)
        timepickerdialog.dismissOnPause(true)
        timepickerdialog.enableSeconds(false)
        timepickerdialog.show(supportFragmentManager, "Timepickerdialog") //show time picker dialog
    }

    fun openDatePickerDialog() {
        val now = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog.newInstance(
            this@UpdateToDoList,
            now[Calendar.YEAR],
            now[Calendar.MONTH],
            now[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog!!.isThemeDark = false
        datePickerDialog.vibrate(true)
        datePickerDialog.dismissOnPause(true)
        datePickerDialog.show(supportFragmentManager, "Datepickerdialog")
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        sdate = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
        date!!.text = sdate
        calSet!![Calendar.DAY_OF_MONTH] = dayOfMonth
        calSet!![Calendar.MONTH] = monthOfYear
        calSet!![Calendar.YEAR] = year
        if (calSet!! <= calNow) {
            calSet!!.add(Calendar.DATE, 1)
        }
    }

    private fun setAlarm(targetcal: Calendar?) {
        val intent = Intent(baseContext, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(baseContext, scount, intent, 0)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager!![AlarmManager.RTC_WAKEUP, targetcal!!.timeInMillis] = pendingIntent
    }


    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        val dt = Date()
        val sdf = SimpleDateFormat("hh:mm a")
        stime = sdf.format(dt)
        time!!.text = stime
        Log.i("Hour :", hourOfDay.toString())
        calSet!![Calendar.HOUR_OF_DAY] = hourOfDay
        calSet!![Calendar.MINUTE] = minute
        calSet!![Calendar.SECOND] = 0
        calSet!![Calendar.MILLISECOND] = 0
        if (calSet!!.compareTo(calNow) <= 0) {
            calSet!!.add(Calendar.DATE, 1)
        }
    }
}