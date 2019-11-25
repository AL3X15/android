package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.model.Annonce;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultatActivity extends AppCompatActivity {

    @BindView(R.id.ListAnnonce)
    public RecyclerView recyclerView;
    private ArrayList<Annonce> annonces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        ButterKnife.bind(this);
        annonces = new ArrayList<>();


        //todo task get annonce resultat


    }

    private class AnnonceViewHolder extends RecyclerView.ViewHolder{
        public TextView annonce;

        public AnnonceViewHolder (@NonNull View itemView){
            super(itemView);
            annonce = itemView.findViewById(R.id.annonce);
        }
    }
    private class AnnonceAdapter extends RecyclerView.Adapter<AnnonceViewHolder>{
        private ArrayList<Annonce> myAnnonces;
        @NonNull
        @Override
        public AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_recherche_element,parent,false);
            AnnonceViewHolder vh = new AnnonceViewHolder(v);
            return vh;
        }
        @Override
        public void onBindViewHolder(@NonNull AnnonceViewHolder holder, int position){
            Annonce annonce = myAnnonces.get(position);
            holder.annonce.setText(annonce.toString());
        }
        @Override
        public int getItemCount(){
            return myAnnonces == null ? 0 : myAnnonces.size();
        }
    }
}
