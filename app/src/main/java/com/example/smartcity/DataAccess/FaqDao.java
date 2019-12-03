package com.example.smartcity.DataAccess;

import com.example.smartcity.model.Faq;

import java.util.ArrayList;

public class FaqDao implements FaqDataAccess{
    private ArrayList faqs;

    public FaqDao(){
        faqs = new ArrayList();
        faqs.add(new Faq("Blabla","lqsjdlkhfkqh"));
    }
    public ArrayList<Faq> getAllFaq(){
        return faqs;
    }
}
