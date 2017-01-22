package com.ghx.app.lulu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * creat by guo_hx
 */

public class SharedPreferencesUtil {
    // 要做缓存用的
    private static String CONFIG = "config";
    private static SharedPreferences sharedPreferences;

    public static void saveStringData(Context context, String key, String value) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static String getStringData(Context context, String key,
                                       String defValue) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(CONFIG,
                    Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(key, defValue);
    }
}