package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.DataAccess.ApiClient;
import com.example.smartcity.DataAccess.service.TagService;
import com.example.smartcity.model.TagClasse;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

public class TagDao implements TagDataAccess {

	@Override
	public Response<ArrayList<TagClasse>> GetTags() throws IOException{
		return ApiClient.getInstance().getRetrofit()
				.create(TagService.class)
				.GetTags()
				.execute();
	}
}
