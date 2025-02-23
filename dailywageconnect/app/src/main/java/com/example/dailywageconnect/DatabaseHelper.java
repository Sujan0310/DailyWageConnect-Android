package com.example.dailywageconnect.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserDB";
    private static final int DATABASE_VERSION = 2;

    // Users Table
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_ROLE = "role";

    // Jobs Table
    private static final String TABLE_JOBS = "jobs";
    private static final String COLUMN_JOB_ID = "job_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_WAGE = "wage";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_ROLE + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);

        // Create jobs table
        String CREATE_JOBS_TABLE = "CREATE TABLE " + TABLE_JOBS + " (" +
                COLUMN_JOB_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_WAGE + " TEXT)";
        db.execSQL(CREATE_JOBS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOBS);
        onCreate(db);
    }

    // Insert user role after registration
    public void insertUser(String email, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_ROLE, role);
        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    // Get user role by email
    public String getUserRole(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_ROLE}, COLUMN_EMAIL + "=?",
                new String[]{email}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String role = cursor.getString(0);
            cursor.close();
            return role;
        }
        return null;  // No role found
    }

    // Insert a new job posting
    public void insertJob(String title, String description, String wage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_WAGE, wage);
        db.insert(TABLE_JOBS, null, values);
        db.close();
    }

    // Retrieve all job postings
    public Cursor getAllJobs() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_JOBS, null);
    }
}
