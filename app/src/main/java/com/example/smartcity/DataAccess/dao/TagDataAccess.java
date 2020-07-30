package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.model.TagClasse;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

public interface TagDataAccess {

	Response<ArrayList<TagClasse>> GetTags() throws IOException;
}
