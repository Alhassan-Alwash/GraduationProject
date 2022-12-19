package com.example.qrgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSession;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class txtAct extends AppCompatActivity {

    TextInputEditText input;
    Button generate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txt);

        generate = findViewById(R.id.generate);
        input = findViewById(R.id.input);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(input.getText().toString().trim())){
                    Toast.makeText(txtAct.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }else{

                    String data = "Notes: "+input.getText().toString().trim();
                    Intent intent = new Intent(txtAct.this, QrView.class);
                    intent.putExtra("data", data);
                    startActivity(intent);

                }

            }
        });
    }
}