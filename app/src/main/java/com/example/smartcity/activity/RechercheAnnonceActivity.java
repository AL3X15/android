package com.example.smartcity.activity;

import android.content.Intent;
import android.graphics.Color;
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
	//@BindView(R.id.TagRecherche)
	//public RecyclerView tagRecyclerView;
	//private TagRechercheAdapter adapter;
	//private ArrayList<Tag> tagsEtudiant;
	//private UserEtudiant userEtudiant;

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

		//search.setEnabled(false);

		//userEtudiant = ((MyApplication) this.getApplication()).getInfoConnection().getUserEtudiant();

		//adapter = new TagRechercheAdapter();
		//tagsEtudiant = new ArrayList<>();

		//LoadTagEtudiant loadTagEtudiant = new LoadTagEtudiant();
		//loadTagEtudiant.execute(userEtudiant);

		//tagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		//tagRecyclerView.setAdapter(adapter);

		search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
/*

				if(dateDebut.getText().toString().isEmpty()) {
					dateDebut.setError(getResources().getString(R.string.error_empty));
					success = false;
				} else if (!dateDebut.getText().toString().matches("\\d{4}\\/\\d{2}\\/\\d{2}")) {

					dateDebut.setError(getResources().getString(R.string.error_matche_birthdate));
					success = false;
				} else if(!(DateFormatUtil.getDateFormated(dateDebut.getText().toString()).after(new Date()))) {
					dateDebut.setError(getResources().getString(R.string.error_date_after_today));
					success = false;
				}

				if(!dateFin.getText().toString().isEmpty() && !dateFin.getText().toString().matches("\\d{4}\\/\\d{2}\\/\\d{2}")){
					dateFin.setError(getResources().getString(R.string.error_matche_birthdate));
					success = false;
				}else if(!endDateText.getText().toString().isEmpty()) {

					if(!DateFormatUtil.getDateFormated(dateFin.getText().toString()).after(DateFormatUtil.getDateFormated(startDateText.getText().toString()))) {
						dateFin.setError(getResources().getString(R.string.error_date_after_begin_date));
						success = false;
					}
				}
*/

				String dateDebutRech = dateDebut.getText().toString();
				String dateFinRech = dateFin.getText().toString();
				if (dateDebutRech != null && dateFinRech != null && Utils.stringToDate(dateDebutRech).before(Utils.stringToDate(dateFinRech))) {
					Intent intent = new Intent(RechercheAnnonceActivity.this, ResultatActivity.class);
					intent.putExtra(getString(R.string.date_start), dateDebutRech);
					intent.putExtra(getString(R.string.date_end), dateFinRech);
					startActivity(intent);
				} else {
					dateDebut.setBackgroundColor(Color.parseColor("#FF0000"));
					dateFin.setBackgroundColor(Color.parseColor("#FF0000"));
				}
			}
		});
	}
}
