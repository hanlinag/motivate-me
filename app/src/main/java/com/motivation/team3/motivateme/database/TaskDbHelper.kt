package com.motivation.team3.motivateme.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.motivation.team3.motivateme.model.Contact
import com.motivation.team3.motivateme.model.Note

class TaskDbHelper(aContext: Context?) {
    private val openHelper: DatabaseOpenHelper
    private val database: SQLiteDatabase
    fun insertData(count: Int, title: String?, body: String?, time: String?, date: String?) {
        val contentValues = ContentValues()
        contentValues.put(TABLE_COLUM_COUNT, count)
        contentValues.put(TABLE_COLUMN_TITLE, title)
        contentValues.put(TABLE_COLUMN_BODY, body)
        contentValues.put(TABLE_COLUM_TIME, time)
        contentValues.put(TABLE_COLUM_DATE, date)
        database.insert(TABLE_NAME, null, contentValues)
    }

    val allData: Cursor
        get() {
            val buildSQL = "SELECT * FROM " + TABLE_NAME
            return database.rawQuery(buildSQL, null)
        }

    fun getData(id: Int): Contact? {
        val buildSQL = "SELECT * FROM " + TABLE_NAME
        val cursor = database.rawQuery(buildSQL, null)
        if (cursor.moveToPosition(id)) {
            val idd = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)))
            val count = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(1)))
            val title =
                cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)))
            val body =
                cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3)))
            val time =
                cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)))
            val date =
                cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5)))
            return Contact(idd, count, title, body, time, date)
        }
        return null
    }

    fun getID(title: String): String {
        var id = ""
        val buildSQL = "SELECT * FROM " + TABLE_NAME
        val cursor = database.rawQuery(buildSQL, null)
        if (cursor.moveToFirst()) {
            do {
                if (title == cursor.getString(1)) {
                    id = cursor.getString(0)
                }
            } while (cursor.moveToNext())
        }
        return id
    }

    fun updateData(
        id: String,
        count: Int,
        title: String?,
        body: String?,
        time: String?,
        date: String?
    ): Int {
        val contentValues = ContentValues()
        contentValues.put(TABLE_COLUM_COUNT, count)
        contentValues.put(TABLE_COLUMN_TITLE, title)
        contentValues.put(TABLE_COLUMN_BODY, body)
        contentValues.put(TABLE_COLUM_TIME, time)
        contentValues.put(TABLE_COLUM_DATE, date)
        return database.update(TABLE_NAME, contentValues, TABLE_COLUMN_ID + " =  ? ", arrayOf(id))
    }

    fun deleteData(id: String) {
        database.delete(TABLE_NAME, TABLE_COLUMN_ID + " = ? ", arrayOf(id))
    }

    val count: Int
        get() {
            val buildSQL = "SELECT * FROM " + TABLE_NAME
            val cursor = database.rawQuery(buildSQL, null)
            var count = 0
            if (cursor.moveToFirst()) {
                do {
                    count++
                } while (cursor.moveToNext())
            }
            return count
        }
    val allCount: IntArray
        get() {
            val buildSQL = "SELECT * FROM " + TABLE_NAME
            val cursor = database.rawQuery(buildSQL, null)
            val arr = IntArray(count)
            var i = 0
            if (cursor.moveToFirst()) {
                do {
                    arr[i++] = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(1)))
                } while (cursor.moveToNext())
            }
            return arr
        }
    val currentId: Int
        get() {
            var id = -1
            val buildSQL = "SELECT * FROM " + TABLE_NAME
            val cursor = database.rawQuery(buildSQL, null)
            if (cursor.moveToLast()) id =
                cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)))
            return id
        }
    val lastToDoList: Contact
        get() {
            val buildSQL = "SELECT * FROM " + TABLE_NAME
            val cursor = database.rawQuery(buildSQL, null)
            return if (cursor.moveToFirst()) {
                val idd = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)))
                val count = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(1)))
                val title =
                    cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)))
                val body =
                    cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3)))
                val time =
                    cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)))
                val date =
                    cursor.getString(cursor.getColumnIndex(cursor.getColumnName(5)))
                Contact(idd, count, title, body, time, date)
            } else {
                val title = "No Title"
                Contact(0, 0, title, " ", " ", " ")
            }
        }

    //------------------------------------------------------------------------------------------------------------------------
    fun noteInsertData(title: String?, body: String?, time: String?, date: String?) {
        val contentValues = ContentValues()
        contentValues.put(TABLE_COLUMN_NOTE_TITLE, title)
        contentValues.put(TABLE_COLUMN_NOTE_BODY, body)
        contentValues.put(TABLE_COLUMN_NOTE_TIME, time)
        contentValues.put(TABLE_COLUMN_NOTE_DATE, date)
        database.insert(TABLE_NAME_NOTE, null, contentValues)
    }

    val allNoteData: Cursor
        get() {
            val buildSQL = "SELECT * FROM " + TABLE_NAME_NOTE
            return database.rawQuery(buildSQL, null)
        }

    fun getNoteData(id: Int): Note? {
        val buildSQL = "SELECT * FROM " + TABLE_NAME_NOTE
        val cursor = database.rawQuery(buildSQL, null)
        if (cursor.moveToPosition(id)) {
            val idd = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)))
            val title =
                cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)))
            val body =
                cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)))
            val time =
                cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3)))
            val date =
                cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)))
            return Note(idd, title, body, time, date)
        }
        return null
    }

    val lastNote: Note
        get() {
            val buildSQL = "SELECT * FROM " + TABLE_NAME_NOTE
            val cursor = database.rawQuery(buildSQL, null)
            return if (cursor.moveToFirst()) {
                val idd = cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)))
                val title =
                    cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)))
                val body =
                    cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2)))
                val time =
                    cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3)))
                val date =
                    cursor.getString(cursor.getColumnIndex(cursor.getColumnName(4)))
                Note(idd, title, body, time, date)
            } else {
                val title = "No Title"
                Note(0, title, " ", " ", " ")
            }
        }

    fun updateNoteData(id: Int, title: String?, body: String?, time: String?, date: String?): Int {
        val contentValues = ContentValues()
        contentValues.put(TABLE_COLUMN_NOTE_TITLE, title)
        contentValues.put(TABLE_COLUMN_NOTE_BODY, body)
        contentValues.put(TABLE_COLUMN_NOTE_TIME, time)
        contentValues.put(TABLE_COLUMN_NOTE_DATE, date)
        return database.update(
            TABLE_NAME_NOTE,
            contentValues,
            TABLE_COLUMN_NOTE_ID + " =  ? ",
            arrayOf(id.toString())
        )
    }

    fun deleteNoteData(id: Int) {
        database.delete(TABLE_NAME_NOTE, TABLE_COLUMN_NOTE_ID + " = ? ", arrayOf(id.toString()))
    }

    //------------------------------------------------------------------------------------------------------------------------
    fun insertHome(title: String?, time: String?, date: String?) {
        val contentValues = ContentValues()
        contentValues.put(TABLE_COLUMN_HOME_TITLE, title)
        contentValues.put(TABLE_COLUMN_HOME_TIME, time)
        contentValues.put(TABLE_COLUMN_HOME_DATE, date)
        database.insert(TABLE_NAME_HOME, null, contentValues)
    }

    val allHomeData: Cursor
        get() {
            val buildSQL = "SELECT * FROM " + TABLE_NAME_HOME
            return database.rawQuery(buildSQL, null)
        }

    //------------------------------------------------------------------------------------------------------------------------
    private inner class DatabaseOpenHelper(context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASEVERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            val buildSQL = ("CREATE TABLE " + TABLE_NAME
                    + "(" +
                    TABLE_COLUMN_ID + " INTEGER PRIMARY KEY," +
                    TABLE_COLUM_COUNT + " INTEGER , " +
                    TABLE_COLUMN_TITLE + " TEXT , " +
                    TABLE_COLUMN_BODY + " TEXT , " +
                    TABLE_COLUM_TIME + " TEXT , " +
                    TABLE_COLUM_DATE + " TEXT ) ")
            db.execSQL(buildSQL)
            val notebuildSQL = ("CREATE TABLE " + TABLE_NAME_NOTE
                    + "(" +
                    TABLE_COLUMN_NOTE_ID + " INTEGER PRIMARY KEY," +
                    TABLE_COLUMN_NOTE_TITLE + " TEXT , " +
                    TABLE_COLUMN_NOTE_BODY + " TEXT , " +
                    TABLE_COLUMN_NOTE_TIME + " TEXT , " +
                    TABLE_COLUMN_NOTE_DATE + " TEXT ) ")
            db.execSQL(notebuildSQL)
            val homebuildSQL = ("CREATE TABLE " + TABLE_NAME_HOME
                    + " (" +
                    TABLE_COLUMN_HOME_ID + " INTEGER PRIMARY KEY," +
                    TABLE_COLUMN_HOME_TITLE + " TEXT , " +
                    TABLE_COLUMN_HOME_TIME + " TEXT , " +
                    TABLE_COLUMN_HOME_DATE + " TEXT ) ")
            db.execSQL(homebuildSQL)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            val buildSQL = "DROP TABLE IF EXISTS " + TABLE_NAME
            db.execSQL(buildSQL)
            onCreate(db)
        }
    }

    companion object {
        private val TAG = TaskDbHelper::class.java.simpleName
        private const val DATABASEVERSION = 1
        private const val DATABASE_NAME = "note_two.db"
        private const val TABLE_NAME = "data_two_table"
        private const val TABLE_NAME_NOTE = "data_note_table"
        private const val TABLE_NAME_HOME = "data_home_table"
        private const val TABLE_COLUMN_ID = "_id"
        private const val TABLE_COLUM_COUNT = "count"
        private const val TABLE_COLUMN_TITLE = "title"
        private const val TABLE_COLUMN_BODY = "body"
        private const val TABLE_COLUM_TIME = "time"
        private const val TABLE_COLUM_DATE = "date"
        private const val TABLE_COLUMN_NOTE_ID = "note_id"
        private const val TABLE_COLUMN_NOTE_TITLE = "notetitle"
        private const val TABLE_COLUMN_NOTE_BODY = "notebody"
        private const val TABLE_COLUMN_NOTE_TIME = "notetime"
        private const val TABLE_COLUMN_NOTE_DATE = "notedate"
        private const val TABLE_COLUMN_HOME_ID = "home_id"
        private const val TABLE_COLUMN_HOME_TITLE = "hometitle"
        private const val TABLE_COLUMN_HOME_TIME = "hometime"
        private const val TABLE_COLUMN_HOME_DATE = "homedate"
    }

    init {
        openHelper = DatabaseOpenHelper(aContext)
        database = openHelper.writableDatabase
    }
}