package com.example.qrgenerator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.MapView;
import com.google.android.material.textfield.TextInputEditText;

public class LocAct extends AppCompatActivity {
Button generate;
Button marker;
TextInputEditText lat;
TextInputEditText lon;

int PLACE_PICKER_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);


        lat = findViewById(R.id.lat);
        marker = findViewById(R.id.marker);
        lon = findViewById(R.id.lon);
        generate = findViewById(R.id.generate);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(lat.getText()) || TextUtils.isEmpty(lon.getText())){
                    Toast.makeText(LocAct.this,"Please chose a location first",Toast.LENGTH_SHORT).show();

                }else {

                    String dataa = "Latitude :"+ lat.getText().toString().trim() + "\n Longitude"+ lon.getText().toString().trim();
                    Intent intent = new Intent(LocAct.this, QrView.class);
                    intent.putExtra("data", dataa);
                    startActivity(intent);
                }
            }
        });

        marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(LocAct.this),PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {

                Place place = PlacePicker.getPlace(data, this);
                StringBuilder stringBuilder = new StringBuilder();
                String latitude = String.valueOf(place.getLatLng().latitude);
                String longitude = String.valueOf(place.getLatLng().longitude);
                lat.setText(latitude);
                lon.setText(longitude);
            }
        }

    }
}