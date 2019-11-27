package com.example.smartcity.DataAccess;

import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;

public interface AnnonceDataAccess {
    public ArrayList<Annonce> getAnnonceEtudiant(Etudiant etudiant);
    public ArrayList<Annonce> getResultatSerch(ArrayList<Tag> tags);
}
