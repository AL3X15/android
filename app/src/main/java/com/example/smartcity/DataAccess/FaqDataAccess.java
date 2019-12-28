package com.example.smartcity.DataAccess;

import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.Faq;

import java.util.ArrayList;

public interface FaqDataAccess {
    public ArrayList<Faq> getAllFaq(AccessToken accessToken)throws Exception;
}
