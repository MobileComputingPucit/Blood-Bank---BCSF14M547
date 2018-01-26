package com.example.umaransari.myproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Umar Ansari on 25/01/2018.
 */

public class DBHandler extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Donors";

    public static final String ID = "ID";
    public static final String Name = "Name";
    public static final String Email = "Email";
    public static final String Password = "Password";
    public static final String BloodGroup = "BloodGroup";
    public static final String Gender = "Gender";
    public static final String DOB = "DOB";
    public static final String Country = "Country";
    public static final String Address = "Address";
    public static final String Phone = "Phone";
    public static final String SecQuestion = "Question";
    public static final String SecAns = "Answer";


    public DBHandler(Context context){
        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +" ( Name TEXT NOT NULL PRIMARY KEY, Email VARCHAR(32) NOT NULL, Password VARCHAR(32) NOT NULL, BloodGroup VARCHAR(3) NOT NULL, Gender TEXT NOT NULL, DOB VARCHAR(20) NOT NULL, Address VARCHAR(50) NOT NULL, Country TEXT NOT NULL, Phone VARCHAR(32) NOT NULL, Question TEXT NOT NULL, Answer TEXT NOT NULL )";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String drop = "drop table if exists " +TABLE_NAME;
        db.execSQL(drop);
        onCreate(db);
    }

    public long insert(String name, String email, String password, String blood,String gender, String dob, String address, String country, String phone, String quest, String answer){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Name, name);
        cv.put(Email, email);
        cv.put(Password, password);
        cv.put(BloodGroup, blood);
        cv.put(Gender, gender);
        cv.put(DOB, dob);
        cv.put(Address, address);
        cv.put(Country, country);
        cv.put(Phone, phone);
        cv.put(SecQuestion, quest);
        cv.put(SecAns, answer);

        long i = db.insert(TABLE_NAME,null,cv);
        Log.d("Database Handler ", String.valueOf(i));
        db.close();

        return i;
    }

    public boolean checkUser(String email, String password )
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query ="SELECT * FROM "+ TABLE_NAME + " WHERE " + Email + " = '"+email+"' AND " + Password + " = '"+password+"'";

        Cursor cursor = db.rawQuery(query,null);

        if(cursor!=null)
        {
            if(cursor.getCount() > 0)
            {
                return true;
            }
        }

        return false;
    }
    public Cursor getQA (String email){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select Question, Answer, Password from " +TABLE_NAME +" WHERE " +Email +" = '" +email +"'";

        Cursor cursor = db.rawQuery(query,null);

        //String error = "No user found";
        return cursor;
    }

    public Cursor getData(String blood, String country){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select Name, BloodGroup, Country, Phone from " +TABLE_NAME +" WHERE " +BloodGroup +" = '" +blood +"' AND " +Country +" = '" +country +"'";
        Cursor res = db.rawQuery(query,null);
        return res;
    }

}
