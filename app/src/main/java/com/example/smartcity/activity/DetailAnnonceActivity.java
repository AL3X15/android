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
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.smartcity.DataAccess.AnnonceDataAccess;
import com.example.smartcity.DataAccess.TagDao;
import com.example.smartcity.DataAccess.TagDataAccess;
import com.example.smartcity.R;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Tag;

import java.lang.annotation.AnnotationFormatError;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailAnnonceActivity extends AppCompatActivity {

    @BindView(R.id.tagsDescription)
    public RecyclerView recyclerView;
    private TagAdapter adapter;
    Annonce annonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_annonce);
        ButterKnife.bind(this);

        adapter = new TagAdapter();

        LoadTag loadTag = new LoadTag();
        loadTag.execute(annonce);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private class TagViewHolder extends RecyclerView.ViewHolder{
        public TextView tag;

        public TagViewHolder(@NonNull View itemView){
            super(itemView);
            tag = itemView.findViewById(R.id.tagDescription);
        }
    }
    private class TagAdapter extends RecyclerView.Adapter<TagViewHolder>{
        private ArrayList<Tag> myTags;

        @NonNull
        @Override
        public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_description_element,parent,false);
            TagViewHolder vh = new TagViewHolder(v);
            return vh;
        }
        @Override
        public void onBindViewHolder(@NonNull TagViewHolder holder, int position){
            Tag tag = myTags.get(position);
            holder.tag.setText(tag.getDescription());
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

    private class LoadTag extends AsyncTask<Annonce,Void,ArrayList<Tag>>{
        @Override
        protected ArrayList<Tag> doInBackground(Annonce... params){
            TagDataAccess tagDataAccess = new TagDao();
            return tagDataAccess.getTagsAnnonce(params[0]);
        }
        @Override
        protected void onPostExecute(ArrayList<Tag> tags){
            adapter.setTags(tags);
        }
    }

}
