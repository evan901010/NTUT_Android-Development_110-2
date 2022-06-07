package com.example.afinal.model;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.afinal.MainApplication;
import com.example.afinal.views.MainActivity;

import java.util.ArrayList;

public class DataModel extends SQLiteOpenHelper {
    private static final String DataBaseName;
    private static final String DataBaseTable;

    static {
        DataBaseName = "DataBaseIt";
        DataBaseTable = "Users";
    }

    private static final int DataBaseVersion = 1;


    public void getNewNotification(@NonNull StatusBarNotification sbn) {
        Bundle extras = sbn.getNotification().extras;
        String  packageName= sbn.getPackageName();
        String title = extras.getString(Notification.EXTRA_TITLE);
        String text = extras.getString(Notification.EXTRA_TEXT);
        long timestamp = sbn.getPostTime();
        insertNotification(packageName, title, text, timestamp);
    }

    public void insertNotification(String packageName, String title, String text, long timestamp){
        ContentValues contentValues = new ContentValues();
        contentValues.put("packageName",packageName);
        contentValues.put("title",title);
        contentValues.put("text",text);
        contentValues.put("timestamp",timestamp);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DataBaseTable,null,contentValues);

        readData();
    }

    public void readData(){
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor c = db.rawQuery("SELECT * FROM " + DataBaseTable,null);
        c.moveToLast();

        ArrayList<ArrayList<String>> notificationList = new ArrayList<>();

        while (c.moveToPrevious()){
            ArrayList<String> notification = new ArrayList<>();
            for(int column = 1; column < 4; column++){
                notification.add(c.getString(column));
            }
            notificationList.add(notification);
        }
        MainActivity mainActivityContext = (MainActivity) MainApplication.getMainActivityContext();
        mainActivityContext.setNotificationList(notificationList);
    }

    public DataModel(@Nullable Context context) {
        super(context, DataBaseName, null, DataBaseVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SqlTable = "CREATE TABLE IF NOT EXISTS Users (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "packageName TEXT not null," +
                "title TEXT not null," +
                "text TEXT not null," +
                "timestamp INTEGER not null" +
                ")";
        sqLiteDatabase.execSQL(SqlTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        final String SQL = "DROP TABLE Users";
        sqLiteDatabase.execSQL(SQL);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
