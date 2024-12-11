package com.rmartin.cs360_inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.AppCompatActivity;


public class RegistrationDBHandler extends SQLiteOpenHelper {

    // Database constants
    private static final String DB_NAME = "usersdb";
    private static final int DB_VERSION = 1;

    // Table and column constants
    private static final String TABLE_NAME = "employees";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "username";
    private static final String PASSWORD_COL = "password";
    private static final String EMAIL_COL = "email";
    private static final String PHONE_COL = "phone";
    private static final String SUPERVISOR_COL = "supervisor";
    private static final String LOCATION_COL = "location";

    public RegistrationDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT, "
                + PASSWORD_COL + " TEXT, "
                + EMAIL_COL + " TEXT, "
                + PHONE_COL + " TEXT, "
                + SUPERVISOR_COL + " TEXT, "
                + LOCATION_COL + " TEXT)";
        db.execSQL(query);
    }

    public void addNewUser(String username, String password, String email, String phone, String supervisor, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, username);
        values.put(PASSWORD_COL, password);
        values.put(EMAIL_COL, email);
        values.put(PHONE_COL, phone);
        values.put(SUPERVISOR_COL, supervisor);
        values.put(LOCATION_COL, location);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME_COL + " = ? AND " + PASSWORD_COL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        boolean isAuthenticated = cursor.moveToFirst();
        cursor.close();
        db.close();
        return isAuthenticated;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}