package com.example.qrgenerator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.MultiFormatWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class QrView extends AppCompatActivity {

    Button back;
    Button save;
    ImageView output;
    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_view);

        back = findViewById(R.id.back);
        save = findViewById(R.id.save);
        ActivityCompat.requestPermissions(QrView.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(QrView.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        output = findViewById(R.id.output);

        String data = getIntent().getStringExtra("data");

        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            //initialize bit matrix
            BitMatrix matrix= writer.encode(data, BarcodeFormat.QR_CODE, 350 ,350);
            //initialize barcode encoder
            BarcodeEncoder encoder = new BarcodeEncoder();
            //initialize bitmap
            Bitmap bitmap =encoder.createBitmap(matrix);
            //set bitmap on imageview
            output.setImageBitmap(bitmap);
            //initialize input manager
            InputMethodManager manager = (InputMethodManager)  getSystemService(Context.INPUT_METHOD_SERVICE);
            //Hide soft keyboard
            manager.hideSoftInputFromWindow(output.getApplicationWindowToken(),0);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToGallary();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void saveToGallary() {

        BitmapDrawable bitmapDrawable = (BitmapDrawable) output.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        FileOutputStream outputStream =null;

        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File dir = new File(file.getAbsolutePath()+"/QrCode");

        boolean b = dir.mkdir();
        Log.v("dir", ""+b);
        String filename = String.format("%d.png",System.currentTimeMillis());
        File outfile = new File(dir,filename);
        try {
            outputStream= new FileOutputStream(outfile);

        }catch (FileNotFoundException e)
        {e.printStackTrace(); }
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        MediaScannerConnection.scanFile(this, new String[] { outfile.getPath() }, new String[] { "image/png" }, null);
        Toast.makeText(getApplicationContext(),"Qr Code Saved ",Toast.LENGTH_SHORT).show();

        try {
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}