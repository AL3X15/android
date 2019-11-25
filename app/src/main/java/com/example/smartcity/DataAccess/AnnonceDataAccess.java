package com.example.smartcity.DataAccess;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Etudiant;

import java.util.ArrayList;

public interface AnnonceDataAccess {
    public ArrayList<Annonce> getAnnonceEtudiant(Etudiant etudiant);
}
