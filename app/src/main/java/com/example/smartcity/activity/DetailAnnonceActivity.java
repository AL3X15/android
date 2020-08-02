package com.example.smartcity.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.DataAccess.dao.AnnonceDao;
import com.example.smartcity.R;
import com.example.smartcity.Utils.Utils;
import com.example.smartcity.model.Annonce;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class DetailAnnonceActivity extends AppCompatActivity {

	@BindView(R.id.EntrepriseNom)
	public TextView entrepriseNom;
	@BindView(R.id.DetailEntrepriseDescription)
	public TextView details;
	@BindView(R.id.AcceptAnnonce)
	public Button button;

	Annonce annonce;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_annonce);
		ButterKnife.bind(this);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		annonce = (Annonce) getIntent().getSerializableExtra(getString(R.string.annonce));


		entrepriseNom.setText(annonce.getPoste());
		details.setText(new StringBuilder()
				.append(getString(R.string.poste))
				.append(annonce.getPoste())
				.append("\n")
				.append(getString(R.string.paie))
				.append(annonce.getPaie())
				.append("\n")
				.append(getString(R.string.date_start))
				.append(dateFormat.format(annonce.getDateDebut()))
				.append("\n")
				.append(getString(R.string.date_end))
				.append(dateFormat.format(annonce.getDateFin()))
				.append("\n")
				.append(getString(R.string.nomEntreprise))
				.append(annonce.getEntreprise().getUser().getNom())
				.append("\n")
				.append(getString(R.string.nomResponsable))
				.append(annonce.getEntreprise().getNomResponsable())
				.append("\n")
				.append(getString(R.string.email))
				.append(annonce.getEntreprise().getUser().getEmail())
				.append("\n")
				.append(getString(R.string.phone))
				.append(annonce.getEntreprise().getUser().getPhoneNumber())
				.append("\n")
				.append(getString(R.string.adress))
				.append("\n")
				.append(getString(R.string.zip))
				.append(annonce.getAdresse().getLocalite().getCodePostal())
				.append("\n")
				.append(getString(R.string.locality))
				.append(annonce.getAdresse().getLocalite().getNom())
				.append("\n")
				.append(getString(R.string.road))
				.append(annonce.getAdresse().getRue())
				.append("\n")
				.append(getString(R.string.number))
				.append(annonce.getAdresse().getNumero())
				.append("\n")
				.toString());



		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				button.setEnabled(false);
				new AccepterOffre().execute(annonce.getId());
			}
		});
	}

	private class AccepterOffre extends AsyncTask<Integer, Void, Void> {
		@Override
		protected Void doInBackground(Integer... id) {
			try {
				Response<Void> response = new AnnonceDao().acceptAnnonce(id[0]);

				if (response.isSuccessful() && response.code() == 201) {
					return null;
				}
				runOnUiThread(() -> Toast.makeText(DetailAnnonceActivity.this, getString(Utils.msgErreur(response)), Toast.LENGTH_LONG).show());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

	}

}
