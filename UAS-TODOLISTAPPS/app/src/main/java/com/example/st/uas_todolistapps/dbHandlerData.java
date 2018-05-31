package com.example.st.uas_todolistapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ST on 6/11/2016.
 */
public class dbHandlerData extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDoListApps";

    private static final String TABLE_CONTACTS = "taskdataToDoList";
    private static final String COLUMN_ID = "task_id";
    private static final String COLUMN_NAME = "task_name";
    private static final String COLUMN_NOTES = "notes";
    private static final String COLUMN_CATEGORY = "category_id";
    private static final String COLUMN_FLAG = "flag_done";

    public dbHandlerData(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addTask(Task task)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, task.getTask_name());
        values.put(COLUMN_NOTES, task.getNotes());
        values.put(COLUMN_CATEGORY, task.getCategory_id());
        values.put(COLUMN_FLAG, task.getFlag_done());
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public ArrayList<Task> getAllData()
    {
        ArrayList<Task> userlist = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Task task = new Task(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4)
                );

                userlist.add(task);
            }
            while(cursor.moveToNext());
        }

        return userlist;
    }

    public ArrayList<Task> getDataCategory(int categoryCode)
    {
        ArrayList<Task> userlist = new ArrayList<Task>();
        userlist = getAllData();

        if (categoryCode == 0)
        {
            return userlist;
        }

        else
        {

            ArrayList<Task> tempHelp = new ArrayList<>();


            for(int i=0 ;i<userlist.size() ; i++)
            {
                if(userlist.get(i).getCategory_id() == categoryCode)
                {
                    tempHelp.add(userlist.get(i));
                }
            }

            return tempHelp;
        }
    }

    public void UpdateDataTask(Task task)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, task.getTask_name());
        values.put(COLUMN_NOTES, task.getNotes());
        values.put(COLUMN_CATEGORY, task.getCategory_id());
        values.put(COLUMN_FLAG, task.getFlag_done());
        db.update(TABLE_CONTACTS,values,COLUMN_ID+" = ?", new String[]{String.valueOf(task.getId())});
        db.close();
    }

    public void deleteDataTask (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public Task getOneTask (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{COLUMN_ID,COLUMN_NAME,COLUMN_NOTES,COLUMN_CATEGORY,COLUMN_FLAG},
                COLUMN_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);

        if(cursor.moveToFirst())
        {
            Task task = new Task( cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getInt(4));
            return task;
        }

        return null ;

    }


}
