package com.example.smartcity.DataAccess.service;

import com.example.smartcity.model.Faq;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FaqService {

	@GET("/Faq/page/")
	Call<ArrayList<Faq>> getAllFaq(@Path("id")int id);
}
