package com.innoria.khanhduong.library.DataStorage.SharedPreferences;

import android.content.Context;
import android.content.*;
/**
 * Created by KhanhDuong on 8/13/2015.
 * Author Khanh Duong
 * Email khanhduong@innoria.com
 * Company www.innoria.com
 */
public class KBaseSharedPreferences{
    public static final String PREFS_NAME = "KBaseSharedPreferences";
    public SharedPreferences settings;
    public SharedPreferences.Editor editor;
    public KBaseSharedPreferences(Context thiss){
        settings = thiss.getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();
    }

    //common string
    public void set(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public String get(String key){
        return settings.getString(key, "");
    }

    //get string
    public void setString(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key){
        return settings.getString(key, "");
    }

    //get int
    public void setInt(String key, int value){
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key){
        return settings.getInt(key, 0);
    }

    //get boolean
    public void setBoolean(String key, boolean value){
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key){
        return settings.getBoolean(key, false);
    }

    //get float
    public void setFloat(String key, float value){
        editor.putFloat(key, value);
        editor.commit();
    }

    public float getFloat(String key){
        return settings.getFloat(key, 0f);
    }

    //get long
    public void setLong(String key, long value){
        editor.putLong(key, value);
        editor.commit();
    }

    public long getLong(String key){
        return settings.getLong(key, 0l);
    }
}
