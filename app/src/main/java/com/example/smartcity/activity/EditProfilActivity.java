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
import android.widget.Toast;

import com.example.smartcity.DataAccess.TagDao;
import com.example.smartcity.DataAccess.TagDataAccess;
import com.example.smartcity.DataAccess.UserDao;
import com.example.smartcity.DataAccess.UserDataAccess;
import com.example.smartcity.MyApplication;
import com.example.smartcity.R;
import com.example.smartcity.model.Adresse;
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

    @BindView(R.id.roadEdit)
    public EditText roadEdit;
    @BindView(R.id.numberEdit)
    public EditText numberEdit;
    @BindView(R.id.zipEdit)
    public EditText zipEdit;
    @BindView(R.id.localityEdit)
    public EditText localityEdit;
    @BindView(R.id.phoneEdit)
    public EditText phoneEdit;
    @BindView(R.id.mailEdit)
    public EditText mailEdit;
    @BindView(R.id.passwordEdit)
    public EditText passwordEdit;
    @BindView(R.id.validateEdit)
    public Button validateEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        ButterKnife.bind(this);
        validateEdit.setEnabled(false);

        etudiant = ((MyApplication)this.getApplication()).getEtudiant();

        adapter = new TagAdapter();
        tagsEtudiant = new ArrayList<>();


        LoadTagEtudiant loadTagEtudiant = new LoadTagEtudiant();
        loadTagEtudiant.execute(etudiant);

        tagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tagRecyclerView.setAdapter(adapter);


        roadEdit.setText(etudiant.getAdresse().getRoute());
        numberEdit.setText(etudiant.getAdresse().getNumero());
        zipEdit.setText(etudiant.getAdresse().getCodePostal().toString());
        localityEdit.setText(etudiant.getAdresse().getLocalite());
        phoneEdit.setText(etudiant.getNumTel().toString());
        mailEdit.setText(etudiant.getMail());

        validateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean mailValide = mailEdit.getText().toString().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
                boolean passwordValide = passwordEdit.getText().toString().isEmpty() || passwordEdit.getText().toString().matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$");
                boolean formulaireValide = passwordValide && mailValide;
                if (formulaireValide){
                        etudiant.setMail(mailEdit.getText().toString());
                    if (passwordEdit.getText().toString().isEmpty())
                        etudiant.setPassword(passwordEdit.getText().toString());
                    etudiant.setNumTel(phoneEdit.getText().toString());
                    etudiant.setAdresse(new Adresse(
                            roadEdit.getText().toString(),
                            numberEdit.getText().toString(),
                            Integer.parseInt(zipEdit.getText().toString()),
                            localityEdit.getText().toString()
                    ));
                    etudiant.setTags(tagsEtudiant);
                    EditProfile editProfile = new EditProfile();
                    editProfile.execute(etudiant);
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
            try {
                TagDataAccess tagDataAccess = new TagDao();
                return tagDataAccess.getTagsEtudiant(((MyApplication) getApplication()).getEtudiant().getAccesToken(),etudiants[0]);
            }catch (Exception e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        errorMessage(e.getMessage());
                    }
                });
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Tag> tags) {
            if(tags != null) {
                tagsEtudiant = tags;
                LoadAllTags loadAllTags = new LoadAllTags();
                loadAllTags.execute();
            }
        }
    }
    private class LoadAllTags extends AsyncTask<Void,Void,ArrayList<Tag>>{
        @Override
        protected ArrayList<Tag> doInBackground(Void... voids) {
            TagDataAccess tagDataAccess = new TagDao();
            try {
                return tagDataAccess.getAllTag(((MyApplication) getApplication()).getEtudiant().getAccesToken());
            }catch (Exception e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        errorMessage(e.getMessage());
                    }
                });
                return null;
            }
        }
        @Override
        protected void onPostExecute(ArrayList<Tag> tags){
            adapter.setTags(tags);
            validateEdit.setEnabled(true);
        }
    }

    private class EditProfile extends AsyncTask<Etudiant,Void,Etudiant>{
        @Override
        protected Etudiant doInBackground(Etudiant... etudiants) {
            UserDataAccess userDataAccess = new UserDao();
            try {
                userDataAccess.editMe(((MyApplication) getApplication()).getEtudiant().getAccesToken(), etudiants[0]);
            }catch (Exception e){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        errorMessage(e.getMessage());
                    }
                });
            }
            return etudiants[0];
        }

        @Override
        protected void onPostExecute(Etudiant etudiant) {
            Intent intent = new Intent(EditProfilActivity.this,AcceuilActivity.class);
            intent.putExtra(getResources().getString(R.string.user),etudiant);
            startActivity(intent);
        }
    }
}
