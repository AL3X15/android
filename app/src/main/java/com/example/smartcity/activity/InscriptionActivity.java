package com.example.smartcity.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
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
import com.example.smartcity.service.CheckIntenetConnection;

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
	@BindView(R.id.autreInscription)
	public RadioButton autre;

	@BindView(R.id.roadInscription)
	public EditText road;
	@BindView(R.id.numberInscription)
	public EditText number;
	@BindView(R.id.zipInscription)
	public EditText zip;
	@BindView(R.id.localityInscription)
	public EditText locality;

	@BindView(R.id.phoneInscription)
	public EditText phone;
	@BindView(R.id.mailInscription)
	public EditText mail;

	@BindView(R.id.birthdayInscription)
	public EditText birthday;
	@BindView(R.id.IdNumberInscription)
	public EditText idNumber;

	@BindView(R.id.passwordInscription)
	public EditText password;
	@BindView(R.id.passwordConfirmation)
	public EditText passwordConfirmation;

	@BindView(R.id.tagsLayout)
	public LinearLayout tagsLayout;

	public TextView titreTagClasse;
	public CheckBox tagCheckBox;

	@BindView(R.id.validateInscription)
	public Button validateInscription;

	public ArrayList<Tag> selectedTags;


	//TODO regex nom/prenom avec accent et espace + erreur 500

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		ButterKnife.bind(this);
		homme.setChecked(true);

		selectedTags = new ArrayList<>();
		if (CheckIntenetConnection.checkConnection(InscriptionActivity.this))
			new LoadAllTags().execute();
		else
			Toast.makeText(InscriptionActivity.this, getString(R.string.connection_error), Toast.LENGTH_LONG).show();

		validateInscription.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkForm()) {
					Etudiant e = new Etudiant();
					e.setUser(new UserEtudiant());
					e.setPrenom(firstName.getText().toString());
					e.getUser().setNom(lastName.getText().toString());
					e.setAdresse(new Adresse());
					e.getAdresse().setRue(road.getText().toString());
					e.getAdresse().setNumero(number.getText().toString());
					e.getAdresse().setLocalite(new Localite());
					e.getAdresse().getLocalite().setCodePostal(zip.getText().toString());
					e.getAdresse().getLocalite().setNom(locality.getText().toString());
					e.setSexe(getSexe());
					e.getUser().setPhoneNumber(phone.getText().toString());
					e.setDateNaissance(Utils.stringToDate(birthday.getText().toString()));
					e.getUser().setEmail(mail.getText().toString());
					e.setRegistreNational(idNumber.getText().toString());
					e.getUser().setPassword(password.getText().toString());
					e.getUser().setConfirmationPassword(passwordConfirmation.getText().toString());
					e.setTags(new ArrayList());
					for (Tag tag : selectedTags)
						e.getTags().add(tag);

					if (CheckIntenetConnection.checkConnection(InscriptionActivity.this))
						new Inscription().execute(e);
					else
						Toast.makeText(InscriptionActivity.this, getString(R.string.connection_error), Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private Boolean checkForm() {
		Boolean success = true;

		if (firstName.getText().toString().isEmpty()) {
			firstName.setError(getResources().getString(R.string.error_empty));
			success = false;
		}else if(!firstName.getText().toString().matches("^[A-Za-z À-ÿ]+$")){
			firstName.setError(getResources().getString(R.string.invalidName));
			success = false;
		}


		if (lastName.getText().toString().isEmpty()) {
			lastName.setError(getResources().getString(R.string.error_empty));
			success = false;
		}
		else if(!lastName.getText().toString().matches("^[A-Za-z À-ÿ]+$")){
			lastName.setError(getResources().getString(R.string.invalidName));
			success = false;
		}

		if (road.getText().toString().isEmpty()) {
			road.setError(getResources().getString(R.string.error_empty));
			success = false;
		}

		if (number.getText().toString().isEmpty()) {
			number.setError(getResources().getString(R.string.error_empty));
			success = false;
		}else if (number.getText().toString().matches("0.*") || !number.getText().toString().matches("\\d+.*")){
			number.setError(getString(R.string.invalidNumber));
			success = false;
		}

		if (zip.getText().toString().isEmpty()) {
			zip.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!zip.getText().toString().matches("^\\d{4}$")) {
			zip.setError(getResources().getString(R.string.error_matche_postalcode));
			success = false;
		}

		if (locality.getText().toString().isEmpty()) {
			locality.setError(getResources().getString(R.string.error_empty));
			success = false;
		}

		if (phone.getText().toString().isEmpty()) {
			phone.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!phone.getText().toString().matches("^((\\+|00)32\\s?|0)(\\d\\s?\\d{3}|\\d{2}\\s?\\d{2})(\\s?\\d{2}){2}$") && !phone.getText().toString().matches("^((\\+|00)32\\s?|0)4(60|[789]\\d)(\\s?\\d{2}){3}$")) {
			phone.setError(getResources().getString(R.string.error_phone_matche));
			success = false;
		}


		if (mail.getText().toString().isEmpty()) {
			mail.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!mail.getText().toString().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
			mail.setError(getResources().getString(R.string.error_matche_email));
			success = false;
		}


		if (birthday.getText().toString().isEmpty()) {
			birthday.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (Utils.stringToDate(birthday.getText().toString()) == null) {
			birthday.setError(getResources().getString(R.string.error_matche_birthdate));
			success = false;
		} else if (Utils.stringToDate(birthday.getText().toString()).after(new Date())) {
			birthday.setError(getResources().getString(R.string.futurBirth));
			success = false;
		} else if (Period.between(Utils.stringToDate(birthday.getText().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears() < 18) {
			birthday.setError(getResources().getString(R.string.ageError));
			success = false;
		}

		if (idNumber.getText().toString().isEmpty()) {
			idNumber.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!idNumber.getText().toString().matches("^(\\d{2}\\.){2}\\d{2}-\\d{3}\\.\\d{2}$")) {
			idNumber.setError(getResources().getString(R.string.error_matche_id));
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

		if (selectedTags.isEmpty()) {
			Toast.makeText(InscriptionActivity.this, getString(R.string.tagLess), Toast.LENGTH_LONG).show();
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

	View.OnClickListener getTagListener(final CheckBox checkBox) {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!selectedTags.removeIf(x -> x.getId() == checkBox.getId()))
					selectedTags.add(new Tag(checkBox.getId(), checkBox.getText().toString()));
			}
		};
	}


	private class Inscription extends AsyncTask<Etudiant, Void, Void> {
		@Override
		protected Void doInBackground(Etudiant... etudiants) {
			try {
				Response<Void> response = new UserDao().inscription(etudiants[0]);

				if (response.isSuccessful() && response.code() == 201) {
					startActivity(new Intent(InscriptionActivity.this, ConnexionActivity.class));
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

		protected void onPostExecute(ArrayList<TagClasse> tagClasses) {
			if (tagClasses != null) {
				for (TagClasse tagClasse : tagClasses) {
					titreTagClasse = new TextView(InscriptionActivity.this);
					titreTagClasse.setText(tagClasse.getNom());
					tagsLayout.addView(titreTagClasse);

					for (Tag tag : tagClasse.getTags()) {
						tagCheckBox = new CheckBox(InscriptionActivity.this);
						tagCheckBox.setId(tag.getId());
						tagCheckBox.setText(tag.getNom());
						tagCheckBox.setOnClickListener(getTagListener(tagCheckBox));
						tagsLayout.addView(tagCheckBox);
					}

				}
				validateInscription.setEnabled(true);

			}else
				startActivity(new Intent(InscriptionActivity.this, ConnexionActivity.class));
		}

	}

}
