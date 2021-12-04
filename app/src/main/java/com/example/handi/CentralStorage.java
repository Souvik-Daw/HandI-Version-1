package com.example.handi;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CentralStorage {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;//help to write in sp
    Context myContext;

    public CentralStorage(Context myContext)
    {
        this.myContext=myContext;
        preferences = PreferenceManager.getDefaultSharedPreferences(myContext);
        editor=preferences.edit();
    }
    public void setData(String key,String value)
    {
        editor.putString(key,value);
        editor.commit();
    }

    public String getData(String key)
    {
        return preferences.getString(key,"");
    }

    public void clearData(){
        editor.clear();
        editor.commit();
    }

    public void removeData(String key)
    {
        editor.remove(key);
        editor.commit();
    }
}
