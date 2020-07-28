package com.example.smartcity.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity.DataAccess.dao.FaqDao;
import com.example.smartcity.R;
import com.example.smartcity.model.Faq;
import com.example.smartcity.model.PageResultFaq;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class FaqActivity extends AppCompatActivity {

	@BindView(R.id.faqs)
	RecyclerView recyclerView;
	FaqAdapter adapter;
	int page = 0;
	//TODO pagination
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faq);
		ButterKnife.bind(this);
		adapter = new FaqAdapter();

		LoadFaq loadFaq = new LoadFaq();
		loadFaq.execute(page);

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setAdapter(adapter);
	}

	public void errorMessage(String error) {
		Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
	}

	private class FaqViewHolder extends RecyclerView.ViewHolder {
		public TextView question;
		public TextView reponse;

		public FaqViewHolder(@NonNull View itemView) {
			super(itemView);
			question = itemView.findViewById(R.id.question);
			reponse = itemView.findViewById(R.id.answerd);
		}

		public boolean isAffiche() {
			return reponse.getText().toString().isEmpty();
		}
	}


	private class FaqAdapter extends RecyclerView.Adapter<FaqViewHolder> {
		private ArrayList<Faq> myFaq;

		@NonNull
		@Override
		public FaqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_element, parent, false);
			FaqViewHolder vh = new FaqViewHolder(v);
			return vh;
		}

		@Override
		public void onBindViewHolder(@NonNull FaqViewHolder holder, int position) {
			Faq annonce = myFaq.get(position);
			holder.question.setText(annonce.getQuestion());
			holder.question.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int currentPosition = holder.getAdapterPosition();
					if (!holder.isAffiche()) {
						Faq faqSelect = myFaq.get(position);
						holder.reponse.setText(faqSelect.getReponse());
					} else {
						holder.reponse.setText(getString(R.string.void_string));
					}
				}
			});
		}

		@Override
		public int getItemCount() {
			return myFaq == null ? 0 : myFaq.size();
		}

		public void setFaq(ArrayList<Faq> myFaqs) {
			this.myFaq = myFaqs;
			notifyDataSetChanged();
		}
	}

	private class LoadFaq extends AsyncTask<Integer, Void, PageResultFaq> {
		/*@Override
		protected ArrayList<Faq> doInBackground(Void... voids) {
			FaqDataAccess access = new FaqDao();
			try {
				UserEtudiant userEtudiant = ((MyApplication) getApplication()).getInfoConnection().getUserEtudiant();
				return access.getAllFaq(((MyApplication) getApplication()).getInfoConnection().getAccessToken());
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
		protected void onPostExecute(ArrayList<Faq> faqs) {
			adapter.setFaq(faqs);
		}*/
		@Override
		protected PageResultFaq doInBackground(Integer... page) {
			try {
				Response<PageResultFaq> response = new FaqDao().getFaq(page[0]);

				if (response.isSuccessful() && response.code() == 200) {
					return response.body();
				}
				//TODO vérifier si ca marche
				runOnUiThread(() -> {Toast.makeText(FaqActivity.this, "Erreur : " + response.code(), Toast.LENGTH_LONG).show();
					try {
						Toast.makeText(FaqActivity.this, "Échec : " + response.errorBody().string(), Toast.LENGTH_LONG).show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;

		}
		@Override
		protected void onPostExecute(PageResultFaq pageResultFaq) {
			page = pageResultFaq.getPageindex();
			adapter.setFaq(pageResultFaq.getFaqs());
		}
	}
}
