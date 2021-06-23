package com.example.grinhits.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.grinhits.models.ProfileModel;
import com.google.gson.Gson;

public class SharedPreferenceUtils {

    private static SharedPreferenceUtils sharedPreferenceUtils;
    private static SharedPreferences sharedPreferences=null;
    private static SharedPreferences.Editor  editor =null;

    private SharedPreferenceUtils(Context context)
    {
        sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public static SharedPreferenceUtils getInstance(Context context) {
        if (sharedPreferenceUtils == null) {

            sharedPreferenceUtils = new SharedPreferenceUtils(context);
        }
        return sharedPreferenceUtils;
    }

    public void isLogin(String login,boolean status)
    {
        editor.putBoolean(login,status);
        editor.commit();
    }

    public boolean isLogin(String login)
    {
        return sharedPreferences.getBoolean(login,false);
    }

    public void isEditButton(String edit,boolean status)
    {
        editor.putBoolean(edit,status);
        editor.commit();
    }

    public boolean isEditButton(String edit)
    {
        return sharedPreferences.getBoolean(edit,false);
    }

    public void setUserId(String key,int id)
    {
        editor.putInt(key, id);
        editor.commit();
    }

    public int getUserId(String key)
    {
      return  sharedPreferences.getInt(key,-1);
    }

    public void setProfileModel(String key , ProfileModel profileModel)
    {
        Gson gson = new Gson();
        String json = gson.toJson(profileModel);
        editor.putString(key, json);
        editor.commit();
    }

    public ProfileModel getProfileModel(String key)
    {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, "");
        ProfileModel obj = gson.fromJson(json, ProfileModel.class);
        return obj;
    }

}
