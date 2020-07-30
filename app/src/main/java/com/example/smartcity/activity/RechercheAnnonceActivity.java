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
/*
	public void errorMessage(String error) {
		Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
	}

	public void updateTag(Tag tag) {
		if (tagsEtudiant.contains(tag)) tagsEtudiant.remove(tag);
		else tagsEtudiant.add(tag);
	}

	private class TagRechercheViewHolder extends RecyclerView.ViewHolder {
		public Switch tag;

		public TagRechercheViewHolder(@NonNull View itemView, OnItemSelectedListener listener) {
			super(itemView);
			tag = itemView.findViewById(R.id.switch1);
			tag.setOnClickListener(e -> {
				int currentPosition = getAdapterPosition();
				listener.onItemSelected(currentPosition);
			});
		}

	}

	private class TagRechercheAdapter extends RecyclerView.Adapter<TagRechercheViewHolder> {
		private ArrayList<Tag> myTags;

		@NonNull
		@Override
		public TagRechercheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_recherche_element, parent, false);
			TagRechercheViewHolder vh = new TagRechercheViewHolder(v, position -> {
				Tag tagSelect = myTags.get(position);
				updateTag(tagSelect);
			});
			return vh;
		}

		@Override
		public void onBindViewHolder(@NonNull TagRechercheViewHolder holder, int position) {
			Tag tag = myTags.get(position);
			holder.tag.setText(tag.getNom());
			if (tagsEtudiant.contains(tag)) holder.tag.setChecked(true);
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


		private class LoadTagEtudiant extends AsyncTask<UserEtudiant, Void, ArrayList<Tag>> {
			@Override
			protected ArrayList<Tag> doInBackground(UserEtudiant... userEtudiants) {
				TagDataAccess tagDataAccess = new TagDao();
				try {
					return tagDataAccess.getTagsEtudiant(((MyApplication) getApplication()).getInfoConnection().getAccessToken(), userEtudiants[0]);
				} catch (ApiAccessException e) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							errorMessage(getString(R.string.accessApiError));
						}
					});
				} catch (NoTag e) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							errorMessage(getString(R.string.tag_errors));
						}
					});
				} catch (NothingFoundException e) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							errorMessage(getString(R.string.recherche_error));
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
			protected void onPostExecute(ArrayList<Tag> tags) {
				if (tags != null) {
					tagsEtudiant = tags;
				}
				LoadAllTags loadAllTags = new LoadAllTags();
				loadAllTags.execute();
			}

		}

	private class LoadAllTags extends AsyncTask<Void, Void, ArrayList<TagClasse>> {
		@Override
		protected ArrayList<Tag> doInBackground(Void... voids) {
			TagDataAccess tagDataAccess = new TagDao();
			try {
				return tagDataAccess.getAllTag(((MyApplication) getApplication()).getInfoConnection().getAccessToken());
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
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Tag> tags) {
			adapter.setTags(tags);
			search.setEnabled(true);
		}
		@Override
		protected ArrayList<TagClasse> doInBackground(Void... voids) {
			try {
				Response<ArrayList<TagClasse>> response = new TagDao().GetTags();

				if (response.isSuccessful() && response.code() == 200) {
					return response.body();
				}

				runOnUiThread(() -> {
					Toast.makeText(RechercheAnnonceActivity.this, "Erreur : " + response.code(), Toast.LENGTH_LONG).show();
					try {
						Toast.makeText(RechercheAnnonceActivity.this, "Échec : " + response.errorBody().string(), Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(ArrayList<TagClasse> tags) {
			adapter.setTags(tags);
			search.setEnabled(true);
		}
	}*/
}
