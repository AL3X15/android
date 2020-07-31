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
import com.example.smartcity.model.Postulation;

import java.io.IOException;

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

		postulation = (Postulation) getIntent().getSerializableExtra(getString(R.string.postulation));

		details.setText(postulation.toString());
		entrepriseNom.setText(postulation.getAnnonce().getEntreprise().getUser().getNom());

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
				//TODO vérifier si ca marche
				runOnUiThread(() -> {
					Toast.makeText(PostulationActivity.this, "Erreur : " + response.code(), Toast.LENGTH_LONG).show();
					try {
						Toast.makeText(PostulationActivity.this, "Échec : " + response.errorBody().string(), Toast.LENGTH_LONG).show();
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
