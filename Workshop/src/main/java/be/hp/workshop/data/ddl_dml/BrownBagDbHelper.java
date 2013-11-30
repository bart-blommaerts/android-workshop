package be.hp.workshop.data.ddl_dml;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bart on 11/20/13.
 */
public class BrownBagDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 21;
    public static final String DATABASE_NAME = "BrownBag.db";

    public BrownBagDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BrownBagContract.SQL_DROP_TABLE);
        db.execSQL(BrownBagContract.SQL_CREATE_TABLE);
        db.execSQL(BrownBagContract.SQL_INSERT_ROWS);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(BrownBagContract.SQL_DROP_TABLE);
        db.execSQL(BrownBagContract.SQL_CREATE_TABLE);
        db.execSQL(BrownBagContract.SQL_INSERT_ROWS);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO implement
    }
}