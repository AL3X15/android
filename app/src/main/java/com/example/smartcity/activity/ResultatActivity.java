package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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

    @BindView(R.id.ListAnnonce)
    public RecyclerView recyclerView;
    private AnnonceAdapter adapter;
    private Tag[] tags;

    private GregorianCalendar dateFin;
    private GregorianCalendar dateDebut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);
        ButterKnife.bind(this);

        tags = (Tag[]) getIntent().getSerializableExtra("tags");

        String dateDebut = getIntent().getStringExtra("dateDebut");
        this.dateDebut = new GregorianCalendar(
                Integer.parseInt(dateDebut.substring(4,7)),
                Integer.parseInt(dateDebut.substring(2,3)),
                Integer.parseInt(dateDebut.substring(0,1))
        );
        String dateFin = getIntent().getStringExtra("dateFin");
        this.dateFin = new GregorianCalendar(
                Integer.parseInt(dateFin.substring(4,7)),
                Integer.parseInt(dateFin.substring(2,3)),
                Integer.parseInt(dateFin.substring(0,1))
        );
        LoadAnnonce loadAnnonce = new LoadAnnonce();
        loadAnnonce.execute(tags);
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
        public void setMyAnnonces(ArrayList<Annonce> annonces){
            this.myAnnonces = annonces;
            notifyDataSetChanged();
        }
    }

    private class LoadAnnonce extends AsyncTask<Tag,Void,ArrayList<Annonce>> {
        @Override
        protected ArrayList<Annonce> doInBackground(Tag... tags) {
            AnnonceDataAccess annonceDataAccess = new AnnonceDao();
            ArrayList<Tag> tagsList = new ArrayList<>();
            for(int i = 0; i< tags.length;i++ ) tagsList.add(tags[i]);
            return annonceDataAccess.getResultatSerch(dateDebut,dateFin,tagsList);
        }

        @Override
        protected void onPostExecute(ArrayList<Annonce> annonces) {
            adapter.setMyAnnonces(annonces);
        }
    }
}
