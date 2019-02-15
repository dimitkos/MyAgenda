package com.example.kosmas.myagenda;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView textView6;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView6 = findViewById(R.id.textView6);
        String s = getIntent().getStringExtra("mymessage");
        db = openOrCreateDatabase("MyAgendaDB",MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("SELECT * FROM MyAgenda WHERE email = '"+s+"';",null);
        cursor.moveToNext();
        String result = cursor.getString(0)+" , "+cursor.getString(1)+" , "+cursor.getString(2)+" , "+cursor.getString(3);
        cursor.close();
        textView6.setText(result);

    }

}
