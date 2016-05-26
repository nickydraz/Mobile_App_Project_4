package com.example.bacraig.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bacraig on 5/25/2016.
 */
public class QuizDBHelper  extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + QuizDBContract.QuizDBEntry.TABLE_NAME + " (" +
                    QuizDBContract.QuizDBEntry._ID + " INTEGER PRIMARY KEY," +
                    QuizDBContract.QuizDBEntry.COLUMN_NAME_TEST_ID + TEXT_TYPE + COMMA_SEP +
                    QuizDBContract.QuizDBEntry.COLUMN_NAME_STUDENT + TEXT_TYPE + COMMA_SEP +
                    QuizDBContract.QuizDBEntry.COLUMN_NAME_SCORE + INT_TYPE +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + QuizDBContract.QuizDBEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TestDatabase.db";

    public QuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static int idCounter;
    public void addRecord(String student, int score){
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(QuizDBContract.QuizDBEntry.COLUMN_NAME_TEST_ID, idCounter++);
        values.put(QuizDBContract.QuizDBEntry.COLUMN_NAME_STUDENT, student);
        values.put(QuizDBContract.QuizDBEntry.COLUMN_NAME_SCORE, score);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(
                QuizDBContract.QuizDBEntry.TABLE_NAME,
                null,
                values);

    }

    public List<Object[]> getRecords(){
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                QuizDBContract.QuizDBEntry._ID,
                QuizDBContract.QuizDBEntry.COLUMN_NAME_TEST_ID,
                QuizDBContract.QuizDBEntry.COLUMN_NAME_STUDENT,
                QuizDBContract.QuizDBEntry.COLUMN_NAME_SCORE,
        };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                QuizDBContract.QuizDBEntry.COLUMN_NAME_SCORE + " DESC";

        Cursor c = db.query(
                QuizDBContract.QuizDBEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        List<Object[]> ret = new ArrayList<>();
        Object[] row;
        while (c.moveToNext()) {
            row = new Object[3];
            row[0] = c.getInt(1);
            row[1] = c.getString(2);
            row[2] = c.getInt(3);
            ret.add(row);
        }
        return ret;
    }
}
