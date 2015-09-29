package com.lsyiverson.xingtian;

import android.app.Application;

import com.lsyiverson.xingtian.utils.PropertiesUtil;

public class App extends Application {
    private static final String TAG = App.class.getName();

    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        PropertiesUtil.init(this);
    }
}
