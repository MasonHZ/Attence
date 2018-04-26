package com.example.a89370;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class SignActivity extends AppCompatActivity {

    private TextView tv_gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        final double a = 114.101627;
        final double b = 22.554402;
        tv_gps = (TextView) findViewById(R.id.tv_gps);

        Log.e("=========>", "" + a + b);
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.e("---------->", "begin");
                double j = location.getLatitude();
                double w = location.getLongitude();
                Log.e("getLatitude===>", "" + j);
                Log.e("getLongitude===>", "" + w);

                tv_gps.setText("getLatitude:" + j + ",getLongitude:" + w + Thread.currentThread().getName());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });


    }
}
