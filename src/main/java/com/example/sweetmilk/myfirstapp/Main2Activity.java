package com.example.sweetmilk.myfirstapp;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button back =(Button) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(startIntent);
            }
        });
        Button b1 =(Button) findViewById(R.id.oo);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),Main3Activity.class);
                startIntent.putExtra("com.example.sweetmilk.myfirstapp.SOMETHING","Hello Nicu");
                startActivity(startIntent);
            }
        });

        if (getIntent().hasExtra("com.example.sweetmilk.myfirstapp.SOMETHING")){
            TextView tv= (TextView) findViewById(R.id.textView);
            String text = getIntent().getExtras().getString("com.example.sweetmilk.myfirstapp.SOMETHING");
            tv.setText(text);
        }
    }
}
