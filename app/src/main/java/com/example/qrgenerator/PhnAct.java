package com.example.qrgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class PhnAct extends AppCompatActivity {

    TextInputEditText phone;
    Button generate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phn);

        phone=findViewById(R.id.phone);
        generate=findViewById(R.id.generate);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(phone.getText().toString().trim())){
                    Toast.makeText(PhnAct.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();

                }else{

                String data = phone.getText().toString().trim();
                Intent intent =new Intent(PhnAct.this,QrView.class);
                intent.putExtra("data",data);
                startActivity(intent);

            }}
        });
    }
}