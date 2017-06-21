package com.app.a15017569.notes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class addNote extends AppCompatActivity {
    EditText et_title;
    EditText et_note;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initialise activity items
        et_note = (EditText)findViewById(R.id.et_note);
        et_title = (EditText) findViewById(R.id.et_title);

        //initialise Database
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        Cursor res = dbHelper.getData(id);
        res.moveToFirst();

        //getting items from Database using received id
        String title = res.getString(res.getColumnIndex(DBHelper.db_column_title));
        String note = res.getString(res.getColumnIndex(DBHelper.db_column_notes));

        //displaying items in the Edit text for edit.
        et_title.setText(title);
        et_note.setText(note);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Saving and updating items
                Intent intents = getIntent();
                int id_to_search = intents.getIntExtra("id",0);
                dbHelper.updateNotes(id_to_search,et_title.getText().toString(), et_note.getText().toString());
                Intent intent = new Intent(addNote.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

}
