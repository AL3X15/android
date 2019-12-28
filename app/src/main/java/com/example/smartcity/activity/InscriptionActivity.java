package com.example.smartcity.activity;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.smartcity.DataAccess.UserDao;
import com.example.smartcity.DataAccess.UserDataAccess;
import com.example.smartcity.R;
import com.example.smartcity.model.Adresse;
import com.example.smartcity.model.Etudiant;

import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InscriptionActivity extends AppCompatActivity {

	@BindView(R.id.firstInscription)
	public EditText firstName;
	@BindView(R.id.nameInscription)
    public EditText lastName;

	@BindView(R.id.hommeInscription)
	public RadioButton homme;
	@BindView(R.id.femmeInscription)
    public RadioButton femme;

	@BindView(R.id.roadInscription)
	public EditText roadInscription;
	@BindView(R.id.numberInscription)
	public EditText numberInscription;
	@BindView(R.id.zipInscription)
	public EditText zipInscription;
	@BindView(R.id.localityInscription)
	public EditText localityInscription;

	@BindView(R.id.phoneInscription)
    public EditText phoneInscription;
	@BindView(R.id.mailInscription)
	public EditText mailInscription;

	@BindView(R.id.birthdayInscription)
	public EditText birthdayInscription;
	@BindView(R.id.IdNumberInscription)
	public EditText idNumberInscription;

	@BindView(R.id.passwordInscription)
	public EditText password;

	@BindView(R.id.validateInscription)
	public Button validateInscription;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		ButterKnife.bind(this);
		homme.setChecked(true);

		validateInscription.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean dateValide = (birthdayInscription.getText().toString().length() == 8);
				boolean registreNationalValide = (idNumberInscription.getText().toString().matches("[0-9]{2}.[0-9]{2}.[0-9]{2}-[0-9]{3}.[0-9]{2}"));
				boolean mailValide = mailInscription.getText().toString().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
				boolean passwordValide = password.getText().toString().matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$");
				boolean formulaireValide = mailValide && dateValide && registreNationalValide && passwordValide;
				if(!mailValide)
					mailInscription.setBackgroundColor(Color.parseColor("#FF0000"));
				if(!registreNationalValide)
					idNumberInscription.setBackgroundColor(Color.parseColor("#FF0000"));
				if(!dateValide)
					birthdayInscription.setBackgroundColor(Color.parseColor("#FF0000"));
				if(!passwordValide)
					password.setBackgroundColor(Color.parseColor("#FF0000"));
				if(formulaireValide);{
					Etudiant e = new Etudiant();
					e.setPrenom(firstName.getText().toString());
					e.setNom(lastName.getText().toString());
					e.setAdresse(new Adresse(
							roadInscription.getText().toString(),
							numberInscription.getText().toString(),
							Integer.parseInt(zipInscription.getText().toString()),
							localityInscription.getText().toString())
					);
					char s;
					if(homme.isChecked())	s = 'h';
					else {
						if(femme.isChecked())	s = 'f';
						else	s = 'a';
					}
					e.setSexe(s);
					e.setNumTel(phoneInscription.getText().toString());
					int jour, mois,année;
					jour = Integer.parseInt(birthdayInscription.getText().toString().substring(0,1));
					mois = Integer.parseInt(birthdayInscription.getText().toString().substring(2,3));
					année = Integer.parseInt(birthdayInscription.getText().toString().substring(4,7));
					e.setDateNaissance(new GregorianCalendar(année,mois,jour));

					e.setMail(mailInscription.getText().toString());

					e.setRegistreNational(idNumberInscription.getText().toString());

					e.setPassword(password.getText().toString());

					Inscription inscription = new Inscription();
					inscription.execute(e);
				}
			}
		});
	}
	public void errorMessage(String error){
		Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
	}

	private class Inscription extends AsyncTask<Etudiant,Void,Void>{
		@Override
		protected Void doInBackground(Etudiant... etudiants) {
			UserDataAccess userDataAccess = new UserDao();
			try {
				userDataAccess.inscription(etudiants[0]);
				Intent intent = new Intent(InscriptionActivity.this,AcceuilActivity.class);
				intent.putExtra(getString(R.string.user),etudiants[0]);
				startActivity(intent);
			}catch (Exception e){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(e.getMessage());
					}
				});
			}
			return null;
		}
	}
}
