package com.example.smartcity.DataAccess.service;

import com.example.smartcity.model.PageResultFaq;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FaqService {

	@GET("/Faq/page/")
	Call<PageResultFaq> getFaq(@Path("ligne")int ligne);
}
