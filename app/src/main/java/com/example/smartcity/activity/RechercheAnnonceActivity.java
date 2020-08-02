package com.example.smartcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;
import com.example.smartcity.Utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RechercheAnnonceActivity extends AppCompatActivity {

	@BindView(R.id.dateDebut)
	public EditText dateDebut;
	@BindView(R.id.dateFin)
	public EditText dateFin;
	@BindView(R.id.search)
	public Button search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recherche_annonce);
		ButterKnife.bind(this);

		search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkForm()) {
					Intent intent = new Intent(RechercheAnnonceActivity.this, ResultatActivity.class);
					intent.putExtra(getString(R.string.date_start), dateDebut.getText().toString());
					intent.putExtra(getString(R.string.date_end), dateFin.getText().toString());
					startActivity(intent);
				}
			}
		});
	}

	private Boolean checkForm() {
		Boolean success = true;

		if (dateDebut.getText().toString().isEmpty()) {
			dateDebut.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (Utils.stringToDate(dateDebut.getText().toString()) == null) {

			dateDebut.setError(getResources().getString(R.string.error_matche_birthdate));
			success = false;
		}

		if (dateFin.getText().toString().isEmpty()) {
			dateFin.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (Utils.stringToDate(dateFin.getText().toString()) == null) {
			dateFin.setError(getResources().getString(R.string.error_matche_birthdate));
			success = false;
		} else if (Utils.stringToDate(dateDebut.getText().toString()) == null && Utils.stringToDate(dateDebut.getText().toString()).after(Utils.stringToDate(dateFin.getText().toString()))) {
			dateFin.setError(getResources().getString(R.string.dateError));
			success = false;
		}
		return success;
	}
}
