package com.example.a89370;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.a89370.view.SettingItemView;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initUpdate();

    }

    private void initUpdate() {

        final SettingItemView siv_update = (SettingItemView) findViewById(R.id.siv_update);

        siv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ischeck = siv_update.isCheck();
                siv_update.setCheck(!ischeck);
            }
        });

    }
}
