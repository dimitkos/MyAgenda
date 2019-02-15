package com.example.kosmas.myagenda;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;//mail
    SQLiteDatabase db;
    EditText editText2;//phone
    EditText editText3;//address
    EditText editText4;//name


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3= findViewById(R.id.editText3);
        editText4= findViewById(R.id.editText4);

        db = openOrCreateDatabase("MyAgendaDB",MODE_PRIVATE,null);

        db.execSQL("CREATE TABLE IF NOT EXISTS `MyAgenda` (" +
                "`name` TEXT," +
                "`email` TEXT ," +
                "`address` TEXT," +
                "`phone` TEXT," +
                "PRIMARY KEY(`email`)" +
                ");");

        db.execSQL("INSERT OR IGNORE INTO 'MyAgenda' VALUES ('Dimitris','mitsos@yahoo.gr','Votsi 48','2108533444');");
        db.execSQL("INSERT OR IGNORE INTO 'MyAgenda' VALUES ('Marianna','marianna@gmail.com','Thivon 260','2103677897');");
        db.execSQL("INSERT OR IGNORE INTO 'MyAgenda' VALUES ('Niki','niki@yahoo.gr','Deksamenis 57','2104567456');");
    }
        //dokimastiko koumpi gia na doume oti dimiourgithike h vash kai emfanizontai kanonika otan kanouem tiw allages
    public void selectall(View view){
        StringBuffer buffer = new StringBuffer();
        Cursor cursor = db.rawQuery("SELECT * FROM MyAgenda ;",null);
        if (cursor.getCount()==0)
            Toast.makeText(this,"No records found...",Toast.LENGTH_LONG).show();
        else {
            while (cursor.moveToNext()){
                buffer.append("Name: " + cursor.getString(0)+"\n");
                buffer.append("Email: " + cursor.getString(1)+"\n");
                buffer.append("Address: " + cursor.getString(2)+"\n");
                buffer.append("Phone: " + cursor.getString(3)+"\n");
                buffer.append("--------------------------\n");
            }
            String s = buffer.toString();
            showmessage("Records",s);
        }
        cursor.close();
    }

    public void showmessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.show();
    }

    public void delete (View view) {

        try {
            db.execSQL("DELETE FROM MyAgenda WHERE email = '" + editText.getText().toString() + "';");
            Toast.makeText(this, "deleted", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            showmessage("Error", e.toString());
        }
    }

    public void update(View view){

        db.execSQL("UPDATE MyAgenda SET phone = '"+editText2.getText().toString()+ "' WHERE  email = '" + editText.getText().toString() + "';");
        Toast.makeText(this,"updated",Toast.LENGTH_LONG).show();
    }

    public void insert(View view)
    {
        db.execSQL("INSERT INTO 'MyAgenda' VALUES ('"+ editText4.getText().toString() +"'," +
                "'"+ editText.getText().toString() +"'," +
                "'"+ editText3.getText().toString() +"'," +
                "'"+ editText2.getText().toString() +"')");

        Toast.makeText(this,"Added",Toast.LENGTH_LONG).show();
    }

    public void searchmail(View view)
    {
        Intent intent = new Intent(this,Main2Activity.class);
        String input = editText.getText().toString();
        intent.putExtra("mymessage",input);
        startActivity(intent);
    }
}
