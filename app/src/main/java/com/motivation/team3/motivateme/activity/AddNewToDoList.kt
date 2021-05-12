package com.motivation.team3.motivateme.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.motivation.team3.motivateme.R
import com.motivation.team3.motivateme.database.TaskDbHelper
import com.motivation.team3.motivateme.service.AlarmReceiver
import com.readystatesoftware.systembartint.SystemBarTintManager
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.text.SimpleDateFormat
import java.util.*

class AddNewToDoList : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    var db: TaskDbHelper? = null
    var title: EditText? = null
    var body: EditText? = null
    var time: Button? = null
    var date: Button? = null
    var stitle: String? = null
    var sbody: String? = null
    var timeText: String? = null
    var dateText: String? = null
    var alarmManager: AlarmManager? = null
    var pendingIntent: PendingIntent? = null
    private lateinit var calNow: Calendar
    var calSet: Calendar? = null
    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newnote_layout)
        db = TaskDbHelper(this)
        val toolbar = findViewById(R.id.appToolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        calNow = Calendar.getInstance()
        calSet = calNow.clone() as Calendar
        val now = Calendar.getInstance()
        title = findViewById(R.id.new_title) as EditText
        body = findViewById(R.id.new_body) as EditText
        time = findViewById(R.id.new_time) as Button
        date = findViewById(R.id.new_date) as Button
        if (now[Calendar.HOUR_OF_DAY] < 22) {
            calSet!![Calendar.HOUR_OF_DAY] = now[Calendar.HOUR_OF_DAY]
            calSet!![Calendar.MINUTE] = now[Calendar.MINUTE]
            val dt = Date(0, 0, 0, now[Calendar.HOUR_OF_DAY], now[Calendar.MINUTE], 0)
            val sdf = SimpleDateFormat("hh:mm a")
            timeText = sdf.format(dt)
            time!!.text = timeText
            calSet!![Calendar.DAY_OF_MONTH] = now[Calendar.DAY_OF_MONTH]
            calSet!![Calendar.MONTH] = now[Calendar.MONTH]
            calSet!![Calendar.YEAR] = now[Calendar.YEAR]
            dateText =
                now[Calendar.DAY_OF_MONTH].toString() + "/" + (now[Calendar.MONTH] + 1) + "/" + now[Calendar.YEAR]
            date!!.text = dateText
        } else {
            calSet!![Calendar.HOUR_OF_DAY] = 8
            calSet!![Calendar.MINUTE] = 0
            val dt = Date(0, 0, 0, 8, 0, 0)
            val sdf = SimpleDateFormat("hh:mm a")
            timeText = sdf.format(dt)
            time!!.text = timeText
            calSet!![Calendar.DAY_OF_MONTH] = now[Calendar.DAY_OF_MONTH] + 1
            calSet!![Calendar.MONTH] = now[Calendar.MONTH]
            calSet!![Calendar.YEAR] = now[Calendar.YEAR]
            dateText =
                (now[Calendar.DAY_OF_MONTH] + 1).toString() + "/" + (now[Calendar.MONTH] + 1) + "/" + now[Calendar.YEAR]
            date!!.text = dateText
        }
        time!!.setOnClickListener { openTimePickerDialog() }
        date!!.setOnClickListener { openDatePickerDialog() }
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
                stitle = title!!.text.toString()
                sbody = title!!.text.toString()
                if (stitle!!.length != 0) {
                    setAlarm(calSet)
                    db!!.insertData(count, stitle, sbody, timeText, dateText)
                }
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
            this@AddNewToDoList,
            now[Calendar.HOUR_OF_DAY],
            now[Calendar.MINUTE], false
        )
        timepickerdialog.isThemeDark = false
        timepickerdialog.vibrate(true)
        timepickerdialog.dismissOnPause(true)
        timepickerdialog.enableSeconds(false)
        timepickerdialog.show(fragmentManager, "Timepickerdialog")
    }

    fun openDatePickerDialog() {
        val now = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog.newInstance(
            this@AddNewToDoList,
            now[Calendar.YEAR],
            now[Calendar.MONTH],
            now[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.isThemeDark = false
        datePickerDialog.vibrate(true)
        datePickerDialog.dismissOnPause(true)
        datePickerDialog.show(fragmentManager, "Datepickerdialog")
    }

    override fun onDateSet(view: DatePickerDialog, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        dateText = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
        date!!.text = dateText
        calSet!![Calendar.DAY_OF_MONTH] = dayOfMonth
        calSet!![Calendar.MONTH] = monthOfYear
        calSet!![Calendar.YEAR] = year
        if (calSet!!.compareTo(calNow) <= 0) {
            calSet!!.add(Calendar.DATE, 1)
        }
    }

    private fun setAlarm(targetcal: Calendar?) {
        count = db!!.currentId
        if (count == -1) count = 1 else count += 1
        val intent = Intent(baseContext, AlarmReceiver::class.java)
        intent.putExtra("Title", stitle)
        pendingIntent = PendingIntent.getBroadcast(baseContext, count, intent, 0)
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager!![AlarmManager.RTC_WAKEUP, targetcal!!.timeInMillis] = pendingIntent
    }

    override fun onTimeSet(view: RadialPickerLayout, hourOfDay: Int, minute: Int, second: Int) {
        val dt = Date(0, 0, 0, hourOfDay, minute, second)
        val sdf = SimpleDateFormat("hh:mm a")
        timeText = sdf.format(dt)
        time!!.text = timeText
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