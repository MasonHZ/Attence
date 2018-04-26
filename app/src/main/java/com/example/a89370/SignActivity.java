package com.example.a89370;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.a89370.util.BDLocationUtils;


/**
 *
 * 公司总部坐标   114.101689,22.554394  海拔 71.68
 *
 *
 */
public class SignActivity extends AppCompatActivity {

    private TextView btn_getLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        btn_getLocation = (TextView) findViewById(R.id.btn_getLocation);

        btn_getLocation.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                BDLocationUtils bdLocationUtils = new BDLocationUtils(SignActivity.this);
                bdLocationUtils.doLocation();
                bdLocationUtils.mLocationClient.start();
            }
        });

    }
}
