package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.example.smartcity.R;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfilActivity extends AppCompatActivity {

    @BindView(R.id.TagsEdit)
    RecyclerView tagRecyclerView;
    private TagAdapter adapter;
    private ArrayList<Tag> tagsEtudiant;
    private ArrayList<Tag> allTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        ButterKnife.bind(this);
        adapter = new TagAdapter();
        tagsEtudiant = new ArrayList<>();
        allTags = new ArrayList<>();

        //todo task get tag Etudiant
        //todo task get all tag

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
}
