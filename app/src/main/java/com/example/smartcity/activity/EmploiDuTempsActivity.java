package com.example.smartcity.activity;

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
import com.example.smartcity.MyApplication;
import com.example.smartcity.R;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.PageResultPostulation;
import com.example.smartcity.model.UserEtudiant;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class EmploiDuTempsActivity extends AppCompatActivity {

	@BindView(R.id.ListAnnonce)
	public RecyclerView recyclerView;
	private AnnonceAdapter adapter;
	private UserEtudiant userEtudiant;
//TODO pagination
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emploi_du_temps);
		ButterKnife.bind(this);

		userEtudiant = ((MyApplication) this.getApplication()).getInfoConnection().getUserEtudiant();

		adapter = new AnnonceAdapter();

		LoadAnnonce loadAnnonce = new LoadAnnonce();
		loadAnnonce.execute();

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}

	public void errorMessage(String error) {
		Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
	}

	private class AnnonceViewHolder extends RecyclerView.ViewHolder {

		public TextView horraire;
		public TextView activite;
		public Button localise;

		public AnnonceViewHolder(@NonNull View itemView, OnItemSelectedListener listener) {
			super(itemView);
			horraire = itemView.findViewById(R.id.horraire);
			activite = itemView.findViewById(R.id.descElemHorraire);
			localise = itemView.findViewById(R.id.localise);
			localise.setOnClickListener(e -> {
				int currentPosition = getAdapterPosition();
				listener.onItemSelected(currentPosition);
			});
		}
	}

	private class AnnonceAdapter extends RecyclerView.Adapter<AnnonceViewHolder> {
		private ArrayList<Annonce> myAnnonces;

		@NonNull
		@Override
		public AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.horraire_element, parent, false);
			AnnonceViewHolder vh = new AnnonceViewHolder(v, position -> {
                /*Annonce annonceSelect = myAnnonces.get(position);
                Uri gmmIntentUri = Uri.parse(getString(R.string.uri_map)+annonceSelect.getEntreprise().getAdresse().toString());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage(getString(R.string.map_intent));
                startActivity(mapIntent);*/

			});
			return vh;
		}

		@Override
		public void onBindViewHolder(@NonNull AnnonceViewHolder holder, int position) {
			Annonce annonce = myAnnonces.get(position);
			holder.activite.setText(annonce.getPoste());
			holder.horraire.setText(annonce.getDateDebut() + getString(R.string.tiret) + annonce.getDateFin());
			//holder.localise.setText(annonce.getEntreprise().getAdresse().toString());
		}

		@Override
		public int getItemCount() {
			return myAnnonces == null ? 0 : myAnnonces.size();
		}

		public void setAnnonces(ArrayList<Annonce> myAnnonces) {
			this.myAnnonces = myAnnonces;
			notifyDataSetChanged();
		}
	}

	private class LoadAnnonce extends AsyncTask<Integer, Void, PageResultPostulation> {
		/*@Override
		public ArrayList<Annonce> doInBackground(UserEtudiant... userEtudiants) {
			AnnonceDataAccess annonceDataAccess = new AnnonceDao();
			try {
				return annonceDataAccess.getAnnonceEtudiant(((MyApplication) getApplication()).getInfoConnection().getAccessToken(), userEtudiants[0]);
			} catch (EtudiantDontExist e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.etudiant_error));
					}
				});
			} catch (ApiAccessException e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.accessApiError));
					}
				});
			} catch (NothingFoundException e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.emploieDuTemp_error));
					}
				});
			} catch (Exception e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.connection_error));
					}
				});
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Annonce> annonces) {
			if (annonces != null) {
				//LoadEntrepriseAnnonces loadEntreprise = new LoadEntrepriseAnnonces();
				//loadEntreprise.execute((Annonce[]) annonces.toArray());
			}

		 */

		@Override
		protected PageResultPostulation doInBackground(Integer... ligne) {
			try {
				Response<PageResultPostulation> response = new AnnonceDao().getAnnonceEtudiant(ligne[0]);

				if (response.isSuccessful() && response.code() == 200) {
					return response.body();
				}
				//TODO vérifier si ca marche
				runOnUiThread(() -> {
					Toast.makeText(EmploiDuTempsActivity.this, "Erreur : " + response.code(), Toast.LENGTH_LONG).show();
					try {
						Toast.makeText(EmploiDuTempsActivity.this, "Échec : " + response.errorBody().string(), Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(PageResultPostulation postulation) {
			//adapter.setAnnonces(postulation);//TODO commentaire pour test
		}
	}
}

	/*private class LoadEntrepriseAnnonces extends AsyncTask<Annonce, Void, ArrayList<Annonce>> {
		@Override
		protected ArrayList<Annonce> doInBackground(Annonce... params) {
			EntrepriseDataAccess entrepriseDataAccess = new EntrepriseDao();
			try {
				ArrayList<Annonce> annoncesComplete = new ArrayList<>();
				for (Annonce a : params) {
					a.setEntreprise(entrepriseDataAccess.getEntrepriseByAnnonce(((MyApplication) getApplication()).getInfoConnection().getAccessToken().getAccessToken(), params[0]));
					annoncesComplete.add(a);
				}
				return annoncesComplete;
			} catch (ApiAccessException e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.accessApiError));
					}
				});
			} catch (AnnonceDontExist e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.annonce_error));
					}
				});
			} catch (Exception e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.connection_error));
					}
				});
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Annonce> annonces) {
			adapter.setAnnonces(annonces);
		}
	}
}
*/