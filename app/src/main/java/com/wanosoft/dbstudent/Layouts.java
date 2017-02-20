package com.wanosoft.dbstudent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Layouts extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layouts);

        textView = (TextView) findViewById(R.id.textView);

        AdminSqliteHelper admin = new AdminSqliteHelper(this,"DB_Students",null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor fila = db.rawQuery("Select * from student",null);

        String textViewString="";

        if(fila.moveToFirst()){
            while (fila.isAfterLast()== false){
                textViewString += " " + fila.getString(fila.getColumnIndex("id"));
                textViewString += " " + fila.getString(fila.getColumnIndex("name"));
                textViewString += " " + fila.getString(fila.getColumnIndex("course"));
                textViewString += " " + fila.getString(fila.getColumnIndex("year"))+"\n";
                fila.moveToNext();
            }
        } else {
            Toast.makeText(this, "Row Exception", Toast.LENGTH_SHORT).show();
        }
        textView.setText(textViewString);
    }
}
