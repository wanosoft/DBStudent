package com.wanosoft.dbstudent;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{


    public EditText id, name, course, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = (EditText) findViewById(R.id.id);
        name = (EditText) findViewById(R.id.name);
        course = (EditText) findViewById(R.id.course);
        year = (EditText) findViewById(R.id.year);
    }

    public void add(View v){
        AdminSqliteHelper admin = new AdminSqliteHelper(this,"DB_Students",null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String n1 = name.getText().toString();
        String cur = course.getText().toString();
        String id_ = id.getText().toString();
        String yea = year.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("id",id_);
        registro.put("name",n1);
        registro.put("course",cur);
        registro.put("year",yea);

        db.insert("student", null, registro);
        db.close();

        id.setText("");
        name.setText("");
        course.setText("");
        year.setText("");
        Toast.makeText(this, "Success add", Toast.LENGTH_SHORT).show();
    }
    public void delete(View v){
        AdminSqliteHelper admin = new AdminSqliteHelper(this,"DB_Students",null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String id_ = id.getText().toString();

        int cant = db.delete("student", "id="+id_,null);
        id.setText("");

        if(cant==1){
            Toast.makeText(this, "Succes Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Delete Exception", Toast.LENGTH_SHORT).show();
        }
    }

    public void edit(View v){
        AdminSqliteHelper admin = new AdminSqliteHelper(this,"DB_Students",null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String n1 = name.getText().toString();
        String cur = course.getText().toString();
        String id_ = id.getText().toString();
        String yea = year.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("id",id_);
        registro.put("name",n1);
        registro.put("course",cur);
        registro.put("year",yea);

        int cant = db.update("student", registro, "id="+id_,null);

        if(cant==1){
            Toast.makeText(this, "Succes Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Delete Exception", Toast.LENGTH_SHORT).show();
        }
    }

    public void query(View v){
        AdminSqliteHelper admin = new AdminSqliteHelper(this,"DB_Students",null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id_ = id.getText().toString();
        Cursor fila = db.rawQuery("Select name, course, year from student where id="+id_,null);
        if(fila.moveToFirst()){
            name.setText(fila.getString(0));
            course.setText(fila.getString(1));
            year.setText(fila.getString(2));
        } else {
            Toast.makeText(this, "Query Exeption", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void rows(View v){
        //Llamar activity Layouts
    }
}
