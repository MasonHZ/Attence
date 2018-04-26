package com.example.a89370;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 89370 on 2018/4/23.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(false);
    }
}
