package com.example.saatvik.sipcall.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telecom.Call;

/**
 * Created by kshitiz on 11/28/15.
 */
public class CallLogsDatabase extends SQLiteOpenHelper {
    public static final String Call_Logs = "Call_Logs";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_Name = "displayname";
    public static final String COLUMN_Date="calldate";
    public static final String COLUMN_Call_type="call_type";
    private static final String DATABASE_NAME = "SIPCall.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + Call_Logs + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_Name
            + " text not null, " + COLUMN_Date
            + " date not null, " + COLUMN_Call_type
            + " text not null);";

    public CallLogsDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + Call_Logs);
        onCreate(db);

    }
}
