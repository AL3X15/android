package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.smartcity.DataAccess.TagDao;
import com.example.smartcity.DataAccess.TagDataAccess;
import com.example.smartcity.Exception.ApiAccessException;
import com.example.smartcity.Exception.NoTag;
import com.example.smartcity.Exception.NothingFoundException;
import com.example.smartcity.MyApplication;
import com.example.smartcity.R;
import com.example.smartcity.model.UserEtudiant;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RechercheAnnonceActivity extends AppCompatActivity {
    @BindView(R.id.TagRecherche)
    public RecyclerView tagRecyclerView;
    private TagRechercheAdapter adapter;
    private ArrayList<Tag> tagsEtudiant;
    private UserEtudiant userEtudiant;

    @BindView(R.id.dateDebut)
    public EditText dateDebut;
    @BindView(R.id.dateFin)
    public EditText dateFin;
    @BindView(R.id.search)
    public Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_annonce);
        ButterKnife.bind(this);

        search.setEnabled(false);

        userEtudiant = ((MyApplication)this.getApplication()).getInfoConnection().getUserEtudiant();

        adapter = new TagRechercheAdapter();
        tagsEtudiant = new ArrayList<>();

        LoadTagEtudiant loadTagEtudiant = new LoadTagEtudiant();
        loadTagEtudiant.execute(userEtudiant);

        tagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tagRecyclerView.setAdapter(adapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean dateValide = (dateDebut.getText().toString().length() == 8) && (dateFin.getText().toString().length() == 8);
                if(dateValide) {
                    int jour, mois, année;
                    jour = Integer.parseInt(dateDebut.getText().toString().substring(0, 1));
                    mois = Integer.parseInt(dateDebut.getText().toString().substring(2, 3));
                    année = Integer.parseInt(dateDebut.getText().toString().substring(4, 7));
                    Date dateDebut = new GregorianCalendar(année, mois, jour).getTime();
                    jour = Integer.parseInt(dateFin.getText().toString().substring(0, 1));
                    mois = Integer.parseInt(dateFin.getText().toString().substring(2, 3));
                    année = Integer.parseInt(dateFin.getText().toString().substring(4, 7));
                    Date dateFin = new GregorianCalendar(année, mois, jour).getTime();
                    boolean FormulaireValide = dateDebut.before(dateFin);
                    if(FormulaireValide){
                        Intent intent = new Intent(RechercheAnnonceActivity.this, ResultatActivity.class);
                        intent.putParcelableArrayListExtra(getString(R.string.tags_transfer),tagsEtudiant);
                        intent.putExtra(getString(R.string.date_start),dateDebut);
                        intent.putExtra(getString(R.string.date_end),dateFin);
                        startActivity(intent);
                    }
                }else {
                    dateDebut.setBackgroundColor(Color.parseColor("#FF0000"));
                    dateFin.setBackgroundColor(Color.parseColor("#FF0000"));
                }

            }
        });
    }
    public void errorMessage(String error){
        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
    }
    public void updateTag(Tag tag){
        if(tagsEtudiant.contains(tag)) tagsEtudiant.remove(tag);
        else tagsEtudiant.add(tag);
    }

    private class TagRechercheViewHolder extends RecyclerView.ViewHolder{
        public Switch tag;

        public TagRechercheViewHolder(@NonNull View itemView,OnItemSelectedListener listener){
            super(itemView);
            tag = itemView.findViewById(R.id.switch1);
            tag.setOnClickListener(e ->{
                int currentPosition = getAdapterPosition();
                listener.onItemSelected(currentPosition);
            });
        }

    }
    private class TagRechercheAdapter extends RecyclerView.Adapter<TagRechercheViewHolder>{
        private ArrayList<Tag> myTags;

        @NonNull
        @Override
        public TagRechercheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_recherche_element,parent,false);
            TagRechercheViewHolder vh = new TagRechercheViewHolder(v,position -> {
                Tag tagSelect = myTags.get(position);
                updateTag(tagSelect);
            });
            return vh;
        }
        @Override
        public void onBindViewHolder(@NonNull TagRechercheViewHolder holder, int position){
            Tag tag = myTags.get(position);
            holder.tag.setText(tag.getNom());
            if(tagsEtudiant.contains(tag))holder.tag.setChecked(true);
        }
        @Override
        public int getItemCount(){
            return myTags == null ? 0 : myTags.size();
        }

        public void setTags(ArrayList<Tag> myTags){
            this.myTags = myTags;
            notifyDataSetChanged();
        }
    }
    private class LoadTagEtudiant extends AsyncTask<UserEtudiant,Void,ArrayList<Tag>> {
        @Override
        protected ArrayList<Tag> doInBackground(UserEtudiant... userEtudiants) {
            TagDataAccess tagDataAccess = new TagDao();
            try{
                return tagDataAccess.getTagsEtudiant(((MyApplication) getApplication()).getInfoConnection().getAccessToken(), userEtudiants[0]);
            }catch (ApiAccessException e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        errorMessage(getString(R.string.accessApiError));
                    }
                });
            }catch (NoTag e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        errorMessage(getString(R.string.tag_errors));
                    }
                });
            }catch (NothingFoundException e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        errorMessage(getString(R.string.recherche_error));
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
        protected void onPostExecute(ArrayList<Tag> tags) {
            if(tags != null) {
                tagsEtudiant = tags;
            }
            LoadAllTags loadAllTags = new LoadAllTags();
            loadAllTags.execute();
        }

    }
    private class LoadAllTags extends AsyncTask<Void,Void,ArrayList<Tag>>{
        @Override
        protected ArrayList<Tag> doInBackground(Void... voids) {
            TagDataAccess tagDataAccess = new TagDao();
            try {
                return tagDataAccess.getAllTag(((MyApplication) getApplication()).getInfoConnection().getAccessToken());
            }catch (ApiAccessException e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        errorMessage(getString(R.string.accessApiError));
                    }
                });
            }catch (Exception e) {
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
        protected void onPostExecute(ArrayList<Tag> tags){
            adapter.setTags(tags);
            search.setEnabled(true);
        }
    }
}
