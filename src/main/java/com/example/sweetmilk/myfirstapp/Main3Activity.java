package com.example.sweetmilk.myfirstapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
DatabaseHelper myDb;
EditText editName,editSurname,editMarks, editId;
Button addButton;
Button viewButton;
Button updateButton;
Button deleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        myDb= new DatabaseHelper(this);
         editName = (EditText) findViewById(R.id.firstNumEditText);
         editSurname = (EditText) findViewById(R.id.firstNumEditText2);
         editMarks = (EditText) findViewById(R.id.firstNumEditText3);
         editId = (EditText) findViewById(R.id.firstNumEditText4);
         addButton = (Button) findViewById(R.id.AddID);
         viewButton = (Button) findViewById(R.id.button2);
         updateButton=(Button) findViewById(R.id.button3);
         deleteButton=(Button) findViewById(R.id.button4);

         AddData();
        viewAll();
        UpdateData();
        DeleteData();

    }
    public void AddData(){
        addButton.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean isInserted =  myDb.insertData(editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString() );
            if (isInserted==true)
                Toast.makeText(Main3Activity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                Toast.makeText(Main3Activity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
            }
                });
    }

    public void  viewAll(){

        viewButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     Cursor res= myDb.getAllData();
                     if(res.getCount()==0) {
                         //show message
                         showMessage("Error","Nothing found");
                         return;
                     }
                     StringBuffer buffer =new StringBuffer();
                     while (res.moveToNext()){
                        buffer.append("Id:"+res.getString(0)+"\n");
                        buffer.append("Name:"+res.getString(1)+"\n");
                        buffer.append("Surname:"+res.getString(2)+"\n");
                        buffer.append("Marks:"+res.getString(3)+"\n\n");

                     }
                     //show all data
                        showMessage("Students",buffer.toString());
                    }
                });

    }

    public void showMessage(String title ,String Message ){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData(){
        updateButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     boolean isUpdate = myDb.updateData(editId.getText().toString(),editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString());
                     if(isUpdate==true)
                         Toast.makeText(Main3Activity.this,"Data Updated",Toast.LENGTH_LONG).show();
                         else
                         Toast.makeText(Main3Activity.this,"Data Not Updated",Toast.LENGTH_LONG).show();

                    }
                });
    }

    public void DeleteData(){
        deleteButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRow = myDb.deleteData(editId.getText().toString());
                        if(deletedRow>0)
                            Toast.makeText(Main3Activity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main3Activity.this,"Data Not Deleted",Toast.LENGTH_LONG).show();

                    }
                });

    }

}
