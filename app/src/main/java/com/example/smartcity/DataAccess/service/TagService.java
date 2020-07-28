package com.example.smartcity.DataAccess.service;

import com.example.smartcity.model.TagClasse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TagService {

	@GET("/Tag")
	Call<ArrayList<TagClasse>> GetTags();
}
