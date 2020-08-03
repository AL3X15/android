package com.example.smartcity.Utils;

import com.example.smartcity.R;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Response;

public class Utils {

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
