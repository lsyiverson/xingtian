package com.lsyiverson.xingtian.utils;

import android.content.Context;

import com.github.fernandodev.androidproperties.lib.AssetsProperties;

public class PropertiesUtil extends AssetsProperties {

    private static PropertiesUtil instance;

    private PropertiesUtil(Context context) {
        super(context);
    }

    public static void init(Context context) {
        instance = new PropertiesUtil(context);
    }

    public static String getString(String key) {
        return instance.getString(key, "");
    }

    public static int getInt(String key) {
        return instance.getInt(key, 0);
    }
}
