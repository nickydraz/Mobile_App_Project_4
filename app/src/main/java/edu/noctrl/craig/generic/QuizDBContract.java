package com.example.bacraig.myapplication;

import android.provider.BaseColumns;

/**
 * Created by bacraig on 5/25/2016.
 */
public final class QuizDBContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public QuizDBContract() {}

    /* Inner class that defines the table contents */
    public static abstract class QuizDBEntry implements BaseColumns {
        public static final String TABLE_NAME = "testscores";
        public static final String COLUMN_NAME_TEST_ID = "testid";
        public static final String COLUMN_NAME_STUDENT = "student";
        public static final String COLUMN_NAME_SCORE = "score";
    }
}
