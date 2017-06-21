package com.app.a15017569.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;


class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "myNote.db";
    public static final String db_table_name = "notes";
    public static final String db_column_title = "title";
    public static final String db_column_notes = "note";
    public static final String db_column_id = "id";

    public DBHelper(Context context){ super(context,database_name,null,1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table notes (id integer primary key, title text,note text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }

    public boolean insertNote(String title, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("note",note);
        db.insert("notes", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * from notes WHERE id="+id,null);
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,database_name);
        return numRows;
    }

    public boolean updateNotes (Integer id, String title, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("note",note);
        db.update("notes", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteNotes(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("notes","id = ?",new String[]{
                Integer.toString(id)
        });
    }

    public ArrayList<String> getAllTitles() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from notes", null);
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex("title")));
            res.moveToNext();
        }
        return array_list;
    }
}
