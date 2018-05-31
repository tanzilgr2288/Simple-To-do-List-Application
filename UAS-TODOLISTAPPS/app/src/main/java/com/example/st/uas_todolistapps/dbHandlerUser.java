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
public class dbHandlerUser extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDoListApps";


    //user
    private static final String TABLE_CONTACTS = "userdataToDoList";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FULLNAME = "fullname";
    private static final String COLUMN_GENDER = "gender";

    private static final String CREATE_TABLE_CONTACT = "CREATE TABLE "+TABLE_CONTACTS+"("+COLUMN_EMAIL+" VARCHAR(100) PRIMARY KEY, "+
            COLUMN_PASSWORD+" VARCHAR(30), "+COLUMN_FULLNAME+" VARCHAR(100), "+COLUMN_GENDER+" VARCHAR(1) )";

    private static final String DELETE_TABLE_CONTACTS = "DROP TABLE IF EXISTS "+TABLE_CONTACTS;


    //data
    private static final String TABLE_CONTACTS2 = "taskdataToDoList";
    private static final String COLUMN_ID2 = "task_id";
    private static final String COLUMN_NAME2 = "task_name";
    private static final String COLUMN_NOTES2 = "notes";
    private static final String COLUMN_CATEGORY2 = "category_id";
    private static final String COLUMN_FLAG2 = "flag_done";

    private static final String CREATE_TABLE_CONTACT2 = "CREATE TABLE "+TABLE_CONTACTS2+"("+COLUMN_ID2+" INTEGER PRIMARY KEY, "+
            COLUMN_NAME2+" TEXT, "+COLUMN_NOTES2+" TEXT, "+COLUMN_CATEGORY2+" VARCHAR(1), "+COLUMN_FLAG2+"  VARCHAR(1))";

    private static final String DELETE_TABLE_CONTACTS2 = "DROP TABLE IF EXISTS "+TABLE_CONTACTS2;




    public dbHandlerUser(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
        db.execSQL(CREATE_TABLE_CONTACT2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL ( DELETE_TABLE_CONTACTS );
        db.execSQL ( DELETE_TABLE_CONTACTS2 );
        onCreate(db);
    }

    public void adduser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_FULLNAME, user.getName());
        values.put(COLUMN_GENDER, user.getGender());
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public ArrayList<User> getAlldata()
    {
        ArrayList<User> userlist = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst())
        {
            do
            {
                User contact = new User(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );

                userlist.add(contact);
            }
            while(cursor.moveToNext());
        }

        return userlist;
    }

    public boolean checkingUser(User user)
    {
        ArrayList<User> dataCheck = getAlldata();
        for(int i=0 ;i<dataCheck.size();i++)
        {
            if (dataCheck.get(i).getEmail().equals(user.getEmail()))
            {
                return true;
            }
        }
        return false;
    }

    public boolean loginUser(User user)
    {
        ArrayList<User> dataCheck = getAlldata();
        for(int i=0 ;i<dataCheck.size();i++)
        {
            if (dataCheck.get(i).getEmail().equals(user.getEmail()))
            {
                if(dataCheck.get(i).getPassword().equals(user.getPassword()))
                {
                    return true;
                }

            }
        }
        return false;
    }

    public User getUserDetail(String email)
    {
        ArrayList<User> dataCheck = getAlldata();
        for(int i=0 ;i<dataCheck.size();i++)
        {
            if (dataCheck.get(i).getEmail().equals(email))
            {
                return dataCheck.get(i);
            }
        }
        return null;
    }

    public String getUserName (String email)
    {
        ArrayList<User> dataCheck = getAlldata();
        for(int i=0 ;i<dataCheck.size();i++)
        {
            if (dataCheck.get(i).getEmail().equals(email))
            {
                return dataCheck.get(i).getName();
            }
        }
        return null;
    }

    public void UpdateDataUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_FULLNAME, user.getName());
        values.put(COLUMN_GENDER, user.getGender());
        db.update(TABLE_CONTACTS,values,COLUMN_EMAIL+" = ?", new String[]{user.getEmail()});
        db.close();
    }
}
