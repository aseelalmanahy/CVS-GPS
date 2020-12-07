/*
****************************************************************************************************
Name: Aseel Almanahy

Project To: CSV

Purpose: This app extracts the Latitude and Longitude of a certain position.
           Based on the requirements of the given question by Ossama Mikhail,
          stated "Simple Android app can read GPS and populate read values into 2 text fields".
          Assuming the 2 values are Latitude and Longitude, both saved in a separated file as
          demonstrated in the code.

          - As a feature, the results can be shown on the screen as well as saved separately in the
            appropriate files.

File: In order to see the files...
       Device File Explorer -> data -> data -> com.example.cvsgps -> files.
       You will see 2 files: 1- Latitude.txt 2- Longitude.txt
       By clicking you can their Open, Save as.., or Delete, the results.

****************************************************************************************************
*/


package com.example.cvsgps;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    Button btnGetLoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGetLoc = (Button) findViewById(R.id.btnGetLoc);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        btnGetLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CVSTracker g = new CVSTracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "Latitude: " + lat + "\nLongitude : " + lon, Toast.LENGTH_LONG).show();
                }
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("Latitude.txt",  Context.MODE_PRIVATE));
                    OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(getApplicationContext().openFileOutput("Longitude.txt",  Context.MODE_PRIVATE));
                    outputStreamWriter.write(String.valueOf(l.getLatitude()));
                    outputStreamWriter2.write(String.valueOf(l.getLongitude()));
                    outputStreamWriter.close();
                    outputStreamWriter2.close();
                } catch (IOException e) {
                    Log.e("Exception", "File write failed: " + e.toString());
                }
            }

        });
    }
}