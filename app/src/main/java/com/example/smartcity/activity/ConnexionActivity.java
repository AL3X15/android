package com.example.smartcity.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
	private AccessToken accessToken;
	private Activity activity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);
		ButterKnife.bind(this);
		activity = this;

		logInBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (mail.getText().toString().matches(".+@.+\\..+")) {
					if (CheckIntenetConnection.checkConnection(ConnexionActivity.this)) {
						new Connection().execute(new InfoConnection(mail.getText().toString(), password.getText().toString()));
					} else {
						Toast.makeText(ConnexionActivity.this, getString(R.string.connection_error), Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(ConnexionActivity.this, getString(R.string.mail_error), Toast.LENGTH_SHORT).show();
					mail.setBackgroundColor(Color.parseColor("#FF0000"));
				}
			}
		});
		signInBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(ConnexionActivity.this, InscriptionActivity.class);
				startActivity(intent);
			}
		});
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
				return null;

			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}

		}


		protected void onPostExecute(AccessToken accessToken) {
			if (accessToken != null) {
				Context context = activity.getBaseContext();
				AuthSessionService.setToken(context, accessToken.getAccessToken());
				startActivity(new Intent(ConnexionActivity.this, AcceuilActivity.class));
			}
		}
	}

}
