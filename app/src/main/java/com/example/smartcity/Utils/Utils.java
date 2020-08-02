package com.example.smartcity.Utils;

import com.example.smartcity.R;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Response;

public class Utils {

    private static String DEFAULT_STRING = "";
    private static int DEFAULT_YEAR = 2000;
    private static int DEFAULT_MONTH = 1;
    private static int DEFAULT_DAY = 1;
    private static int DEFAULT_HOURS = 0;
    private static int DEFAULT_MIN = 0;
/*
    public static void editSharedPreference(Activity activity, Preference preference){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.file_user), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(activity.getString(R.string.user_saved),preference.getEmail());
        if (preference.getAccessToken() == null){
            editor.putString(activity.getString(R.string.accessToken),DEFAULT_STRING);
            editor.putInt(activity.getString(R.string.year),DEFAULT_YEAR);
            editor.putInt(activity.getString(R.string.month), DEFAULT_MONTH);
            editor.putInt(activity.getString(R.string.dayOfMonth),DEFAULT_DAY);
            editor.putInt(activity.getString(R.string.hourOfDay),DEFAULT_HOURS);
            editor.putInt(activity.getString(R.string.minute),DEFAULT_MIN);
        }else{
            editor.putString(activity.getString(R.string.accessToken),preference.getAccessToken().getAccessToken());
            editor.putInt(activity.getString(R.string.year),preference.getAccessToken().getDateExpiration().get(Calendar.YEAR));
            editor.putInt(activity.getString(R.string.month), preference.getAccessToken().getDateExpiration().get(Calendar.MONTH));
            editor.putInt(activity.getString(R.string.dayOfMonth),preference.getAccessToken().getDateExpiration().get(Calendar.DAY_OF_MONTH));
            editor.putInt(activity.getString(R.string.hourOfDay),preference.getAccessToken().getDateExpiration().get(Calendar.HOUR_OF_DAY));
            editor.putInt(activity.getString(R.string.minute),preference.getAccessToken().getDateExpiration().get(Calendar.MINUTE));
        }
        editor.commit();
    }
    public static Preference getSharedPreference(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(activity.getString(R.string.file_user), Context.MODE_PRIVATE);
        String defaultValueMail = activity.getString(R.string.mail_default);
        String emailUser = sharedPreferences.getString(activity.getString(R.string.user_saved),defaultValueMail);
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken(sharedPreferences.getString(activity.getString(R.string.accessToken),DEFAULT_STRING));
        accessToken.setDateExpiration(new GregorianCalendar
                (
                        sharedPreferences.getInt(activity.getString(R.string.year),DEFAULT_YEAR),
                        sharedPreferences.getInt(activity.getString(R.string.month),DEFAULT_MONTH),
                        sharedPreferences.getInt(activity.getString(R.string.dayOfMonth),DEFAULT_DAY),
                        sharedPreferences.getInt(activity.getString(R.string.hourOfDay),DEFAULT_HOURS),
                        sharedPreferences.getInt(activity.getString(R.string.minute),DEFAULT_MIN)
                )
        );
        return new Preference(emailUser,accessToken);
    }
*/
    public static Date stringToDate(String date){
        if(!date.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) return null;
        int jour;
        int mois;
        int année;
        jour = Integer.parseInt(date.substring(0, 2));
        mois = Integer.parseInt(date.substring(3, 5));
        année = Integer.parseInt(date.substring(6, 10));
        if(mois > 12 || mois <1)return null;
        if(jour >31 || jour < 1)return null;
        if(jour == 31 && (mois == 4 || mois == 6 || mois == 9 ||mois ==11)) return null;
        if(mois == 2 && année % 4 !=0 && jour > 28 )return null;
        return new GregorianCalendar(année, mois-1, jour).getTime();
    }

    public static  int msgErreur(Response response){
        switch (response.code()){
            case 404 :
                return R.string.notFound;
            case 500 :
                return R.string.serverError;
            case 401 :
                return R.string.errorAuthorization;
            case 403 :
                return R.string.forbiden;
            case 400 :
                try{
                    switch (Integer.parseInt(response.errorBody().string().replaceAll("\"",""))){
                        case 1 :
                            return R.string.mailAlredyUsed;
                        case 2 :
                            return R.string.positiveLineNumber;
                        case 3 :
                            return R.string.localityError;
                        case 4 :
                            return R.string.mailPasswordError;
                        case 5 :
                            return R.string.postulationExist;
                        case 6 :
                            return R.string.tagLess;
                        case 7 :
                            return R.string.sexError;
                        case 8 :
                            return R.string.ageError;
                        case 9 :
                            return R.string.AnnoncePast;
                        case 10 :
                            return R.string.dateError;
                        case 11 :
                            return R.string.premuim;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return R.string.internetError;
    }
}
