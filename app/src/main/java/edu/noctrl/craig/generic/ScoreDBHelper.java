package edu.noctrl.craig.generic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import edu.noctrl.craig.generic.ScoreDBContract;

/**
 * Created by bacraig on 5/25/2016.
 */
public class ScoreDBHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ScoreDBContract.ScoreDBEntry.TABLE_NAME + " (" +
                    ScoreDBContract.ScoreDBEntry._ID + " INTEGER PRIMARY KEY," +
                    ScoreDBContract.ScoreDBEntry.COLUMN_NAME_GAME_ID + TEXT_TYPE + COMMA_SEP +
                    ScoreDBContract.ScoreDBEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    ScoreDBContract.ScoreDBEntry.COLUMN_NAME_SCORE + INT_TYPE + COMMA_SEP +
                    ScoreDBContract.ScoreDBEntry.COLUMN_NAME_DATETIME + TEXT_TYPE +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ScoreDBContract.ScoreDBEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TestDatabase.db";

    public ScoreDBHelper(Context context) {
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
    public void addRecord(String name, int score, String dt){
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ScoreDBContract.ScoreDBEntry.COLUMN_NAME_GAME_ID, idCounter++);
        values.put(ScoreDBContract.ScoreDBEntry.COLUMN_NAME_NAME, name);
        values.put(ScoreDBContract.ScoreDBEntry.COLUMN_NAME_SCORE, score);
        values.put(ScoreDBContract.ScoreDBEntry.COLUMN_NAME_DATETIME, dt);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(
                ScoreDBContract.ScoreDBEntry.TABLE_NAME,
                null,
                values);

    }

    public List<Object[]> getRecords(){
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                ScoreDBContract.ScoreDBEntry._ID,
                ScoreDBContract.ScoreDBEntry.COLUMN_NAME_GAME_ID,
                ScoreDBContract.ScoreDBEntry.COLUMN_NAME_NAME,
                ScoreDBContract.ScoreDBEntry.COLUMN_NAME_SCORE,
                ScoreDBContract.ScoreDBEntry.COLUMN_NAME_DATETIME,
        };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                ScoreDBContract.ScoreDBEntry.COLUMN_NAME_SCORE + " DESC";

        Cursor c = db.query(
                ScoreDBContract.ScoreDBEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        List<Object[]> ret = new ArrayList<>();
        Object[] row;
        while (c.moveToNext()) {
            row = new Object[4];
            row[0] = c.getInt(1);
            row[1] = c.getString(2);
            row[2] = c.getInt(3);
            row[3] = c.getString(4);
            ret.add(row);
        }
        return ret;
    }
}
