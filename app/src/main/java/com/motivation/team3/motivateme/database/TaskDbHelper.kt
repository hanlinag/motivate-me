package com.motivation.team3.motivateme.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.motivation.team3.motivateme.model.Contact;
import com.motivation.team3.motivateme.model.Note;

public class TaskDbHelper{

    private static final String TAG = TaskDbHelper.class.getSimpleName();

    private static final int DATABASEVERSION = 1;
    private static final String DATABASE_NAME = "note_two.db";

    private static  final String TABLE_NAME = "data_two_table";
    private static  final String TABLE_NAME_NOTE = "data_note_table";
    private static  final String TABLE_NAME_HOME = "data_home_table";

    private static final String TABLE_COLUMN_ID = "_id";
    private static final String TABLE_COLUM_COUNT="count";
    private static final String TABLE_COLUMN_TITLE = "title";
    private static final String TABLE_COLUMN_BODY = "body";
    private static final String TABLE_COLUM_TIME="time";
    private static final String TABLE_COLUM_DATE="date";

    private static final String TABLE_COLUMN_NOTE_ID="note_id";
    private static final String TABLE_COLUMN_NOTE_TITLE="notetitle";
    private static final String TABLE_COLUMN_NOTE_BODY="notebody";
    private static final String TABLE_COLUMN_NOTE_TIME="notetime";
    private static final String TABLE_COLUMN_NOTE_DATE="notedate";

    private static final String TABLE_COLUMN_HOME_ID="home_id";
    private static final String TABLE_COLUMN_HOME_TITLE="hometitle";
    private static final String TABLE_COLUMN_HOME_TIME="hometime";
    private static final String TABLE_COLUMN_HOME_DATE="homedate";

    private DatabaseOpenHelper openHelper;
    private SQLiteDatabase database;

    public TaskDbHelper(Context aContext)
    {
        openHelper = new DatabaseOpenHelper(aContext);
        database = openHelper.getWritableDatabase();
    }

