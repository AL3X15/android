package com.example.smartcity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcity.DataAccess.UserDao;
import com.example.smartcity.DataAccess.UserDataAccess;
import com.example.smartcity.R;
import com.example.smartcity.Utils.Utils;
import com.example.smartcity.model.Etudiant;
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
		logInBtn.setOnClickListener (new View.OnClickListener() {
			public void onClick(View v) {
				if(mail.getText().toString().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")){
					if(password.getText().toString().matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$")){
						Connection connection = new Connection();
						connection.execute(mail.getText().toString(),password.getText().toString());
					} else {
						Toast.makeText(ConnexionActivity.this,getString(R.string.password_error),Toast.LENGTH_SHORT).show();
					}
				} else
					Toast.makeText(ConnexionActivity.this,getString(R.string.mail_error),Toast.LENGTH_SHORT).show();
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

	private class Connection extends AsyncTask<String,Void, Etudiant> {
		@Override
		protected Etudiant doInBackground(String... strings) {
			UserDataAccess dataAccess = new UserDao();
			try {
				return dataAccess.getMe(strings[0],strings[1]);
			}catch (Exception e){
				return null;
			}
		}

		@Override
		protected void onPostExecute(Etudiant etudiant) {
			if(etudiant != null) {
				Intent intent = new Intent(ConnexionActivity.this, AcceuilActivity.class);

				Preference preference = new Preference(etudiant.getMail());
				Utils.editSharedPreference(ConnexionActivity.this,preference);

				intent.putExtra(getResources().getString(R.string.user),etudiant);

				startActivity(intent);
			}
		}
	}
}
