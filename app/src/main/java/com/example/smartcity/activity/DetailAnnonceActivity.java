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
import com.example.smartcity.R;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.Tag;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class DetailAnnonceActivity extends AppCompatActivity {

	@BindView(R.id.EntrepriseNom)
	public TextView entrepriseNom;
	@BindView(R.id.DetailEntrepriseDescription)
	public TextView details;
	@BindView(R.id.tagsDescription)
	public RecyclerView recyclerView;
	@BindView(R.id.AcceptAnnonce)
	public Button button;
	private TagAdapter adapter;
	Annonce annonce;
	//UserEtudiant userEtudiant;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_annonce);
		ButterKnife.bind(this);

		annonce = (Annonce) getIntent().getSerializableExtra(getString(R.string.annonce));

		//LoadEntreprise loadEntreprise = new LoadEntreprise();
		//loadEntreprise.execute(annonce);

		details.setText(annonce.toString());

		adapter = new TagAdapter();

		//userEtudiant = ((MyApplication) this.getApplication()).getInfoConnection().getUserEtudiant();

		//LoadTag loadTag = new LoadTag();
		//loadTag.execute(annonce);

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				button.setEnabled(false);
				new AccepterOffre().execute(annonce.getId());
			}
		});
	}

	public void errorMessage(String error) {
		Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
	}

	private class TagViewHolder extends RecyclerView.ViewHolder {
		public TextView tag;

		public TagViewHolder(@NonNull View itemView) {
			super(itemView);
			tag = itemView.findViewById(R.id.tagDescription);
		}
	}

	private class TagAdapter extends RecyclerView.Adapter<TagViewHolder> {
		private ArrayList<Tag> myTags;

		@NonNull
		@Override
		public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_description_element, parent, false);
			TagViewHolder vh = new TagViewHolder(v);
			return vh;
		}

		@Override
		public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
			Tag tag = myTags.get(position);
			holder.tag.setText(tag.getNom());
		}

		@Override
		public int getItemCount() {
			return myTags == null ? 0 : myTags.size();
		}

		public void setTags(ArrayList<Tag> myTags) {
			this.myTags = myTags;
			notifyDataSetChanged();
		}
	}
/*
	private class LoadTag extends AsyncTask<Annonce, Void, ArrayList<Tag>> {
		@Override
		protected ArrayList<Tag> doInBackground(Annonce... params) {
			TagDataAccess tagDataAccess = new TagDao();
			ArrayList<Tag> tags = new ArrayList<Tag>();
			try {
				tags = tagDataAccess.getTagsAnnonce(((MyApplication) getApplication()).getInfoConnection().getAccessToken(), params[0]);
			} catch (ApiAccessException e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.accessApiError));
					}
				});
			} catch (AnnonceDontExist e) {
			} catch (Exception e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.connection_error));
					}
				});
			}
			return tags;
		}

		@Override
		protected void onPostExecute(ArrayList<Tag> tags) {
			adapter.setTags(tags);
		}
	}

	private class LoadEntreprise extends AsyncTask<Annonce, Void, UserEntreprise> {
		@Override
		protected UserEntreprise doInBackground(Annonce... params) {
			EntrepriseDataAccess entrepriseDataAccess = new EntrepriseDao();
			try {
				return entrepriseDataAccess.getEntrepriseByAnnonce(((MyApplication) getApplication()).getInfoConnection().getAccessToken().getAccessToken(), params[0]);
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
		protected void onPostExecute(UserEntreprise entreprise) {
			if (entreprise != null)
				entrepriseNom.setText(entreprise.getNom());
		}
	}
*/
	private class AccepterOffre extends AsyncTask<Integer, Void, Void> {
		/*@Override
		protected Void doInBackground(Void... params) {
			AnnonceDataAccess dataAccess = new AnnonceDao();
			try {
				dataAccess.acceptAnnonce(((MyApplication) getApplication()).getInfoConnection().getAccessToken(), annonce, ((MyApplication) getApplication()).getInfoConnection().getUserEtudiant());
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
			} catch (AlreadyPostul e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.deja_postule));
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

		 */
		@Override
		protected Void doInBackground(Integer... id) {
			try {
				Response<Void> response = new AnnonceDao().acceptAnnonce(id[0]);

				if (response.isSuccessful() && response.code() == 200) {
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
