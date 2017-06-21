package com.app.a15017569.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class editNote extends Activity {
    public EditText et_title;
    public EditText et_note;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        et_note = (EditText)findViewById(R.id.et_note);
        et_title = (EditText)findViewById(R.id.et_title);
        dbHelper = new DBHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dbHelper.insertNote(et_title.getText().toString(), et_note.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Added Successfully",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "was not added",
                            Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(editNote.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
