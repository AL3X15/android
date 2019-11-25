package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity.R;
import com.example.smartcity.model.Adresse;
import com.example.smartcity.model.Annonce;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmploiDuTempsActivity extends AppCompatActivity {

    @BindView(R.id.ListAnnonce)
    public RecyclerView recyclerView;
    private AnnonceAdapter adapter;
    private ArrayList<Annonce> annonces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploi_du_temps);
        ButterKnife.bind(this);
        adapter = new AnnonceAdapter();
        annonces = new ArrayList<>();
        //todo task get annonces

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
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+annonceSelect.getAddress().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            });
            return vh;
        }
        @Override
        public void onBindViewHolder(@NonNull AnnonceViewHolder holder, int position){
            Annonce annonce = myAnnonces.get(position);
            holder.activite.setText(annonce.getPoste());
            holder.horraire.setText(annonce.getDateDebut().getWeekYear() +" - "+ annonce.getDateFin().getWeekYear());
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

}
