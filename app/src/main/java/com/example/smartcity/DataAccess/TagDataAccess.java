package com.example.smartcity.DataAccess;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;

public interface TagDataAccess {
    public ArrayList<Tag> getAllTag();
    public ArrayList<Tag> getTagsAnnonce(Annonce annonce);
    public ArrayList<Tag> getTagsEtudiant(Etudiant etudiant);
}
