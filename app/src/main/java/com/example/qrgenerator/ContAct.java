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

public class ContAct extends AppCompatActivity {

    TextInputEditText name;
    TextInputEditText phone;
    TextInputEditText company;
    TextInputEditText title;
    TextInputEditText location;
    TextInputEditText email;
    TextInputEditText website;
    TextInputEditText notes;

    Button generate;
    ImageView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cont);

        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        company=findViewById(R.id.company);
        title=findViewById(R.id.title);
        location=findViewById(R.id.location);
        email=findViewById(R.id.email);
        website=findViewById(R.id.website);
        notes=findViewById(R.id.notes);

        generate=findViewById(R.id.generate);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(name.getText().toString().trim()) || TextUtils.isEmpty(phone.getText().toString().trim())
                || TextUtils.isEmpty(company.getText().toString().trim()) || TextUtils.isEmpty(title.getText().toString().trim())
                || TextUtils.isEmpty(location.getText().toString().trim()) || TextUtils.isEmpty(email.getText().toString().trim())
                ) {
                    Toast.makeText(ContAct.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();

                }else {
                String text =
                                "Name :" + name.getText().toString().trim() +
                                "\nPhoneNumber :" + phone.getText().toString().trim() +
                                "\nCompanyName :" + company.getText().toString().trim() +
                                "\nJobTitle :" + title.getText().toString().trim() +
                                "\nAddress" + location.getText().toString().trim() +
                                "\nE-mail :" + email.getText().toString().trim() +
                                "\nWebSite :" + website.getText().toString().trim() +
                                "\nnotes" + notes.getText().toString().trim() ;

                Intent intent = new Intent(ContAct.this , QrView.class);
                intent.putExtra("data",text);
                startActivity(intent);

            }}
        });

    }
}