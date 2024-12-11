package com.rmartin.cs360_inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;

import androidx.appcompat.app.AppCompatActivity;


public class InventoryDBHandler extends SQLiteOpenHelper {

    // Database constants
    private static final String DB_NAME = "inventorydb";
    private static final int DB_VERSION = 1;

    // Table and column constants
    private static final String TABLE_NAME = "inventory";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "itemname";
    private static final String COUNT_COL = "itemcount";
    private static final String IMAGE_COL = "itemimage";
    private static final String DESCRIPTION_COL = "itemdescription";


    public InventoryDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + COUNT_COL + " INTEGER, "
                + DESCRIPTION_COL + " TEXT, "
                + IMAGE_COL + " IMAGE, ";
        db.execSQL(query);
    }

    public void addNewUser(String itemname, Integer itemcount, String itemdescription, Image itemimage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, itemname);
        values.put(COUNT_COL, itemcount);
        values.put(DESCRIPTION_COL, itemdescription);
        values.put(IMAGE_COL, itemimage);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}