    public void insertData(int count, String title,String body, String time, String date) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COLUM_COUNT, count);
        contentValues.put(TABLE_COLUMN_TITLE, title);
        contentValues.put(TABLE_COLUMN_BODY, body);
        contentValues.put(TABLE_COLUM_TIME, time);
        contentValues.put(TABLE_COLUM_DATE, date);

        database.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllData()
    {
        String buildSQL = "SELECT * FROM "+TABLE_NAME;
        return database.rawQuery(buildSQL,null);
    }

    public Contact getData(int id)
    {
        String buildSQL="SELECT * FROM "+TABLE_NAME;

        Cursor cursor=database.rawQuery(buildSQL, null);

        if(cursor.moveToPosition(id))
        {
            int  idd = cursor.getInt(cursor.getColumnIndex( cursor.getColumnName(0))) ;
            int  count= cursor.getInt(cursor.getColumnIndex( cursor.getColumnName(1))) ;
            String title= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(2))) ;
            String body=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3)));
            String time= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(4))) ;
            String date= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(5))) ;
            Contact contact=new Contact(idd,count,title,body,time,date);
            return contact;
        }
        return null;
    }

    public String getID(String title)
    {
        String id="";
        String buildSQL = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = database.rawQuery(buildSQL, null);

        if (cursor.moveToFirst())
        {
            do {

                if(title.equals(cursor.getString(1)))
                {
                    id= cursor.getString(0);
                }

            }
            while (cursor.moveToNext());
        }
        return id;
    }
    public int updateData(String id ,int count,String title,String body,String time,String date)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COLUM_COUNT, count);
        contentValues.put(TABLE_COLUMN_TITLE, title);
        contentValues.put(TABLE_COLUMN_BODY, body);
        contentValues.put(TABLE_COLUM_TIME, time);
        contentValues.put(TABLE_COLUM_DATE, date);

        return database.update(TABLE_NAME, contentValues, TABLE_COLUMN_ID + " =  ? ",new String[] { id });
    }

    public void deleteData(String id)
    {
        database.delete(TABLE_NAME, TABLE_COLUMN_ID + " = ? ",new String[] { id });
    }

    public int getCount()
    {
        String buildSQL = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = database.rawQuery(buildSQL, null);
        int count=0;
        if (cursor.moveToFirst())
        {
            do
            {
                count++;
            }
            while (cursor.moveToNext());
        }

        return count;
    }

    public int[] getAllCount()
    {
        String buildSQL = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = database.rawQuery(buildSQL, null);
        int[] arr=new int[getCount()];
        int i=0;
        if(cursor.moveToFirst())
        {
            do
            {
                arr[i++]=cursor.getInt(cursor.getColumnIndex( cursor.getColumnName(1)));
            }while (cursor.moveToNext());
        }
        return arr;
    }

    public int getCurrentId()
    {
        int id=-1;
        String buildSQL = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor = database.rawQuery(buildSQL, null);
        if(cursor.moveToLast())
            id=cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0)));
        return id;
    }

    public Contact getLastToDoList()
    {
        String buildSQL="SELECT * FROM "+TABLE_NAME;

        Cursor cursor=database.rawQuery(buildSQL, null);

        if(cursor.moveToFirst())
        {
            int  idd = cursor.getInt(cursor.getColumnIndex( cursor.getColumnName(0))) ;
            int  count= cursor.getInt(cursor.getColumnIndex( cursor.getColumnName(1))) ;
            String title= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(2))) ;
            String body=cursor.getString(cursor.getColumnIndex(cursor.getColumnName(3)));
            String time= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(4))) ;
            String date= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(5))) ;
            Contact contact=new Contact(idd,count,title,body,time,date);
            return contact;
        }
        else
        {

            String title= "No Title" ;

            Contact contact=new Contact(0,0,title," "," "," ");
            return contact;
        }
    }
    //------------------------------------------------------------------------------------------------------------------------

    public void noteInsertData(String title,String body,String time,String date)
    {
        ContentValues contentValues=new ContentValues();

        contentValues.put(TABLE_COLUMN_NOTE_TITLE,title);
        contentValues.put(TABLE_COLUMN_NOTE_BODY,body);
        contentValues.put(TABLE_COLUMN_NOTE_TIME,time);
        contentValues.put(TABLE_COLUMN_NOTE_DATE,date);
        database.insert(TABLE_NAME_NOTE,null,contentValues);
    }

    public Cursor getAllNoteData()
    {
        String buildSQL = "SELECT * FROM " + TABLE_NAME_NOTE;
        return database.rawQuery(buildSQL,null);
    }

    public Note getNoteData(int id)
    {
        String buildSQL="SELECT * FROM "+TABLE_NAME_NOTE;

        Cursor cursor=database.rawQuery(buildSQL, null);

        if(cursor.moveToPosition(id))
        {
            int  idd = cursor.getInt(cursor.getColumnIndex( cursor.getColumnName(0))) ;
            String title= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(1))) ;
            String body= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(2))) ;
            String time= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(3))) ;
            String date= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(4))) ;
            Note contact=new Note(idd,title,body,time,date);
            return contact;
        }
        return null;
    }

    public Note getLastNote()
    {
        String buildSQL="SELECT * FROM "+TABLE_NAME_NOTE;

        Cursor cursor=database.rawQuery(buildSQL, null);

        if(cursor.moveToFirst())
        {
            int  idd = cursor.getInt(cursor.getColumnIndex( cursor.getColumnName(0))) ;
            String title= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(1))) ;
            String body= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(2))) ;
            String time= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(3))) ;
            String date= cursor.getString(cursor.getColumnIndex( cursor.getColumnName(4))) ;
            Note note=new Note(idd,title,body,time,date);
            return note;
        }
        else
        {
            String title="No Title" ;
            Note note=new Note(0,title," "," "," ");
            return note;
        }
    }

    public int updateNoteData(int id,String title,String body,String time,String date)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COLUMN_NOTE_TITLE, title);
        contentValues.put(TABLE_COLUMN_NOTE_BODY,body);
        contentValues.put(TABLE_COLUMN_NOTE_TIME, time);
        contentValues.put(TABLE_COLUMN_NOTE_DATE, date);

        return database.update(TABLE_NAME_NOTE, contentValues, TABLE_COLUMN_NOTE_ID + " =  ? ",new String[] {String.valueOf(id)});
    }

    public void deleteNoteData(int id)
    {
        database.delete(TABLE_NAME_NOTE, TABLE_COLUMN_NOTE_ID + " = ? ",new String[] { String.valueOf(id) });
    }

    //------------------------------------------------------------------------------------------------------------------------

    public void insertHome(String title,String time,String date)
    {
        ContentValues contentValues=new ContentValues();

        contentValues.put(TABLE_COLUMN_HOME_TITLE,title);
        contentValues.put(TABLE_COLUMN_HOME_TIME,time);
        contentValues.put(TABLE_COLUMN_HOME_DATE,date);
        database.insert(TABLE_NAME_HOME,null,contentValues);
    }


    public Cursor getAllHomeData()
    {
        String buildSQL = "SELECT * FROM " + TABLE_NAME_HOME;
        return database.rawQuery(buildSQL,null);
    }


    //------------------------------------------------------------------------------------------------------------------------

    private class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        public DatabaseOpenHelper(Context context )
        {

            super(context, DATABASE_NAME, null, DATABASEVERSION);
        }

        public void onCreate(SQLiteDatabase db)
        {
            String buildSQL = "CREATE TABLE "+ TABLE_NAME
                    + "(" +
                    TABLE_COLUMN_ID + " INTEGER PRIMARY KEY," +
                    TABLE_COLUM_COUNT + " INTEGER , "+
                    TABLE_COLUMN_TITLE + " TEXT , " +
                    TABLE_COLUMN_BODY + " TEXT , " +
                    TABLE_COLUM_TIME + " TEXT , "+
                    TABLE_COLUM_DATE + " TEXT ) ";

            db.execSQL(buildSQL);

            String notebuildSQL="CREATE TABLE "+ TABLE_NAME_NOTE
                    + "(" +
                    TABLE_COLUMN_NOTE_ID + " INTEGER PRIMARY KEY," +
                    TABLE_COLUMN_NOTE_TITLE + " TEXT , " +
                    TABLE_COLUMN_NOTE_BODY + " TEXT , " +
                    TABLE_COLUMN_NOTE_TIME + " TEXT , "+
                    TABLE_COLUMN_NOTE_DATE + " TEXT ) ";
            db.execSQL(notebuildSQL);

            String homebuildSQL="CREATE TABLE "+TABLE_NAME_HOME
                    +" ("+
                    TABLE_COLUMN_HOME_ID+ " INTEGER PRIMARY KEY," +
                    TABLE_COLUMN_HOME_TITLE + " TEXT , " +
                    TABLE_COLUMN_HOME_TIME + " TEXT , " +
                    TABLE_COLUMN_HOME_DATE + " TEXT ) ";
            db.execSQL(homebuildSQL);

        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            String buildSQL = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(buildSQL);
            onCreate(db);
        }
    }
}