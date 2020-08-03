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
import com.example.smartcity.model.PageResultPostulation;
import com.example.smartcity.model.Postulation;
import com.example.smartcity.service.CheckIntenetConnection;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class EmploiDuTempsActivity extends AppCompatActivity {

	@BindView(R.id.ListAnnonce)
	public RecyclerView recyclerView;
	@BindView(R.id.next3)
	public Button next;
	@BindView(R.id.prec3)
	public Button prec;

	private int page;
	private AnnonceAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emploi_du_temps);
		ButterKnife.bind(this);
		prec.setEnabled(false);
		next.setEnabled(false);

		adapter = new AnnonceAdapter();
		page = 1;

		if (CheckIntenetConnection.checkConnection(EmploiDuTempsActivity.this))
			new LoadAnnonce().execute();
		else {
			Toast.makeText(EmploiDuTempsActivity.this, getString(R.string.connection_error), Toast.LENGTH_LONG).show();

			recyclerView.setLayoutManager(new LinearLayoutManager(this));
			recyclerView.setAdapter(adapter);
		}

		next.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (CheckIntenetConnection.checkConnection(EmploiDuTempsActivity.this)) {
					page++;
					new LoadAnnonce().execute();

					recyclerView.setLayoutManager(new LinearLayoutManager(EmploiDuTempsActivity.this));
					recyclerView.setAdapter(adapter);
				} else {
					Toast.makeText(EmploiDuTempsActivity.this, getString(R.string.connection_error), Toast.LENGTH_LONG).show();
				}
			}
		});
		prec.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (CheckIntenetConnection.checkConnection(EmploiDuTempsActivity.this)) {
					page--;
					new LoadAnnonce().execute();

					recyclerView.setLayoutManager(new LinearLayoutManager(EmploiDuTempsActivity.this));
					recyclerView.setAdapter(adapter);
				} else {
					Toast.makeText(EmploiDuTempsActivity.this, getString(R.string.connection_error), Toast.LENGTH_LONG).show();
				}
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

	private class AnnonceAdapter extends RecyclerView.Adapter<EmploiDuTempsActivity.AnnonceViewHolder> {
		private ArrayList<Postulation> postulations;

		@NonNull
		@Override
		public EmploiDuTempsActivity.AnnonceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.annonce_element, parent, false);
			EmploiDuTempsActivity.AnnonceViewHolder vh = new EmploiDuTempsActivity.AnnonceViewHolder(v, position -> {
				Intent intent = new Intent(EmploiDuTempsActivity.this, PostulationActivity.class);
				intent.putExtra(getString(R.string.postulation), postulations.get(position));
				startActivity(intent);
			});
			return vh;
		}

		@Override
		public void onBindViewHolder(@NonNull EmploiDuTempsActivity.AnnonceViewHolder holder, int position) {
			Postulation postulation = postulations.get(position);
			holder.poste.setText(postulation.getAnnonce().getPoste());
		}

		@Override
		public int getItemCount() {
			return postulations == null ? 0 : postulations.size();
		}

		public void setMyPostulation(ArrayList<Postulation> postulations) {
			this.postulations = postulations;
			notifyDataSetChanged();
			notifyDataSetChanged();
		}
	}

	private class LoadAnnonce extends AsyncTask<Void, Void, PageResultPostulation> {
		@Override
		protected PageResultPostulation doInBackground(Void... voids) {
			try {
				Response<PageResultPostulation> response = new AnnonceDao().getAnnonceEtudiant(page);

				if (response.isSuccessful() && response.code() == 200) {
					return response.body();
				}

				runOnUiThread(() -> Toast.makeText(EmploiDuTempsActivity.this, getString(Utils.msgErreur(response)), Toast.LENGTH_LONG).show());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(PageResultPostulation postulation) {
			page = postulation.getPageIndex();
			prec.setEnabled(postulation.getPageIndex() > 1);
			next.setEnabled((postulation.getPageIndex() - 1) * postulation.getPageSize() + postulation.getItems().size() < postulation.getTotalCount());
			adapter.setMyPostulation(postulation.getItems());
		}
	}
}
