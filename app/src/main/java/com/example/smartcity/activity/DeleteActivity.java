package com.example.smartcity.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.DataAccess.dao.UserDao;
import com.example.smartcity.R;
import com.example.smartcity.Utils.Utils;
import com.example.smartcity.service.CheckIntenetConnection;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import service.AuthSessionService;

public class DeleteActivity extends AppCompatActivity {
	@BindView(R.id.buttonDeleteAccount)
	public Button buttonDeleteAccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
		ButterKnife.bind(this);

		buttonDeleteAccount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (CheckIntenetConnection.checkConnection(DeleteActivity.this))
					new DeleteAccount().execute();
				else
					Toast.makeText(DeleteActivity.this, getString(R.string.connection_error), Toast.LENGTH_LONG).show();
			}
		});

	}

	private class DeleteAccount extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... v) {
			try {
				Response<Void> response = new UserDao().deleteMe();

				if (response.isSuccessful() && response.code() == 204) {
					return null;
				}

				runOnUiThread(() -> Toast.makeText(DeleteActivity.this, getString(Utils.msgErreur(response)), Toast.LENGTH_LONG).show());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(Void v) {
			AuthSessionService.disconnectUser(DeleteActivity.this);
			startActivity(new Intent(DeleteActivity.this, ConnexionActivity.class));

		}
	}
}
