package com.example.smartcity.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.DataAccess.dao.TagDao;
import com.example.smartcity.DataAccess.dao.UserDao;
import com.example.smartcity.R;
import com.example.smartcity.Utils.Utils;
import com.example.smartcity.model.Adresse;
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Localite;
import com.example.smartcity.model.Tag;
import com.example.smartcity.model.TagClasse;
import com.example.smartcity.model.UserEtudiant;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

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
	@BindView(R.id.passwordConfirmation)
	public EditText passwordConfirmation;

	@BindView(R.id.validateInscription)
	public Button validateInscription;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		ButterKnife.bind(this);
		homme.setChecked(true);

		new LoadAllTags().execute();


		validateInscription.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkForm()) {
					Etudiant e = new Etudiant();
					e.setUser(new UserEtudiant());
					e.setPrenom(firstName.getText().toString());
					e.getUser().setNom(lastName.getText().toString());
					e.setAdresse(new Adresse());
					e.getAdresse().setRue(roadInscription.getText().toString());
					e.getAdresse().setNumero(numberInscription.getText().toString());
					e.getAdresse().setLocalite(new Localite());
					e.getAdresse().getLocalite().setCodePostal(zipInscription.getText().toString());
					e.getAdresse().getLocalite().setNom(localityInscription.getText().toString());
					e.setSexe(getSexe());
					e.getUser().setPhoneNumber(phoneInscription.getText().toString());
					e.setDateNaissance(Utils.stringToDate(birthdayInscription.getText().toString()));
					e.getUser().setEmail(mailInscription.getText().toString());
					e.setRegistreNational(idNumberInscription.getText().toString());
					e.getUser().setPassword(password.getText().toString());
					e.getUser().setConfirmationPassword(passwordConfirmation.getText().toString());
					e.setTags(new ArrayList<Tag>());
					//TODO tags


					new Inscription().execute(e);
				}
			}
		});
	}

	private Boolean checkForm() {
		Boolean success = true;

		if (firstName.getText().toString().isEmpty()) {
			firstName.setError(getResources().getString(R.string.error_empty));
			success = false;
		}

		if (lastName.getText().toString().isEmpty()) {
			lastName.setError(getResources().getString(R.string.error_empty));
			success = false;
		}

		if (roadInscription.getText().toString().isEmpty()) {
			roadInscription.setError(getResources().getString(R.string.error_empty));
			success = false;
		}

		if (numberInscription.getText().toString().isEmpty()) {
			numberInscription.setError(getResources().getString(R.string.error_empty));
			success = false;
		}

		if (zipInscription.getText().toString().isEmpty()) {
			zipInscription.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!zipInscription.getText().toString().matches("^\\d{4}$")) {
			zipInscription.setError(getResources().getString(R.string.error_matche_postalcode));
			success = false;
		}

		if (localityInscription.getText().toString().isEmpty()) {
			localityInscription.setError(getResources().getString(R.string.error_empty));
			success = false;
		}

		if (phoneInscription.getText().toString().isEmpty()) {
			phoneInscription.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!phoneInscription.getText().toString().matches("0\\d+")) {
			phoneInscription.setError(getResources().getString(R.string.error_phone_matche));
			success = false;
		}


		if (mailInscription.getText().toString().isEmpty()) {
			mailInscription.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!mailInscription.getText().toString().matches(".+@.+\\..+")) {
			mailInscription.setError(getResources().getString(R.string.error_matche_email));
			success = false;
		}


		if (birthdayInscription.getText().toString().isEmpty()) {
			birthdayInscription.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!birthdayInscription.getText().toString().matches("\\d{2}\\/\\d{2}\\/\\d{4}")) {

			birthdayInscription.setError(getResources().getString(R.string.error_matche_birthdate));
			success = false;
		} else if (Utils.stringToDate(birthdayInscription.getText().toString()).after(new Date())) {
			birthdayInscription.setError(getResources().getString(R.string.futurBirth));
			success = false;
		} else if (Period.between(Utils.stringToDate(birthdayInscription.getText().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears() < 18) {
			birthdayInscription.setError(getResources().getString(R.string.ageError));
			success = false;
		}

		if (idNumberInscription.getText().toString().isEmpty()) {
			idNumberInscription.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!idNumberInscription.getText().toString().matches("(\\d{2}\\.){2}\\d{2}-\\d{3}\\.\\d{2}")) {
			idNumberInscription.setError(getResources().getString(R.string.error_matche_id));
			success = false;
		}

		if (password.getText().toString().isEmpty()) {
			password.setError(getResources().getString(R.string.error_empty));
			success = false;
		}

		if (passwordConfirmation.getText().toString().isEmpty()) {
			passwordConfirmation.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!passwordConfirmation.getText().toString().equals(password.getText().toString())) {
			passwordConfirmation.setError(getResources().getString(R.string.register_confirm_pass_matche));
			success = false;
		}

		return success;
	}

	private String getSexe() {
		if (homme.isChecked())
			return "m";
		if (femme.isChecked())
			return "f";
		return "a";
	}

	private class Inscription extends AsyncTask<Etudiant, Void, Void> {
		@Override
		protected Void doInBackground(Etudiant... etudiants) {
			try {
				Response<Void> response = new UserDao().inscription(etudiants[0]);

				if (response.isSuccessful() && response.code() == 201) {
					return null;
				}

				runOnUiThread(() -> Toast.makeText(InscriptionActivity.this, getString(Utils.msgErreur(response)), Toast.LENGTH_LONG).show());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	private class LoadAllTags extends AsyncTask<Void, Void, ArrayList<TagClasse>> {
		@Override
		protected ArrayList<TagClasse> doInBackground(Void... voids) {
			try {
				Response<ArrayList<TagClasse>> response = new TagDao().GetTags();

				if (response.isSuccessful() && response.code() == 200) {
					return response.body();
				}

				runOnUiThread(() -> Toast.makeText(InscriptionActivity.this, getString(Utils.msgErreur(response)), Toast.LENGTH_LONG).show());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(ArrayList<TagClasse> tags) {
			//TODO afficher les tags
			validateInscription.setEnabled(true);
		}

	}

}
