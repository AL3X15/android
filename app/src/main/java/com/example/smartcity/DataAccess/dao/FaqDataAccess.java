package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.model.Faq;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

public interface FaqDataAccess {

	Response<ArrayList<Faq>> getAllFaq(int id) throws IOException;

	//public ArrayList<Faq> getAllFaq(AccessToken accessToken)throws Exception;
}
