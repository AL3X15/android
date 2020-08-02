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
import com.example.smartcity.model.Postulation;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class PostulationActivity extends AppCompatActivity {
	@BindView(R.id.EntrepriseNom2)
	public TextView entrepriseNom;
	@BindView(R.id.DetailPostulation)
	public TextView details;
	@BindView(R.id.AnnulerPostulation)
	public Button button;

	Postulation postulation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_postulation);
		ButterKnife.bind(this);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		postulation = (Postulation) getIntent().getSerializableExtra(getString(R.string.postulation));

		entrepriseNom.setText(postulation.getAnnonce().getPoste());
		details.setText(new StringBuilder()
				.append(getString(R.string.IsAccepted))
				.append(postulation.getEstAccepte() ? getString(R.string.yes) : getString(R.string.no))
				.append("\n")
				.append(getString(R.string.poste))
				.append(postulation.getAnnonce().getPoste())
				.append("\n")
				.append(getString(R.string.paie))
				.append(postulation.getAnnonce().getPaie())
				.append("\n")
				.append(getString(R.string.date_start))
				.append(dateFormat.format(postulation.getAnnonce().getDateDebut()))
				.append("\n")
				.append(getString(R.string.date_end))
				.append(dateFormat.format(postulation.getAnnonce().getDateFin()))
				.append("\n")
				.append(getString(R.string.nomEntreprise))
				.append(postulation.getAnnonce().getEntreprise().getUser().getNom())
				.append("\n")
				.append(getString(R.string.nomResponsable))
				.append(postulation.getAnnonce().getEntreprise().getNomResponsable())
				.append("\n")
				.append(getString(R.string.email))
				.append(postulation.getAnnonce().getEntreprise().getUser().getEmail())
				.append("\n")
				.append(getString(R.string.phone))
				.append(postulation.getAnnonce().getEntreprise().getUser().getPhoneNumber())
				.append("\n")
				.append(getString(R.string.adress))
				.append("\n")
				.append(getString(R.string.zip))
				.append(postulation.getAnnonce().getAdresse().getLocalite().getCodePostal())
				.append("\n")
				.append(getString(R.string.locality))
				.append(postulation.getAnnonce().getAdresse().getLocalite().getNom())
				.append("\n")
				.append(getString(R.string.road))
				.append(postulation.getAnnonce().getAdresse().getRue())
				.append("\n")
				.append(getString(R.string.number))
				.append(postulation.getAnnonce().getAdresse().getNumero())
				.append("\n")
				.toString());


		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				button.setEnabled(false);
				new AnnulerPost().execute(postulation.getId());
			}
		});
	}

	private class AnnulerPost extends AsyncTask<Integer, Void, Void> {
		@Override
		protected Void doInBackground(Integer... id) {
			try {
				Response<Void> response = new AnnonceDao().annulerPost(id[0]);

				if (response.isSuccessful() && response.code() == 204) {
					return null;
				}
				runOnUiThread(() -> Toast.makeText(PostulationActivity.this, getString(Utils.msgErreur(response)), Toast.LENGTH_LONG).show());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

	}

}
