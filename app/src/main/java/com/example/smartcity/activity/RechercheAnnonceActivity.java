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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.smartcity.DataAccess.TagDao;
import com.example.smartcity.DataAccess.TagDataAccess;
import com.example.smartcity.MyApplication;
import com.example.smartcity.R;
import com.example.smartcity.model.Adresse;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Faq;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RechercheAnnonceActivity extends AppCompatActivity {
    @BindView(R.id.TagRecherche)
    public RecyclerView tagRecyclerView;
    private TagRechercheAdapter adapter;
    private ArrayList<Tag> tagsEtudiant;
    private Etudiant etudiant;

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

        etudiant = ((MyApplication)this.getApplication()).getEtudiant();

        adapter = new TagRechercheAdapter();
        tagsEtudiant = new ArrayList<>();

        LoadTagEtudiant loadTagEtudiant = new LoadTagEtudiant();
        loadTagEtudiant.execute(etudiant);

        tagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tagRecyclerView.setAdapter(adapter);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RechercheAnnonceActivity.this, ResultatActivity.class);
                intent.putParcelableArrayListExtra(getString(R.string.tags_transfer),tagsEtudiant);
                startActivity(intent);
            }
        });
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
    private class LoadTagEtudiant extends AsyncTask<Etudiant,Void,ArrayList<Tag>> {
        @Override
        protected ArrayList<Tag> doInBackground(Etudiant... etudiants) {
            TagDataAccess tagDataAccess = new TagDao();
            return tagDataAccess.getTagsEtudiant(etudiants[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Tag> tags) {
            tagsEtudiant = tags;
            LoadAllTags loadAllTags = new LoadAllTags();
            loadAllTags.execute();
        }
    }
    private class LoadAllTags extends AsyncTask<Void,Void,ArrayList<Tag>>{
        @Override
        protected ArrayList<Tag> doInBackground(Void... voids) {
            TagDataAccess tagDataAccess = new TagDao();
            return tagDataAccess.getAllTag();
        }
        @Override
        protected void onPostExecute(ArrayList<Tag> tags){
            adapter.setTags(tags);
            search.setEnabled(true);
        }
    }
}
