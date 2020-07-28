package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.model.PageResultFaq;

import java.io.IOException;

import retrofit2.Response;

public interface FaqDataAccess {

	Response<PageResultFaq> getFaq(int page) throws IOException;

	//public ArrayList<Faq> getAllFaq(AccessToken accessToken)throws Exception;
}
