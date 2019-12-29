package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.DataAccess.AnnonceDao;
import com.example.smartcity.DataAccess.AnnonceDataAccess;
import com.example.smartcity.Exception.AnnonceDontExist;
import com.example.smartcity.Exception.ApiAccessException;
import com.example.smartcity.MyApplication;
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

        tags = getIntent().getParcelableArrayListExtra(getString(R.string.tags_transfer));
        LoadAnnonce loadAnnonce = new LoadAnnonce();
        loadAnnonce.execute(tags);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void errorMessage(String error){
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
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
                intent.putExtra(getString(R.string.annonce),annonceSelect);
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
            try {
                return annonceDataAccess.getResultatSerch(((MyApplication) getApplication()).getInfoConnection().getAccessToken(),new GregorianCalendar(),new GregorianCalendar(),tags[0]);
            }
            catch (AnnonceDontExist e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        errorMessage(getString(R.string.annonce_error));
                    }
                });
            }
            catch (ApiAccessException e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        errorMessage(getString(R.string.accessApiError));
                    }
                });
            }
            catch (Exception e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        errorMessage(getString(R.string.connection_error));
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Annonce> annonces) {
            adapter.setMyAnnonces(annonces);
        }
    }
}
