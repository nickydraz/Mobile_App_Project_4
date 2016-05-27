package edu.noctrl.craig.generic;

import android.provider.BaseColumns;

/**
 * Created by bacraig on 5/25/2016.
 */
public final class ScoreDBContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public ScoreDBContract() {}

    /* Inner class that defines the table contents */
    public static abstract class ScoreDBEntry implements BaseColumns {
        public static final String TABLE_NAME = "localscores";
        public static final String COLUMN_NAME_GAME_ID = "gameid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SCORE = "score";
        public static final String COLUMN_NAME_DATETIME = "datetime";

    }
}
