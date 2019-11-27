package com.example.smartcity.DataAccess;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;

public class AnnonceDao implements AnnonceDataAccess {
    @Override
    public ArrayList<Annonce> getAnnonceEtudiant(Etudiant etudiant) {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<Annonce> getResultatSerch(ArrayList<Tag> tags) {
        return new ArrayList<>();
    }
}
