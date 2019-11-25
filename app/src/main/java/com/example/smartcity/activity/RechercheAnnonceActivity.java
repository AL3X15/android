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

public class RechercheAnnonceActivity extends AppCompatActivity {
    @BindView(R.id.TagRecherche)
    RecyclerView tagRecyclerView;
    private TagRechercheAdapter adapter;
    private ArrayList<Tag> listTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_annonce);
        ButterKnife.bind(this);

        listTags = new ArrayList<Tag>(); //list des tags de l'étudiant

        adapter = new TagRechercheAdapter();
        ArrayList<Tag> tags = new ArrayList<>(); //list de tout les tags
        tags.add(new Tag("test1",""));
        tags.add(new Tag("test2",""));
        tags.add(new Tag("test3",""));
        tags.add(new Tag("test4",""));
        adapter.setTags(tags);
        tagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tagRecyclerView.setAdapter(adapter);

    }
    public void updateTag(Tag tag){
        if(listTags.contains(tag))listTags.remove(tag);
        else listTags.add(tag);
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
            if(listTags.contains(tag))holder.tag.setChecked(true);
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
