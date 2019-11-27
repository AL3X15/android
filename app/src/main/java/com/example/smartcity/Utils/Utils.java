package com.example.smartcity.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.example.smartcity.R;
import com.example.smartcity.model.Preference;

public class Utils {


    public static void editSharedPreference(Activity activity, Preference preference){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.file_user), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(activity.getString(R.string.user_saved),preference.getEmail());
        editor.commit();
    }
    public static Preference getSharedPreference(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.file_user),Context.MODE_PRIVATE);
        String defaultValueMail = activity.getString(R.string.mail_default);
        String emailUser = sharedPreferences.getString(activity.getString(R.string.user_saved),defaultValueMail);
        return new Preference(emailUser);
    }
}
