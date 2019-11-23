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
    private TagRechercheViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_annonce);
        ButterKnife.bind(this);
        adapter = new TagRechercheAdapter();
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag("test1",""));
        tags.add(new Tag("test2",""));
        tags.add(new Tag("test3",""));
        tags.add(new Tag("test4",""));
        adapter.setTags(tags);
        tagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tagRecyclerView.setAdapter(adapter);
    }
    private class TagRechercheViewHolder extends RecyclerView.ViewHolder{
        public Switch tag;

        public TagRechercheViewHolder(@NonNull View itemView){
            super(itemView);
            tag = itemView.findViewById(R.id.switch1);
        }

    }
    private class TagRechercheAdapter extends RecyclerView.Adapter<TagRechercheViewHolder>{
        private ArrayList<Tag> myTags;

        @NonNull
        @Override
        public TagRechercheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_recherche_element,parent,false);
            TagRechercheViewHolder vh = new TagRechercheViewHolder(v);
            return vh;
        }
        @Override
        public void onBindViewHolder(@NonNull TagRechercheViewHolder holder, int position){
            Tag tag = myTags.get(position);
            holder.tag.setText(tag.getNom());
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
