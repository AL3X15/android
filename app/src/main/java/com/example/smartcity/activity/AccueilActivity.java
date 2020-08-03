package com.example.smartcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import service.AuthSessionService;

public class AccueilActivity extends AppCompatActivity {

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
	@BindView(R.id.buttonPassword)
	public Button changePassword;
	@BindView(R.id.buttonDelete)
	public Button deleteAccount;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceuil);
		ButterKnife.bind(this);

		editProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AccueilActivity.this, EditProfilActivity.class));
			}
		});

		schedule.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AccueilActivity.this, EmploiDuTempsActivity.class));
			}
		});

		search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AccueilActivity.this, RechercheAnnonceActivity.class));
			}
		});

		faq.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AccueilActivity.this, FaqActivity.class));
			}
		});

		disconnect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AuthSessionService.disconnectUser(AccueilActivity.this);
				startActivity(new Intent(AccueilActivity.this, ConnexionActivity.class));
			}
		});

		changePassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AccueilActivity.this, PasswordActivity.class));
			}
		});

		deleteAccount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AccueilActivity.this, DeleteActivity.class));
			}
		});

	}
}
