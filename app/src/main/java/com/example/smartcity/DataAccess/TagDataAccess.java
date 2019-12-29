package com.example.smartcity.DataAccess;

import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.UserEtudiant;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;

public interface TagDataAccess {
    public ArrayList<Tag> getAllTag(AccessToken accessToken)throws Exception;
    public ArrayList<Tag> getTagsAnnonce(AccessToken accessToken, Annonce annonce)throws Exception;
    public ArrayList<Tag> getTagsEtudiant(AccessToken accessToken, UserEtudiant userEtudiant)throws Exception;
}
