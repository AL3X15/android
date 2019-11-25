package com.example.smartcity.DataAccess;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Etudiant;

import java.util.ArrayList;

public class AnnonceDao implements AnnonceDataAccess {
    @Override
    public ArrayList<Annonce> getAnnonceEtudiant(Etudiant etudiant) {
        return new ArrayList<>();
    }
}
