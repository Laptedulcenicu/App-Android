package com.example.sweetmilk.myfirstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBtn =(Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText FirstNumEditText = (EditText) findViewById(R.id.FirstNumEditText);
                EditText SecondNumEditText = (EditText) findViewById(R.id.SecondNumEditText);
                TextView ResultTextView = (TextView) findViewById(R.id.RsultTextView);
                int num1= Integer.parseInt(FirstNumEditText.getText().toString());
                int num2= Integer.parseInt(SecondNumEditText.getText().toString());
                int result =num1+num2;
                ResultTextView.setText(result+"");
            }
        });

        Button minusBtn =(Button) findViewById(R.id.minusBtn);
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText FirstNumEditText = (EditText) findViewById(R.id.FirstNumEditText);
                EditText SecondNumEditText = (EditText) findViewById(R.id.SecondNumEditText);
                TextView ResultTextView = (TextView) findViewById(R.id.RsultTextView);
                int num1= Integer.parseInt(FirstNumEditText.getText().toString());
                int num2= Integer.parseInt(SecondNumEditText.getText().toString());
                int result =num1-num2;
                ResultTextView.setText(result+"");
            }
        });

        Button MultipleBtn =(Button) findViewById(R.id.MultipleBtn);
        MultipleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText FirstNumEditText = (EditText) findViewById(R.id.FirstNumEditText);
                EditText SecondNumEditText = (EditText) findViewById(R.id.SecondNumEditText);
                TextView ResultTextView = (TextView) findViewById(R.id.RsultTextView);
                int num1= Integer.parseInt(FirstNumEditText.getText().toString());
                int num2= Integer.parseInt(SecondNumEditText.getText().toString());
                int result =num1*num2;
                ResultTextView.setText(result+"");
            }
        });

        Button secondActivity =(Button) findViewById(R.id.secondActivity);
        secondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),Main2Activity.class);

startActivity(startIntent);
            }
        });

        Button googlebtn =(Button) findViewById(R.id.googleButon);
        googlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String google="http://www.google.com";
                Uri webaddress =Uri.parse(google);
                Intent gotoGoole = new Intent(Intent.ACTION_VIEW, webaddress);
                if (gotoGoole.resolveActivity(getPackageManager()) !=null){
            startActivity(gotoGoole);
                }
            }
        });


    }

}
