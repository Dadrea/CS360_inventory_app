package com.rmartin.cs360_inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import com.rmartin.cs360_inventory.InventoryItem;

public class InventoryDBHandler extends SQLiteOpenHelper {

    // Database constants
    private static final String DB_NAME = "inventorydb";
    private static final int DB_VERSION = 1;

    // Table and column constants
    private static final String TABLE_NAME = "inventory";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "itemname";
    private static final String COUNT_COL = "itemcount";
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
                + DESCRIPTION_COL + " TEXT);";
        db.execSQL(query);
    }

    public boolean doesItemExist(String itemName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID_COL}, NAME_COL + " = ?", new String[]{itemName}, null, null, null);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exists;
    }

    public void addNewItem(String itemname, Integer itemcount, String itemdescription) {
        if (!doesItemExist(itemname)) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NAME_COL, itemname);
            values.put(COUNT_COL, itemcount);
            values.put(DESCRIPTION_COL, itemdescription);
            db.insert(TABLE_NAME, null, values);
            db.close();
        }
    }

    public void updateItem(String itemName, int newCount, String newDescription) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COUNT_COL, newCount);
        values.put(DESCRIPTION_COL, newDescription);

        db.update(TABLE_NAME, values, NAME_COL + " = ?", new String[]{itemName});
        db.close();
    }

    public void deleteItem(String itemName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, NAME_COL + " = ?", new String[]{itemName});
        db.close();
    }

    public InventoryItem getItemByName(String itemName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, NAME_COL + " = ?", new String[]{itemName}, null, null, null);

        InventoryItem item = null;
        if (cursor != null && cursor.moveToFirst()) {
            // Get column indices
            int nameIndex = cursor.getColumnIndex(NAME_COL);
            int countIndex = cursor.getColumnIndex(COUNT_COL);
            int descriptionIndex = cursor.getColumnIndex(DESCRIPTION_COL);

            if (nameIndex != -1 && countIndex != -1 && descriptionIndex != -1) {
                String name = cursor.getString(nameIndex);
                int count = cursor.getInt(countIndex);
                String description = cursor.getString(descriptionIndex);

                // Create InventoryItem object
                item = new InventoryItem(name, count, description);
            }
            cursor.close();
        }
        db.close();
        return item;
    }

    public List<InventoryItem> getAllItems() {
        List<InventoryItem> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                // Get column indices
                int nameIndex = cursor.getColumnIndex(NAME_COL);
                int countIndex = cursor.getColumnIndex(COUNT_COL);
                int descriptionIndex = cursor.getColumnIndex(DESCRIPTION_COL);

                // Check if column indices are valid (>= 0)
                if (nameIndex != -1 && countIndex != -1 && descriptionIndex != -1) {
                    String name = cursor.getString(nameIndex);
                    int count = cursor.getInt(countIndex);
                    String description = cursor.getString(descriptionIndex);

                    InventoryItem item = new InventoryItem(name, count, description);
                    itemList.add(item);
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return itemList;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}