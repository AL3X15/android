package com.example.smartcity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;
import com.example.smartcity.Utils.Utils;
import com.example.smartcity.model.Preference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConnexionActivity extends AppCompatActivity {
	@BindView(R.id.logInBtn)
	public Button logInBtn;
	@BindView(R.id.signInBtn)
	public Button signInBtn;
	@BindView(R.id.mailLogIn)
	public TextView mail;
	@BindView(R.id.passwordLogIn)
	public TextView password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);
		ButterKnife.bind(this);
		Preference user = Utils.getSharedPreference(this);
		if(!user.isDefaultMail()){
			mail.setText(user.getEmail());
		}
		if(!user.isDefaultPassword()){
			password.setText(user.getPassword());
		}

		logInBtn.setOnClickListener (new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ConnexionActivity.this, AcceuilActivity.class);
				if(mail.getText().toString().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
					if(password.getText().toString().matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$")){
						//todo faire le check si inscrit
							Preference preference = new Preference(mail.getText().toString(),password.getText().toString());
							Utils.editSharedPreference(ConnexionActivity.this,preference);
							startActivity(intent);
					} else {
						Toast.makeText(ConnexionActivity.this,"mot de passe incorrect",Toast.LENGTH_SHORT).show();
					}
				} else
					Toast.makeText(ConnexionActivity.this,"mail incorrect",Toast.LENGTH_SHORT).show();
			}
		});
		signInBtn.setOnClickListener (new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ConnexionActivity.this, InscriptionActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onResume(){
		super.onResume();
	}

	@Override
	public void onStart(){
		super.onStart();
	}

	@Override
	public void onPause(){
		super.onPause();
	}

	@Override
	public void onStop(){
		super.onStop();
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
	}


}
