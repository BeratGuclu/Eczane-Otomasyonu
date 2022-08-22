package com.example.eczaneotomasyonu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_name = "productLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_name = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_name = "product_name";
    private static final String COLUMN_skt = "product_skt";
    private static final String COLUMN_adet = "product_adet";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_name, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_name +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_name + " TEXT, " +
                COLUMN_skt + " TEXT, " +
                COLUMN_adet + " INTEGER);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_name);
        onCreate(db);
    }

    void addproduct(String name, String skt, int adet){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_name, name);
        cv.put(COLUMN_skt, skt);
        cv.put(COLUMN_adet, adet);
        long result = db.insert(TABLE_name,null, cv);
        if(result == -1){
            Toast.makeText(context, "Veri Eklenemedi", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Veri Başarıyla Eklendi!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_name;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String name, String skt, String adet){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_name, name);
        cv.put(COLUMN_skt, skt);
        cv.put(COLUMN_adet, adet);

        long result = db.update(TABLE_name, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Veri Güncellenemedi", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Veri Başarıyla Güncellendi!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_name, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Veri Silinemedi.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Veri Başarıyla Silindi.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_name);
    }

}