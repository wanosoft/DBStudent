package com.wanosoft.dbstudent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class DinamicTable extends AppCompatActivity {

    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinamic_table);

        tableLayout = (TableLayout) findViewById(R.id.tableid1);
        TableRow tr_head = new TableRow(this);
        tr_head.setId(123);
        tr_head.setBackgroundColor(Color.BLACK);
        tr_head.setLayoutParams(new ActionBar.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        //Creación de textViews columnas
        TextView textID= new TextView(this);
        textID.setId(124);
        textID.setText("ID");
        textID.setTextColor(Color.WHITE);
        textID.setPadding(5,5,5,5);
        tr_head.addView(textID);

        TextView textName= new TextView(this);
        textName.setId(125);
        textName.setText("NAME");
        textName.setTextColor(Color.WHITE);
        textName.setPadding(5,5,5,5);
        tr_head.addView(textName);

        tableLayout.addView(tr_head, new TableLayout.LayoutParams
                (TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

        int count = 0;
        AdminSqliteHelper admin = new AdminSqliteHelper(this,"DB_Students",null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor fila = db.rawQuery("Select * from student",null);

        String id_= "";
        String name = "";

        if(fila.moveToFirst()){
            while (fila.isAfterLast()== false){

                id_ = fila.getString(fila.getColumnIndex("id"));
                name = fila.getString(fila.getColumnIndex("name"));

                TableRow row = new TableRow(this);
                if(count%2!=0) row.setBackgroundColor(Color.GRAY);
                else row.setBackgroundColor(Color.DKGRAY);
                row.setId(150+count);
                row.setLayoutParams(new ActionBar.LayoutParams(TableLayout.LayoutParams.FILL_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));


                //Adding views to row
                TextView labelID = new TextView(this);
                labelID.setId(200+count);
                labelID.setText(id_);
                labelID.setPadding(2,0,5,0);
                labelID.setTextColor(Color.WHITE);
                row.addView(labelID);

                TextView labelName = new TextView(this);
                labelName.setId(400+count);
                labelName.setText(name);
                labelName.setPadding(2,0,5,0);
                labelName.setTextColor(Color.WHITE);
                row.addView(labelName);

                tableLayout.addView(row, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.FILL_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                count++;
                fila.moveToNext();
            }
        } else {
            Toast.makeText(this, "Row Exception", Toast.LENGTH_SHORT).show();
        }
    }
}
