package com.example.smartcity.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.DataAccess.dao.AnnonceDao;
import com.example.smartcity.R;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.CritereRecherche;
import com.example.smartcity.model.Tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class ResultatActivity extends AppCompatActivity {

	@BindView(R.id.AnnoncesResultat)
	public RecyclerView recyclerView;
	private AnnonceAdapter adapter;
	private ArrayList<Tag> tags;

	private GregorianCalendar dateFin;
	private GregorianCalendar dateDebut;
	private CritereRecherche critereRecherche;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultat);
		ButterKnife.bind(this);
		adapter = new AnnonceAdapter();

		critereRecherche.setTags(getIntent().getParcelableArrayListExtra(getString(R.string.tags_transfer)));
		critereRecherche.setDateDebut(new GregorianCalendar());
		critereRecherche.getDateDebut().setTime((Date) getIntent().getSerializableExtra(getString(R.string.date_start)));
		critereRecherche.setDateFin(new GregorianCalendar());
		critereRecherche.getDateFin().setTime((Date) getIntent().getSerializableExtra(getString(R.string.date_end)));
		/*tags = getIntent().getParcelableArrayListExtra(getString(R.string.tags_transfer));
		dateDebut = new GregorianCalendar();
		dateDebut.setTime((Date) getIntent().getSerializableExtra(getString(R.string.date_start)));
		dateFin = new GregorianCalendar();
		dateFin.setTime((Date) getIntent().getSerializableExtra(getString(R.string.date_end)));*/
		new LoadAnnonce().execute(critereRecherche);

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}

	public void errorMessage(String error) {
		Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
	}

	private class AnnonceViewHolder extends RecyclerView.ViewHolder {
		public TextView poste;
		public Button moreInfo;

		public AnnonceViewHolder(@NonNull View itemView, OnItemSelectedListener listener) {
			super(itemView);
			poste = itemView.findViewById(R.id.annonce);
			moreInfo = itemView.findViewById(R.id.moredetail);
			moreInfo.setOnClickListener(e -> {
				int cur = getAdapterPosition();
				listener.onItemSelected(cur);
			});
		}
	}

	private class AnnonceAdapter extends RecyclerView.Adapter<AnnonceViewHolder> {
		private ArrayList<Annonce> myAnnonces;

		@NonNull
		@Override
		public AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.annonce_element, parent, false);
			AnnonceViewHolder vh = new AnnonceViewHolder(v, position -> {
				Annonce annonceSelect = myAnnonces.get(position);
				Intent intent = new Intent(ResultatActivity.this, DetailAnnonceActivity.class);
				intent.putExtra(getString(R.string.annonce), annonceSelect);
				startActivity(intent);
			});
			return vh;
		}

		@Override
		public void onBindViewHolder(@NonNull AnnonceViewHolder holder, int position) {
			Annonce annonce = myAnnonces.get(position);
			holder.poste.setText(annonce.getPoste());
		}

		@Override
		public int getItemCount() {
			return myAnnonces == null ? 0 : myAnnonces.size();
		}

		public void setMyAnnonces(ArrayList<Annonce> annonces) {
			this.myAnnonces = annonces;
			notifyDataSetChanged();
			notifyDataSetChanged();
		}
	}

	private class LoadAnnonce extends AsyncTask<CritereRecherche, Void, ArrayList<Annonce>> {
		/*@Override
		protected ArrayList<Annonce> doInBackground(ArrayList<Tag>... tags) {
			AnnonceDataAccess annonceDataAccess = new AnnonceDao();
			try {
				return annonceDataAccess.getResultatSerch(((MyApplication) getApplication()).getInfoConnection().getAccessToken(), dateDebut, dateFin, tags[0]);
			} catch (AnnonceDontExist e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.annonce_error));
					}
				});
			} catch (ApiAccessException e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.accessApiError));
					}
				});
			} catch (Exception e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.connection_error));
					}
				});
				Log.i("erreur", e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Annonce> annonces) {
			adapter.setMyAnnonces(annonces);
		}

		 */
		@Override
		protected ArrayList<Annonce> doInBackground(CritereRecherche... critereRecherches) {
			try {
				Response<ArrayList<Annonce>> response = new AnnonceDao().getResultatSerch(critereRecherches[0]);

				if (response.isSuccessful() && response.code() == 200) {
					return response.body();
				}
				//TODO vérifier si ca marche
				runOnUiThread(() -> {Toast.makeText(ResultatActivity.this, "Erreur : " + response.code(), Toast.LENGTH_LONG).show();
					try {
						Toast.makeText(ResultatActivity.this, "Échec : " + response.errorBody().string(), Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(ArrayList<Annonce> annonces) {
			if(annonces != null) {
				adapter.setMyAnnonces(annonces);
			}
		}
	}
}
