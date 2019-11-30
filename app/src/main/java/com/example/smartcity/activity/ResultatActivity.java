package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcity.DataAccess.AnnonceDao;
import com.example.smartcity.DataAccess.AnnonceDataAccess;
import com.example.smartcity.R;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultatActivity extends AppCompatActivity {

    @BindView(R.id.AnnoncesResultat)
    public RecyclerView recyclerView;
    private AnnonceAdapter adapter;
    private ArrayList<Tag> tags;

    private GregorianCalendar dateFin;
    private GregorianCalendar dateDebut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        ButterKnife.bind(this);
        adapter = new AnnonceAdapter();

        tags = getIntent().getParcelableArrayListExtra("tags");
        LoadAnnonce loadAnnonce = new LoadAnnonce();
        loadAnnonce.execute(tags);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private class AnnonceViewHolder extends RecyclerView.ViewHolder{
        public TextView poste;

        public AnnonceViewHolder (@NonNull View itemView, OnItemSelectedListener listener){
            super(itemView);
            poste = itemView.findViewById(R.id.annonce);
            poste.setOnClickListener(e->{
                int cur = getAdapterPosition();
                listener.onItemSelected(cur);
            });
        }
    }

    private class AnnonceAdapter extends RecyclerView.Adapter<AnnonceViewHolder>{
        private ArrayList<Annonce> myAnnonces;

        @NonNull
        @Override
        public AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.annonce_element,parent,false);
            AnnonceViewHolder vh = new AnnonceViewHolder(v,position -> {
                Annonce annonceSelect = myAnnonces.get(position);
                Intent intent = new Intent(ResultatActivity.this, DetailAnnonceActivity.class);
                intent.putExtra("annonce",annonceSelect);
                startActivity(intent);
            });
            return vh;
        }
        @Override
        public void onBindViewHolder(@NonNull AnnonceViewHolder holder, int position){
            Annonce annonce = myAnnonces.get(position);
            holder.poste.setText(annonce.getPoste());
        }
        @Override
        public int getItemCount(){
            return myAnnonces == null ? 0 : myAnnonces.size();
        }
        public void setMyAnnonces(ArrayList<Annonce> annonces){
            this.myAnnonces = annonces;
            notifyDataSetChanged();
            notifyDataSetChanged();
        }
    }

    private class LoadAnnonce extends AsyncTask<ArrayList<Tag>,Void,ArrayList<Annonce>> {
        @Override
        protected ArrayList<Annonce> doInBackground(ArrayList<Tag>... tags) {
            AnnonceDataAccess annonceDataAccess = new AnnonceDao();
            return annonceDataAccess.getResultatSerch(new GregorianCalendar(),new GregorianCalendar(),tags[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Annonce> annonces) {
            annonces.forEach(a->{
                Log.i("annonce", a.toString());
            });
            adapter.setMyAnnonces(annonces);
        }
    }
}
