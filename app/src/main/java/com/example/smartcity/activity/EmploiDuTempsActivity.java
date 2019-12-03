package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity.DataAccess.AnnonceDao;
import com.example.smartcity.DataAccess.AnnonceDataAccess;
import com.example.smartcity.MyApplication;
import com.example.smartcity.R;
import com.example.smartcity.model.Adresse;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Etudiant;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmploiDuTempsActivity extends AppCompatActivity {

    @BindView(R.id.ListAnnonce)
    public RecyclerView recyclerView;
    private AnnonceAdapter adapter;
    private Etudiant etudiant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploi_du_temps);
        ButterKnife.bind(this);

        etudiant = ((MyApplication)this.getApplication()).getEtudiant();

        adapter = new AnnonceAdapter();

        LoadAnnonce loadAnnonce = new LoadAnnonce();
        loadAnnonce.execute(etudiant);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private class AnnonceViewHolder extends RecyclerView.ViewHolder{

        public TextView horraire;
        public TextView activite;
        public Button localise;

        public AnnonceViewHolder(@NonNull View itemView,OnItemSelectedListener listener){
            super(itemView);
            horraire = itemView.findViewById(R.id.horraire);
            activite = itemView.findViewById(R.id.descElemHorraire);
            localise = itemView.findViewById(R.id.localise);
            localise.setOnClickListener(e->{
                int currentPosition = getAdapterPosition();
                listener.onItemSelected(currentPosition);
            });
        }
    }

    private class AnnonceAdapter extends RecyclerView.Adapter<AnnonceViewHolder>{
        private ArrayList<Annonce> myAnnonces;

        @NonNull
        @Override
        public AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.horraire_element,parent,false);
            AnnonceViewHolder vh = new AnnonceViewHolder(v,position -> {
                Annonce annonceSelect = myAnnonces.get(position);
                Uri gmmIntentUri = Uri.parse(getString(R.string.uri_map)+annonceSelect.getAddress().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage(getString(R.string.map_intent));
                startActivity(mapIntent);

            });
            return vh;
        }
        @Override
        public void onBindViewHolder(@NonNull AnnonceViewHolder holder, int position){
            Annonce annonce = myAnnonces.get(position);
            holder.activite.setText(annonce.getPoste());
            holder.horraire.setText(annonce.getDateDebut().getWeekYear() +getString(R.string.tiret)+ annonce.getDateFin().getWeekYear());
            holder.localise.setText(annonce.getAddress().toString());
        }
        @Override
        public int getItemCount(){
            return myAnnonces == null ? 0 : myAnnonces.size();
        }

        public void setAnnonces(ArrayList<Annonce> myAnnonces){
            this.myAnnonces = myAnnonces;
            notifyDataSetChanged();
        }
    }
    private class LoadAnnonce extends AsyncTask<Etudiant,Void,ArrayList<Annonce>>{
        @Override
        public ArrayList<Annonce> doInBackground(Etudiant... etudiants){
            AnnonceDataAccess annonceDataAccess = new AnnonceDao();
            return annonceDataAccess.getAnnonceEtudiant(etudiants[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Annonce> annonces) {
            adapter.setAnnonces(annonces);
        }
    }
}
