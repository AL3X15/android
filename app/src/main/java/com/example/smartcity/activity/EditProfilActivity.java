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
import com.example.smartcity.model.Etudiant;
import com.example.smartcity.model.Localite;
import com.example.smartcity.model.Tag;
import com.example.smartcity.model.TagClasse;
import com.example.smartcity.service.CheckIntenetConnection;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class EditProfilActivity extends AppCompatActivity {

	@BindView(R.id.firstEdit)
	public EditText firstName;
	@BindView(R.id.nameEdit)
	public EditText lastName;

	@BindView(R.id.hommeEdit)
	public RadioButton homme;
	@BindView(R.id.femmeEdit)
	public RadioButton femme;
	@BindView(R.id.autreEdit)
	public RadioButton autre;

	@BindView(R.id.roadEdit)
	public EditText road;
	@BindView(R.id.numberEdit)
	public EditText number;
	@BindView(R.id.zipEdit)
	public EditText zip;
	@BindView(R.id.localityEdit)
	public EditText locality;

	@BindView(R.id.phoneEdit)
	public EditText phone;
	@BindView(R.id.mailEdit)
	public EditText mail;

	@BindView(R.id.birthdayEdit)
	public EditText birthday;
	@BindView(R.id.IdNumberEdit)
	public EditText idNumber;

	@BindView(R.id.tagsLayoutEdit)
	public LinearLayout tagsLayout;
	@BindView(R.id.tagErrorEdit)
	public TextView tagError;
	public TextView titreTagClasse;
	public CheckBox tagCheckBox;

	@BindView(R.id.validateEdit)
	public Button validateEdit;

	public ArrayList<Tag> selectedTags;
	public Etudiant me;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profil);
		ButterKnife.bind(this);

		validateEdit.setEnabled(false);

		selectedTags = new ArrayList<>();
		if (CheckIntenetConnection.checkConnection(EditProfilActivity.this)) {
			new LoadAllTags().execute();
			new GetUser().execute();
		} else {
			Toast.makeText(EditProfilActivity.this, getString(R.string.connection_error), Toast.LENGTH_LONG).show();

			validateEdit.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (checkForm()) {
						me.setPrenom(firstName.getText().toString());
						me.getUser().setNom(lastName.getText().toString());
						me.getAdresse().setRue(road.getText().toString());
						me.getAdresse().setNumero(number.getText().toString());
						me.getAdresse().setLocalite(new Localite());
						me.getAdresse().getLocalite().setCodePostal(zip.getText().toString());
						me.getAdresse().getLocalite().setNom(locality.getText().toString());
						me.setSexe(getSexe());
						me.getUser().setPhoneNumber(phone.getText().toString());
						me.setDateNaissance(Utils.stringToDate(birthday.getText().toString()));
						me.getUser().setEmail(mail.getText().toString());
						me.setRegistreNational(idNumber.getText().toString());
						me.setTags(new ArrayList());
						for (Tag tag : selectedTags)
							me.getTags().add(tag);

						if (CheckIntenetConnection.checkConnection(EditProfilActivity.this))
							new EditProfile().execute(me);
						else
							Toast.makeText(EditProfilActivity.this, getString(R.string.connection_error), Toast.LENGTH_LONG).show();
					}
				}
			});
		}
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

		if (road.getText().toString().isEmpty()) {
			road.setError(getResources().getString(R.string.error_empty));
			success = false;
		}

		if (number.getText().toString().isEmpty()) {
			number.setError(getResources().getString(R.string.error_empty));
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
		} else if (!phone.getText().toString().matches("0\\d+")) {
			phone.setError(getResources().getString(R.string.error_phone_matche));
			success = false;
		}


		if (mail.getText().toString().isEmpty()) {
			mail.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!mail.getText().toString().matches(".+@.+\\..+")) {
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
		} else if (!idNumber.getText().toString().matches("(\\d{2}\\.){2}\\d{2}-\\d{3}\\.\\d{2}")) {
			idNumber.setError(getResources().getString(R.string.error_matche_id));
			success = false;
		}

		if (selectedTags.isEmpty()) {
			tagError.setError(getString(R.string.tagLess));
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

	private void fillForm(Etudiant etudiant) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		firstName.setText(etudiant.getPrenom());
		lastName.setText(etudiant.getUser().getNom());
		road.setText(etudiant.getAdresse().getRue());
		number.setText(etudiant.getAdresse().getNumero());
		zip.setText(etudiant.getAdresse().getLocalite().getCodePostal());
		locality.setText(etudiant.getAdresse().getLocalite().getNom());
		phone.setText(etudiant.getUser().getPhoneNumber());
		mail.setText(etudiant.getUser().getEmail());
		birthday.setText(dateFormat.format(etudiant.getDateNaissance()));
		idNumber.setText(etudiant.getRegistreNational());
		if (etudiant.getSexe().equals("m"))
			homme.setChecked(true);
		else if (etudiant.getSexe().equals("f"))
			femme.setChecked(true);
		else
			autre.setChecked(true);
		for (Tag tag : etudiant.getTags()) {
			((CheckBox) findViewById(tag.getId())).setChecked(true);
			selectedTags.add(new Tag(tag.getId(), tag.getNom()));
		}
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


	private class GetUser extends AsyncTask<Void, Void, Etudiant> {

		@Override
		protected Etudiant doInBackground(Void... voids) {
			try {
				Response<Etudiant> response = new UserDao().getMe();

				if (response.isSuccessful() && response.code() == 200) {
					return response.body();
				}

				runOnUiThread(() -> Toast.makeText(EditProfilActivity.this, getString(Utils.msgErreur(response)), Toast.LENGTH_LONG).show());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(Etudiant etudiant) {
			if (etudiant != null) {
				me = etudiant;
				fillForm(me);
			}
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

				runOnUiThread(() -> Toast.makeText(EditProfilActivity.this, getString(Utils.msgErreur(response)), Toast.LENGTH_LONG).show());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(ArrayList<TagClasse> tagClasses) {
			for (TagClasse tagClasse : tagClasses) {
				titreTagClasse = new TextView(EditProfilActivity.this);
				titreTagClasse.setText(tagClasse.getNom());
				tagsLayout.addView(titreTagClasse);

				for (Tag tag : tagClasse.getTags()) {
					tagCheckBox = new CheckBox(EditProfilActivity.this);
					tagCheckBox.setId(tag.getId());
					tagCheckBox.setText(tag.getNom());
					tagCheckBox.setOnClickListener(getTagListener(tagCheckBox));
					tagsLayout.addView(tagCheckBox);
				}

			}
			validateEdit.setEnabled(true);
		}

	}

	private class EditProfile extends AsyncTask<Etudiant, Void, Void> {
		@Override
		protected Void doInBackground(Etudiant... etudiant) {
			try {
				Response<Void> response = new UserDao().editMe(etudiant[0]);

				if (response.isSuccessful() && response.code() == 200) {
					startActivity(new Intent(EditProfilActivity.this, AccueilActivity.class));
					return null;
				}

				runOnUiThread(() -> Toast.makeText(EditProfilActivity.this, getString(Utils.msgErreur(response)), Toast.LENGTH_LONG).show());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
