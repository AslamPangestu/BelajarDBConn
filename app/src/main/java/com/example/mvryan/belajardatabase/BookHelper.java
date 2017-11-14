package com.example.mvryan.belajardatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mvryan on 10/11/17.
 */

public class BookHelper extends SQLiteOpenHelper {

    //deklarasi nama db dan verisnya
    final static String DBNAME = "book.db";
    final static int DBVERSION = 1;

    public BookHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query_create = "CREATE TABLE book_entries(" +
                "_id INTEGER PRIMARY KEY autoincrement,"+
                "title TEXT," +
                "author TEXT)";//buat queri untuk tabel
        sqLiteDatabase.execSQL(query_create);//jalankan query
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query_drop = "DROP TABLE IF EXISTS book_entries";//query utk drop
        sqLiteDatabase.execSQL(query_drop);//jalankan query
        onCreate(sqLiteDatabase);
    }
}
