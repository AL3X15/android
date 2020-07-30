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
import com.example.smartcity.Utils.Utils;
import com.example.smartcity.model.Annonce;
import com.example.smartcity.model.CritereRecherche;
import com.example.smartcity.model.PageResultAnnonce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class ResultatActivity extends AppCompatActivity {

	@BindView(R.id.AnnoncesResultat)
	public RecyclerView recyclerView;
	@BindView(R.id.next)
	public Button next;
	@BindView(R.id.prec)
	public Button prec;

	private AnnonceAdapter adapter;
	private CritereRecherche critereRecherche;
	private int page;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultat);
		ButterKnife.bind(this);
		adapter = new AnnonceAdapter();
		page = 1;

		critereRecherche = new CritereRecherche();
		critereRecherche.setDateDebut(new Date());
		critereRecherche.setDateDebut(Utils.stringToDate(getIntent().getStringExtra(getString(R.string.date_start))));
		critereRecherche.setDateFin(new Date());
		critereRecherche.setDateFin(Utils.stringToDate(getIntent().getStringExtra(getString(R.string.date_end))));
		new LoadAnnonce().execute(critereRecherche);

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);


		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				page++;
				new LoadAnnonce().execute(critereRecherche);

				recyclerView.setLayoutManager(new LinearLayoutManager(ResultatActivity.this));
				recyclerView.setAdapter(adapter);
			}
		});
		prec.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				page--;
				new LoadAnnonce().execute(critereRecherche);

				recyclerView.setLayoutManager(new LinearLayoutManager(ResultatActivity.this));
				recyclerView.setAdapter(adapter);
			}
		});

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

	private class LoadAnnonce extends AsyncTask<CritereRecherche, Void, PageResultAnnonce> {
		@Override
		protected PageResultAnnonce doInBackground(CritereRecherche... critereRecherches) {
			try {
				Response<PageResultAnnonce> response = new AnnonceDao().getResultatSerch(page, critereRecherches[0]);

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

		protected void onPostExecute(PageResultAnnonce annonces) {
				page = annonces.getPageIndex();
				prec.setEnabled(annonces.getPageIndex() > 1);
				next.setEnabled((annonces.getPageIndex()-1)*annonces.getPageSize()+annonces.getAnnonces().size() < annonces.getTotalCount());
				adapter.setMyAnnonces(annonces.getAnnonces());//TODO commentaire pour test
		}
	}
}
