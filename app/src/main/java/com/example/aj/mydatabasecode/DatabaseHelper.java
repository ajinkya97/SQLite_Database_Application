package com.example.aj.mydatabasecode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aj on 30-May-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="students.db";
    public static final String TABLE_NAME="students_Table";
    public static final String COL_1="ID";
    public static final String COL_2="FNAME";
    public static final String COL_3="LNAME";
    public static final String COL_4="MARKS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
       // SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT , FNAME TEXT , LNAME TEXT , MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String fname,String lname,String marks)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,fname);
        contentValues.put(COL_3,lname);
        contentValues.put(COL_4,marks);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
