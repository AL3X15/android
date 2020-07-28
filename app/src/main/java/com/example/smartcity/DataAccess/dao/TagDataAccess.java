package com.example.smartcity.DataAccess.dao;

import com.example.smartcity.model.TagClasse;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

public interface TagDataAccess {

	Response<ArrayList<TagClasse>> GetTags() throws IOException;
    /*
    public ArrayList<Tag> getAllTag(AccessToken accessToken)throws Exception;
    public ArrayList<Tag> getTagsAnnonce(AccessToken accessToken, Annonce annonce)throws Exception;
    public ArrayList<Tag> getTagsEtudiant(AccessToken accessToken, UserEtudiant userEtudiant)throws Exception;
     */
}
