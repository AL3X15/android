package com.example.smartcity.DataAccess;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;

public class TagDao implements TagDataAccess {

    public TagDao(){}

    public ArrayList<Tag> getAllTag(){
        return new ArrayList<>();
    }
    public ArrayList<Tag> getTagsAnnonce(Annonce annonce){
        return new ArrayList<>();
    }
    public ArrayList<Tag> getTagsEtudiant(Etudiant etudiant){
        return new ArrayList<>();
    }

}
