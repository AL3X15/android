package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.smartcity.DataAccess.TagDao;
import com.example.smartcity.DataAccess.TagDataAccess;
import com.example.smartcity.R;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfilActivity extends AppCompatActivity {

    @BindView(R.id.TagsEdit)
    RecyclerView tagRecyclerView;
    private TagAdapter adapter;
    private ArrayList<Tag> tagsEtudiant;
    private Etudiant etudiant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        ButterKnife.bind(this);
        adapter = new TagAdapter();
        tagsEtudiant = new ArrayList<>();


        LoadTagEtudiant loadTagEtudiant = new LoadTagEtudiant();
        loadTagEtudiant.execute(etudiant);

        tagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tagRecyclerView.setAdapter(adapter);
    }
    public void updateTag(Tag tag){
        if(tagsEtudiant.contains(tag)) tagsEtudiant.remove(tag);
        else tagsEtudiant.add(tag);
    }


    private class TagViewHolder extends RecyclerView.ViewHolder{
        public Switch tag;

        public TagViewHolder(@NonNull View itemView, OnItemSelectedListener listener){
            super(itemView);
            tag = itemView.findViewById(R.id.switch1);
            tag.setOnClickListener(e ->{
                int currentPosition = getAdapterPosition();
                listener.onItemSelected(currentPosition);
            });
        }

    }
    private class TagAdapter extends RecyclerView.Adapter<TagViewHolder>{
        private ArrayList<Tag> myTags;

        @NonNull
        @Override
        public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_recherche_element,parent,false);
            TagViewHolder vh = new TagViewHolder(v,position -> {
                Tag tagSelect = myTags.get(position);
                updateTag(tagSelect);
            });
            return vh;
        }
        @Override
        public void onBindViewHolder(@NonNull TagViewHolder holder, int position){
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

    private class LoadTagEtudiant extends AsyncTask<Etudiant,Void,ArrayList<Tag>>{
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
        }
    }
}
