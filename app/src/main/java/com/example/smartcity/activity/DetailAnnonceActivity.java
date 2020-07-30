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
import com.example.smartcity.model.Annonce;

import java.io.IOException;

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

		annonce = (Annonce) getIntent().getSerializableExtra(getString(R.string.annonce));

		details.setText(annonce.toString());
		entrepriseNom.setText(annonce.getEntreprise().getUser().getNom());


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
				//TODO vérifier si ca marche
				runOnUiThread(() -> {Toast.makeText(DetailAnnonceActivity.this, "Erreur : " + response.code(), Toast.LENGTH_LONG).show();
					try {
						Toast.makeText(DetailAnnonceActivity.this, "Échec : " + response.errorBody().string(), Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

	}

}
