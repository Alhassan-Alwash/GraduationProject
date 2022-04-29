package com.example.qrgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class WebAct extends AppCompatActivity {

    Button generate;
    TextInputEditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        input = findViewById(R.id.website);
        generate= findViewById(R.id.generate);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(input.getText().toString().trim())){
                    Toast.makeText(WebAct.this,"Please fill all the fields",Toast.LENGTH_SHORT).show();
                }else{

                String data = input.getText().toString().trim();
                Intent intent = new Intent(WebAct.this,QrView.class);
                intent.putExtra("data",data);
                startActivity(intent);
            }

            }
        });
    }
}