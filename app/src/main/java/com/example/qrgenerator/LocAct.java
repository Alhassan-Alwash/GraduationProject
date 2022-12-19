package com.example.qrgenerator;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qrgenerator.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputEditText;

public class LocAct extends AppCompatActivity implements OnMapReadyCallback {
Button generate;
TextInputEditText lat;
TextInputEditText lon;
Marker oldMarker = null;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);

        lat = findViewById(R.id.lat);
        lon = findViewById(R.id.lon);
        generate = findViewById(R.id.generate);

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(lat.getText()) || TextUtils.isEmpty(lon.getText())){
                    Toast.makeText(LocAct.this,"Please chose a location first",Toast.LENGTH_SHORT).show();

                }else {

                    String data = "Latitude: "+ lat.getText().toString().trim() + "\n Longitude: "+ lon.getText().toString().trim();
                    Intent intent = new Intent(LocAct.this, QrView.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                }
            }
        });

    /*    marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocAct.this, MapsActivity.class);
                startActivityForResult(intent, 0);
//                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
//                try {
//                    startActivityForResult(builder.build(LocAct.this),PLACE_PICKER_REQUEST);
//                } catch (GooglePlayServicesRepairableException e) {
//                    e.printStackTrace();
//                } catch (GooglePlayServicesNotAvailableException e) {
//                    e.printStackTrace();
//                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
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

    }*/

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
}

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (oldMarker != null){
                    oldMarker.remove();
                }
                oldMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
                String longitude = String.valueOf(latLng.longitude);
                String latitude = String.valueOf(latLng.latitude);
                lat.setText(latitude);
                lon.setText(longitude);
            }
        });

//        Add a marker in Sydney and move the camera
//        LatLng baghdad = new LatLng(33, 44);
//        mMap.addMarker(new MarkerOptions().position(baghdad).title("مدينة بغداد"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(baghdad, 15));
    }


}