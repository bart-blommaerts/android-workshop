package be.hp.workshop.data.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import be.hp.workshop.data.ddl_dml.BrownBagContract;
import be.hp.workshop.data.ddl_dml.BrownBagDbHelper;
import be.hp.workshop.data.model.BrownBag;
import be.hp.workshop.data.model.BrownBagItems;

/**
 * Created by bart on 11/20/13.
 */
public class BrownBagService {

    public void insert(Context context, BrownBag brownBag) {
        BrownBagDbHelper mDbHelper = new BrownBagDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(BrownBagContract.COLUMN_NAME_TITLE, brownBag.getTitle());
        values.put(BrownBagContract.COLUMN_NAME_CONTENT, brownBag.getContent());

        db.insert(
                BrownBagContract.TABLE_NAME,
                null,
                values);
    }

    public void findAll(Context context) {
        BrownBagDbHelper mDbHelper = new BrownBagDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        Cursor cursor = db.query(
                BrownBagContract.TABLE_NAME,                     // The table to query
                new String[] {                                   // The columns to return
                        BrownBagContract._ID,
                        BrownBagContract.COLUMN_NAME_TITLE,
                        BrownBagContract.COLUMN_NAME_CONTENT},
                null,                                            // The columns for the WHERE clause
                null,                                            // The values for the WHERE clause
                null,                                            // don't group the rows
                null,                                            // don't filter by row groups
                null                                             // The sort order
        );

        fillBrownBagItems(cursor);
    }

    private void fillBrownBagItems(Cursor cursor) {
        BrownBagItems brownBagItems = new BrownBagItems();

        while (cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndexOrThrow(BrownBagContract._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(BrownBagContract.COLUMN_NAME_TITLE));
            String content = cursor.getString(cursor.getColumnIndexOrThrow(BrownBagContract.COLUMN_NAME_CONTENT));

            BrownBag brownBag = new BrownBag(id, title, content);

            BrownBagItems.addItem(brownBag);
        }
    }

    /*
    public void removeBrownBags(Context context) {
        BrownBagDbHelper mDbHelper = new BrownBagDbHelper(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(BrownBagContract.TABLE_NAME, null, null);
    }
    */
}
