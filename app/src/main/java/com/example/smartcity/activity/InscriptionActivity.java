package com.example.smartcity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.smartcity.DataAccess.UserDao;
import com.example.smartcity.DataAccess.UserDataAccess;
import com.example.smartcity.Exception.ApiAccessException;
import com.example.smartcity.Exception.EtudiantDontExist;
import com.example.smartcity.Exception.InscriptionInvalide;
import com.example.smartcity.MyApplication;
import com.example.smartcity.R;
import com.example.smartcity.model.Adresse;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.UserEtudiant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
				if(formulaireValide){
					UserEtudiant e = new UserEtudiant();
					e.setEtudiant(new Etudiant());
					e.getEtudiant().setPrenom(firstName.getText().toString());
					e.setNom(lastName.getText().toString());
					e.getEtudiant().setAdresse(new Adresse(
							roadInscription.getText().toString(),
							numberInscription.getText().toString(),
							zipInscription.getText().toString(),
							localityInscription.getText().toString())
					);
					char s;
					if(homme.isChecked())	s = 'M';
					else {
						if(femme.isChecked())	s = 'F';
						else	s = 'A';
					}
					e.getEtudiant().setSexe(s);
					e.setPhoneNumber(phoneInscription.getText().toString());

					int jour, mois,année;
					jour = Integer.parseInt(birthdayInscription.getText().toString().substring(0,1));
					mois = Integer.parseInt(birthdayInscription.getText().toString().substring(2,3));
					année = Integer.parseInt(birthdayInscription.getText().toString().substring(4,7));
					//verifie que date existe
					e.getEtudiant().setDateNaissance(new GregorianCalendar(année,mois,jour).getTime());

					e.setEmail(mailInscription.getText().toString());

					e.getEtudiant().setRegistreNational(idNumberInscription.getText().toString());

					e.setPassword(password.getText().toString());
					e.setConfirmationPassword(password.getText().toString());
					Inscription inscription = new Inscription();
					inscription.execute(e);
				}
			}
		});
	}
	public void errorMessage(String error){
		Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
	}

	private class Inscription extends AsyncTask<UserEtudiant,Void,Void>{
		@Override
		protected Void doInBackground(UserEtudiant... userEtudiants) {
			UserDataAccess userDataAccess = new UserDao();
			try {
				userDataAccess.inscription(userEtudiants[0]);
				Intent intent = new Intent(InscriptionActivity.this,ConnexionActivity.class);
				startActivity(intent);
			}
			catch (EtudiantDontExist e){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.etudiant_error));
					}
				});
			}
			catch (ApiAccessException e){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.accessApiError));
					}
				});
			}
			catch (InscriptionInvalide e){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.inscription_error));
					}
				});
			}
			catch (Exception e){
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.connection_error));
					}
				});
			}
			return null;
		}
	}
}
