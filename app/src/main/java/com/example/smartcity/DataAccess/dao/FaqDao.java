package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.DataAccess.ApiClient;
import com.example.smartcity.DataAccess.service.FaqService;
import com.example.smartcity.model.PageResultFaq;

import java.io.IOException;

import retrofit2.Response;

public class FaqDao implements FaqDataAccess {

	@Override
	public Response<PageResultFaq> getFaq(int page) throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(FaqService.class)
				.getFaq(page)
				.execute();
	}

}
