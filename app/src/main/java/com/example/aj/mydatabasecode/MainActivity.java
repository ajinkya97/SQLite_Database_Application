package com.example.aj.mydatabasecode;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editfname,editlname,editmarks;
    Button btnAddData;
    Button btnviewdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editfname = (EditText)findViewById(R.id.fname);
        editlname=(EditText)findViewById(R.id.lname);
        editmarks=(EditText)findViewById(R.id.marks);
        btnAddData=(Button)findViewById(R.id.add);
        btnviewdata=(Button)findViewById(R.id.ViewData);
        AddData();
        viewAll();
    }

    public void AddData()
    {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      boolean isIns =  myDb.insertData(editfname.getText().toString(),
                                editlname.getText().toString(),
                                editmarks.getText().toString());
                        if(isIns=true)
                        {
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void viewAll()
    {
        btnviewdata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=myDb.getAllData();
                        if(res.getCount()==0)
                        {
                            showMessage("Error","No Data Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext())
                        {
                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer.append("FName :"+res.getString(1)+"\n");
                            buffer.append("LNAME :"+res.getString(2)+"\n");
                            buffer.append("MARKS :"+res.getString(3)+"\n\n");
                        }
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
