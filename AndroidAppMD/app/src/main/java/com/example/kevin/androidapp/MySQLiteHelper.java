package com.example.kevin.androidapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kevin on 25-3-2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    // Database info
    private static final String DATABASE_NAME = "taskmanager.db";
    private static final int DATABASE_VERSION = 1;

    // Assignments
    public static final String TABLE_ASSIGNMENTS = "assignments";
    public static final String COLUMN_TASK_ID = "assignment_id";
    public static final String COLUMN_TASK_DESCRIPTION  = "description";
   // public static final String COLUMN_NAME = "name";

       // Creating the table
    private static final String DATABASE_CREATE_ASSIGNMENTS =
            "CREATE TABLE " + TABLE_ASSIGNMENTS +
                    "(" +
                    COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TASK_DESCRIPTION  + " text not null" +
                  //  COLUMN_NAME + " text not null" +
                    ");";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(DATABASE_CREATE_ASSIGNMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*
         * When the database gets upgraded you should handle the update to make sure there is no data loss.
         * This is the default code you put in the upgrade method, to delete the table and call the oncreate again.
         */
        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENTS);
        onCreate(db);
    }
}

