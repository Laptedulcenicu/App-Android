package com.example.sweetmilk.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Scanner_Activity extends AppCompatActivity {

 

    private static final int MY_PERMISSION_STORAGE = 1;
    private static final int RESULT_LOAD_IMAGE = 0;
    TextView textView;
    Button button;
    ImageView imageView;

    BarcodeDetector detector;

    UrlString testString = new UrlString();

    TextView answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSION_STORAGE);

        }

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.pickImgBtn);
        imageView = (ImageView) findViewById(R.id.imageView);


        Button goToBtn = (Button) findViewById(R.id.GoogleIt);
        answer = (TextView)findViewById(R.id.textAnswer);

        detector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.QR_CODE ) // maybe change later to other format, 128 wasnt working properly
                .build();


        if(!detector.isOperational()){
            textView.setText("Could not set up the detector!");
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });



        goToBtn.setOnClickListener(   new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vk = "http://"+testString.getUrl();
                Uri webadress = Uri.parse(vk);

                Intent gotovk = new Intent(Intent.ACTION_VIEW, webadress);

                if (gotovk.resolveActivity(getPackageManager()) != null  ){
                    startActivity(gotovk);
                }



            }
        });
        Button getBtn = (Button) findViewById(R.id.getBtn);
        getBtn.setOnClickListener(   new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                performCheck();


            }
        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && null != data){
//            Uri selectedImage = data.getData();
//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();
//
//            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
//            imageView.setImageBitmap(bitmap);
//
//            //load data
//            processData(bitmap);
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap (bitmap);

            processData(bitmap);

        }
    }
       

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_IMAGE_CAPTURE:{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "CAMERA granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "CAMERA is not granted!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

        }
    }

    private void processData(Bitmap myBitmap){
        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);


        Barcode thiscode = barcodes.valueAt(0);
        textView.setText(thiscode.rawValue);
        testString.setUrl(thiscode.rawValue);

    }



    private void performCheck(){
        final StringBuilder id = new StringBuilder(testString.getUrl());

        Call<Stuff> call= MainActivity.apiInterface.performStuffCheck(id);
        call.enqueue(new Callback<Stuff>() {
            @Override
            public void onResponse(Call<Stuff> call, Response<Stuff> response) {
                if(response.body().getResponseS().equals("Ok"))
                {
             
                             Toast.makeText(getApplicationContext(),"Stuff is at it's place",Toast.LENGTH_SHORT).show();
                             answer.setText(response.body().getResponseS());
             
                }
                else if(response.body().getResponseS().equals("field"))
                {
                    Toast.makeText(getApplicationContext(),"Stuff is not at it's place",Toast.LENGTH_SHORT).show();
                    answer.setText(response.body().getResponseS());
          
                }
            }

            @Override
            public void onFailure(Call<Stuff> call, Throwable t) {
                MainActivity.prefConfig.displayToast("Message was not sent.");


            }
        });
    }


}
