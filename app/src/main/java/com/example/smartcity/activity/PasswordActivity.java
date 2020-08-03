package com.example.smartcity.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.DataAccess.dao.UserDao;
import com.example.smartcity.R;
import com.example.smartcity.Utils.Utils;
import com.example.smartcity.model.ChangePassword;
import com.example.smartcity.model.Etudiant;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;
import service.AuthSessionService;

public class PasswordActivity extends AppCompatActivity {
	@BindView(R.id.oldPassword)
	public EditText oldPassword;
	@BindView(R.id.newPassword)
	public EditText newPassword;
	@BindView(R.id.newPasswordConfirmation)
	public EditText newPasswordConfirmation;
	@BindView(R.id.buttonEditPassword)
	public Button buttonEditPassword;

	ChangePassword changePassword;
	Etudiant me;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		ButterKnife.bind(this);

		new GetUser().execute();

		buttonEditPassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkForm()) {
					changePassword = new ChangePassword();
					changePassword.setAncienPassword(oldPassword.getText().toString());
					changePassword.setPassword(newPassword.getText().toString());
					changePassword.setConfirmationPassword(newPasswordConfirmation.getText().toString());
					changePassword.setRowVersion(me.getUser().getRowVersion());

					new EditPassword().execute(changePassword);
				}
			}
		});
	}

	private Boolean checkForm() {
		Boolean success = true;

		if (oldPassword.getText().toString().isEmpty()) {
			oldPassword.setError(getResources().getString(R.string.error_empty));
			success = false;
		}

		if (newPassword.getText().toString().isEmpty()) {
			newPassword.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (newPassword.getText().toString().equals(oldPassword.getText().toString())) {
			newPassword.setError(getResources().getString(R.string.newPasswordError));
			success = false;
		}

		if (newPasswordConfirmation.getText().toString().isEmpty()) {
			newPasswordConfirmation.setError(getResources().getString(R.string.error_empty));
			success = false;
		} else if (!newPasswordConfirmation.getText().toString().equals(newPassword.getText().toString())) {
			newPasswordConfirmation.setError(getResources().getString(R.string.register_confirm_pass_matche));
			success = false;
		}

		return success;
	}

	private class EditPassword extends AsyncTask<ChangePassword, Void, Void> {
		@Override
		protected Void doInBackground(ChangePassword... changePassword) {
			try {
				Response<Void> response = new UserDao().editPassword(changePassword[0]);

				if (response.isSuccessful() && response.code() == 204) {
					return null;
				}

				runOnUiThread(() -> Toast.makeText(PasswordActivity.this, getString(Utils.msgErreur(response)), Toast.LENGTH_LONG).show());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(Void v) {
			AuthSessionService.disconnectUser(PasswordActivity.this);
			startActivity(new Intent(PasswordActivity.this, ConnexionActivity.class));

		}
	}

	private class GetUser extends AsyncTask<Void, Void, Etudiant> {

		@Override
		protected Etudiant doInBackground(Void... voids) {
			try {
				Response<Etudiant> response = new UserDao().getMe();

				if (response.isSuccessful() && response.code() == 200) {
					return response.body();
				}

				runOnUiThread(() -> Toast.makeText(PasswordActivity.this, getString(Utils.msgErreur(response)), Toast.LENGTH_LONG).show());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(Etudiant etudiant) {
			if (etudiant != null) {
				me = etudiant;
			}
		}
	}
}
