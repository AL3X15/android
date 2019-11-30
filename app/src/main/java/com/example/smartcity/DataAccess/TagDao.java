package com.example.smartcity.DataAccess;

import com.example.smartcity.Exception.TagDontExistAnymore;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;

public class TagDao implements TagDataAccess {

    private ArrayList<Tag> tags;

    public TagDao(){
        tags = new ArrayList<>();
        tags.add(new Tag("Tag1","Tag 1"));
        tags.add(new Tag("Tag2","Tag 2"));
    }

    public ArrayList<Tag> getAllTag(){
        return tags;
    }
    public ArrayList<Tag> getTagsAnnonce(Annonce annonce){
        return tags;
    }
    public ArrayList<Tag> getTagsEtudiant(Etudiant etudiant){
        return etudiant.getTags();
    }


}
