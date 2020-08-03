package com.example.smartcity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.DataAccess.dao.JwtDao;
import com.example.smartcity.R;
import com.example.smartcity.Utils.Utils;
import com.example.smartcity.model.AccessToken;
import com.example.smartcity.model.InfoConnection;
import com.example.smartcity.service.CheckIntenetConnection;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import service.AuthSessionService;

public class ConnexionActivity extends AppCompatActivity {
	@BindView(R.id.logInBtn)
	public Button logInBtn;
	@BindView(R.id.signInBtn)
	public Button signInBtn;
	@BindView(R.id.mailLogIn)
	public TextView mail;
	@BindView(R.id.passwordLogIn)
	public TextView password;
	private Activity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);
		ButterKnife.bind(this);
		activity = this;

		logInBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (checkForm()) {
					InfoConnection infoConnection = new InfoConnection();
					infoConnection.setUsername(mail.getText().toString());
					infoConnection.setPassword(password.getText().toString());

					if (CheckIntenetConnection.checkConnection(ConnexionActivity.this))
						new Connection().execute(infoConnection);
					else
						Toast.makeText(ConnexionActivity.this, getString(R.string.connection_error), Toast.LENGTH_LONG).show();
				}
			}
		});

		signInBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(ConnexionActivity.this, InscriptionActivity.class));
			}
		});
	}

	private Boolean checkForm(){
		Boolean success = true;

		if (mail.getText().toString().isEmpty()) {
			mail.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!mail.getText().toString().matches(".+@.+\\..+")) {
			mail.setError(getResources().getString(R.string.error_matche_email));
			success = false;
		}

		if (password.getText().toString().isEmpty()) {
			password.setError(getResources().getString(R.string.error_empty));
			success = false;
		}

		return success;
	}


	private class Connection extends AsyncTask<InfoConnection, Void, AccessToken> {
		@Override
		protected AccessToken doInBackground(InfoConnection... infoConnections) {
			try {

				Response<AccessToken> response = new JwtDao().getAccessToken(infoConnections[0]);
				if (response.isSuccessful() && response.code() == 200) {
					return response.body();
				}
				runOnUiThread(() -> Toast.makeText(ConnexionActivity.this, getString(Utils.msgErreur(response)), Toast.LENGTH_LONG).show());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(AccessToken accessToken) {
			if (accessToken != null) {
				Context context = activity.getBaseContext();
				AuthSessionService.setToken(context, accessToken.getAccessToken());
				startActivity(new Intent(ConnexionActivity.this, AccueilActivity.class));
			}
		}
	}

	@Override
	public void onBackPressed() {

	}

}
