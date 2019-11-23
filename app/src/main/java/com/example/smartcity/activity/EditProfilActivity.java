package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TabHost;

import com.example.smartcity.R;
import com.example.smartcity.model.Tag;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfilActivity extends AppCompatActivity {

    @BindView(R.id.TagsEdit)
    RecyclerView tagRecyclerView;
    private TagAdapter adapter;
    private TagViewHolder viewHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        ButterKnife.bind(this);
        adapter = new TagAdapter();
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag("test1",""));
        tags.add(new Tag("test2",""));
        tags.add(new Tag("test3",""));
        tags.add(new Tag("test4",""));
        adapter.setTags(tags);
        tagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tagRecyclerView.setAdapter(adapter);
    }

    private class TagViewHolder extends RecyclerView.ViewHolder{
        public Switch tag;

        public TagViewHolder(@NonNull View itemView){
            super(itemView);
            tag = itemView.findViewById(R.id.switch1);
        }

    }
    private class TagAdapter extends RecyclerView.Adapter<TagViewHolder>{
        private ArrayList<Tag> myTags;

        @NonNull
        @Override
        public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_recherche_element,parent,false);
            TagViewHolder vh = new TagViewHolder(v);
            return vh;
        }
        @Override
        public void onBindViewHolder(@NonNull TagViewHolder holder, int position){
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
