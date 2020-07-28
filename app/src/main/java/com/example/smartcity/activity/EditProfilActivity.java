package com.example.smartcity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.smartcity.DataAccess.dao.TagDao;
import com.example.smartcity.DataAccess.dao.TagDataAccess;
import com.example.smartcity.DataAccess.dao.UserDao;
import com.example.smartcity.DataAccess.dao.UserDataAccess;
import com.example.smartcity.Exception.ApiAccessException;
import com.example.smartcity.Exception.NoTag;
import com.example.smartcity.MyApplication;
import com.example.smartcity.R;
import com.example.smartcity.model.Adresse;
import com.example.smartcity.model.UserEtudiant;
import com.example.smartcity.model.Tag;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class EditProfilActivity extends AppCompatActivity {

	@BindView(R.id.TagsEdit)
	RecyclerView tagRecyclerView;
	private TagAdapter adapter;
	private ArrayList<Tag> tagsEtudiant;
	private UserEtudiant userEtudiant;

	@BindView(R.id.roadEdit)
	public EditText roadEdit;
	@BindView(R.id.numberEdit)
	public EditText numberEdit;
	@BindView(R.id.zipEdit)
	public EditText zipEdit;
	@BindView(R.id.localityEdit)
	public EditText localityEdit;
	@BindView(R.id.phoneEdit)
	public EditText phoneEdit;
	@BindView(R.id.mailEdit)
	public EditText mailEdit;
	@BindView(R.id.passwordPreviousEdit)
	public EditText previousPassword;
	@BindView(R.id.passwordEdit)
	public EditText passwordEdit;
	@BindView(R.id.validateEdit)
	public Button validateEdit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profil);
		ButterKnife.bind(this);
		validateEdit.setEnabled(false);

		userEtudiant = ((MyApplication) this.getApplication()).getInfoConnection().getUserEtudiant();

		adapter = new TagAdapter();
		tagsEtudiant = new ArrayList<>();


		//LoadTagEtudiant loadTagEtudiant = new LoadTagEtudiant();
		//loadTagEtudiant.execute(userEtudiant);

		tagRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		tagRecyclerView.setAdapter(adapter);


		roadEdit.setText(userEtudiant.getEtudiant().getAdresse().getRue());
		numberEdit.setText(userEtudiant.getEtudiant().getAdresse().getNumero());
		zipEdit.setText(userEtudiant.getEtudiant().getAdresse().getLocalite().getCodePostal().toString());
		localityEdit.setText(userEtudiant.getEtudiant().getAdresse().getLocalite().getNom());
		phoneEdit.setText(userEtudiant.getPhoneNumber().toString());
		mailEdit.setText(userEtudiant.getEmail());

		validateEdit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean mailValide = mailEdit.getText().toString().matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
				boolean passwordValide = (passwordEdit.getText().toString().isEmpty() && previousPassword.getText().toString().isEmpty()) || (!previousPassword.getText().toString().isEmpty() && passwordEdit.getText().toString().matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$"));
				boolean previousPasswordValide = previousPassword.getText().toString().isEmpty() || previousPassword.getText().toString().matches("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$");
				boolean formulaireValide = passwordValide && mailValide && previousPasswordValide;
				if (formulaireValide) {
					userEtudiant.setEmail(mailEdit.getText().toString());
					if (!passwordEdit.getText().toString().isEmpty()) {
						userEtudiant.setPassword(passwordEdit.getText().toString());
						userEtudiant.setConfirmationPassword(passwordEdit.getText().toString());
					}
					if (!previousPassword.getText().toString().isEmpty())
						userEtudiant.setPreviousPassword(previousPassword.getText().toString());
					userEtudiant.setPhoneNumber(phoneEdit.getText().toString());
					userEtudiant.getEtudiant().setAdresse(new Adresse(
							roadEdit.getText().toString(),
							numberEdit.getText().toString(),
							zipEdit.getText().toString(),
							localityEdit.getText().toString()
					));
					userEtudiant.getEtudiant().setTags(tagsEtudiant);
					new EditProfile().execute(userEtudiant);
					((MyApplication) getApplication()).getInfoConnection().setUserEtudiant(userEtudiant);
					startActivity(new Intent(EditProfilActivity.this, AcceuilActivity.class));
				}


			}
		});

	}

	public void errorMessage(String error) {
		Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
	}

	public void updateTag(Tag tag) {
		if (tagsEtudiant.contains(tag)) tagsEtudiant.remove(tag);
		else tagsEtudiant.add(tag);
	}


	private class TagViewHolder extends RecyclerView.ViewHolder {
		public Switch tag;

		public TagViewHolder(@NonNull View itemView, OnItemSelectedListener listener) {
			super(itemView);
			tag = itemView.findViewById(R.id.switch1);
			tag.setOnClickListener(e -> {
				int currentPosition = getAdapterPosition();
				listener.onItemSelected(currentPosition);
			});
		}

	}

	private class TagAdapter extends RecyclerView.Adapter<TagViewHolder> {
		private ArrayList<Tag> myTags;

		@NonNull
		@Override
		public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_recherche_element, parent, false);
			TagViewHolder vh = new TagViewHolder(v, position -> {
				Tag tagSelect = myTags.get(position);
				updateTag(tagSelect);
			});
			return vh;
		}

		@Override
		public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
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
/*
	private class LoadTagEtudiant extends AsyncTask<UserEtudiant, Void, ArrayList<Tag>> {
		@Override
		protected ArrayList<Tag> doInBackground(UserEtudiant... userEtudiants) {
			try {
				TagDataAccess tagDataAccess = new TagDao();
				return tagDataAccess.getTagsEtudiant(((MyApplication) getApplication()).getInfoConnection().getAccessToken(), userEtudiants[0]);
			} catch (ApiAccessException e) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						errorMessage(getString(R.string.accessApiError));
					}
				});
			} catch (NoTag e) {

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
*/
	private class LoadAllTags extends AsyncTask<Void, Void, ArrayList<Tag>> {
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
			validateEdit.setEnabled(true);
		}
	}

	private class EditProfile extends AsyncTask<UserEtudiant, Void, Void> {
		/*@Override
		protected UserEtudiant doInBackground(UserEtudiant... userEtudiants) {
			UserDataAccess userDataAccess = new UserDao();
			try {
				userDataAccess.editMe(((MyApplication) getApplication()).getInfoConnection().getAccessToken(), userEtudiants[0]);
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
			return userEtudiants[0];
		}

		@Override
		protected void onPostExecute(UserEtudiant userEtudiant) {
			((MyApplication) getApplication()).getInfoConnection().setUserEtudiant(userEtudiant);
			startActivity(new Intent(EditProfilActivity.this, AcceuilActivity.class));
		}*/
		@Override
		protected Void doInBackground(UserEtudiant... userEtudiants) {
			try {
				Response<Void> response = new UserDao().editMe(userEtudiants[0]);

				if (response.isSuccessful() && response.code() == 200) {
					return null;
				}
				//TODO vérifier si ca marche
				runOnUiThread(() -> {Toast.makeText(EditProfilActivity.this, "Erreur : " + response.code(), Toast.LENGTH_LONG).show();
					try {
						Toast.makeText(EditProfilActivity.this, "Échec : " + response.errorBody().string(), Toast.LENGTH_LONG).show();
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
