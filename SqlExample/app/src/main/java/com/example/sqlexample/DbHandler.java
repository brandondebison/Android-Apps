package com.example.sqlexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "userdb";
    private static final int DB_VERSION = 1;

    private static final String TABLE_USERS = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOC = "location";
    private static final String KEY_DES = "designation";


    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db ) {
        String CREATE_TABLE = "CREATE TABLE userdetails (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "name TEXT, location TEXT, designation TEXT) ";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userdetails");
        onCreate(db);

    }


    void insertUserDetails(String name, String location, String designation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put("name", name);
        cValues.put("location", location);
        cValues.put("designation", designation);
        long newRowId = db.insert("userdetails",null, cValues);
        db.close();

    }

    public ArrayList<HashMap<String, String >> GetUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String >> userList = new ArrayList<>();
        String query = "SELECT name, location, designation FROM userdetails";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("name", cursor.getString(cursor.getColumnIndex("name")));
            user.put("location", cursor.getString(cursor.getColumnIndex("location")));
            user.put("designation", cursor.getString(cursor.getColumnIndex("designation")));
            userList.add(user);
        }

        return userList;
    }

    // in this you use an if because your looking by Id which you would only have one of
    public ArrayList<HashMap<String, String >> GetUserByUserId(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String >> userList = new ArrayList<>();
        String query = "SELECT name, location, designation FROM userdetails";
        Cursor cursor = db.query("userdetails", new String[]{"name", "location", "designation"},
                "id=?", new String[]{ String.valueOf(userid) },
                null,null,null,null);
        if (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("name", cursor.getString(cursor.getColumnIndex("name")));
            user.put("location", cursor.getString(cursor.getColumnIndex("location")));
            user.put("designation", cursor.getString(cursor.getColumnIndex("designation")));
            userList.add(user);
        }

        return userList;
    }

    public void DeleteUser(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("userdetails", "id = ?", new String[]{String.valueOf(userid)});
        db.close();
    }

    public int UpdateUserDetails(String location, String designation, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put("location", location);
        cValues.put("designation", designation);
        int count = db.update("userdetails", cValues, "id=?",
                new String[] {String.valueOf(id)});
        db.close();
        return count;
    }



}
