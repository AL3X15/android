package com.example.smartcity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.R;

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

		logInBtn.setOnClickListener (new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ConnexionActivity.this, AcceuilActivity.class);
				if(mail.getText().toString().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$") && !password.getText().toString().equals("")){
				//todo faire le check si inscrit
					startActivity(intent);
				} else
					Toast.makeText(ConnexionActivity.this,"erreur de connection",Toast.LENGTH_SHORT).show();
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
