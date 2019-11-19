package com.example.smartcity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartcity.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        ButterKnife.bind(this);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceuilActivity.this,EditProfilActivity.class));
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceuilActivity.this,EmploiDuTempsActivity.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceuilActivity.this,RechercheAnnonceActivity.class));
            }
        });
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AcceuilActivity.this,FaqActivity.class));
            }
        });
        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo clear fichier de préférence
            }
        });
    }
}
