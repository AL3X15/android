package com.example.smartcity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.smartcity.MyApplication;
import com.example.smartcity.R;
import com.example.smartcity.Utils.Utils;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Preference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcceuilActivity extends AppCompatActivity {

    @BindView(R.id.buttonEditProfile)
    public Button editProfile;
    @BindView(R.id.buttonSchedule)
    public Button schedule;
    @BindView(R.id.buttonSearch)
    public Button search;
    @BindView(R.id.buttonFaq)
    public Button faq;
    @BindView(R.id.buttonDisconnect)
    public Button disconnect;

    private Etudiant etudiant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        ButterKnife.bind(this);
        Log.i("bonjour","au moins ca marche");
        etudiant =(Etudiant) getIntent().getSerializableExtra(getResources().getString(R.string.user));
        ((MyApplication)this.getApplication()).setEtudiant(etudiant);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcceuilActivity.this,EditProfilActivity.class);
                startActivity(intent);
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcceuilActivity.this,EmploiDuTempsActivity.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcceuilActivity.this,RechercheAnnonceActivity.class);
                startActivity(intent);
            }
        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcceuilActivity.this,FaqActivity.class);
                startActivity(intent);
            }
        });
        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference preference = new Preference(getString(R.string.mail_default));
                Utils.editSharedPreference(AcceuilActivity.this,preference);
                Intent intent = new Intent(AcceuilActivity.this,ConnexionActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
