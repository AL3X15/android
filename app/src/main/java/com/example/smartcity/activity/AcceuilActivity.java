package com.example.smartcity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartcity.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcceuilActivity extends AppCompatActivity {

	@BindView(R.id.buttonEditProfile)
	public Button editProfile;
	@BindView(R.id.buttonSchedule)
	public Button schedule;
	@BindView(R.id.buttonSearch)
	public Button search;
	@BindView(R.id.buttonFaq)
	public Button faq;
	@BindView(R.id.buttonDisconnect)
	public Button disconnect;
	@BindView(R.id.buttonPassword)
	public Button changePassword;
	@BindView(R.id.buttonDelete)
	public Button deleteAccount;

	public Boolean click;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acceuil);
		ButterKnife.bind(this);

		editProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AcceuilActivity.this, EditProfilActivity.class));
			}
		});
		schedule.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AcceuilActivity.this, EmploiDuTempsActivity.class));
			}
		});
		search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AcceuilActivity.this, RechercheAnnonceActivity.class));
			}
		});
		faq.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AcceuilActivity.this, FaqActivity.class));
			}
		});
		disconnect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AcceuilActivity.this, ConnexionActivity.class));
			}
		});
		changePassword.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AcceuilActivity.this, PasswordActivity.class));
			}
		});
		PopupWindow popUp = new PopupWindow(this);
		click = true;
		deleteAccount.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO popup confirmation
			}
		});

	}

	@Override
	public void onBackPressed() {

	}
}
/*
public class ShowPopUp extends Activity {
	PopupWindow popUp;
	boolean click = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		popUp = new PopupWindow(this);
		LinearLayout layout = new LinearLayout(this);
		LinearLayout mainLayout = new LinearLayout(this);
		TextView tv = new TextView(this);
		Button but = new Button(this);
		but.setText("Click Me");
		but.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (click) {
					popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
					popUp.update(50, 50, 300, 80);
					click = false;
				} else {
					popUp.dismiss();
					click = true;
				}
			}
		});

		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		layout.setOrientation(LinearLayout.VERTICAL);
		tv.setText("Hi this is a sample text for popup window");
		layout.addView(tv, params);
		popUp.setContentView(layout);
		// popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
		mainLayout.addView(but, params);
		setContentView(mainLayout);
	}
}
*/