package com.example.stufacjoint;

import java.io.IOException;
import java.net.MalformedURLException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;

@SuppressLint("ShowToast")
public class LoginAktivnost extends Activity {

	Facebook fb;

	private EditText ime = null;
	private EditText lozinka = null;
	private TextView attempts;
	private Button login;
	int counter = 3;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_aktivnos);

		ime = (EditText) findViewById(R.id.editText1);
		lozinka = (EditText) findViewById(R.id.editText2);
		attempts = (TextView) findViewById(R.id.Brojcanik);
		attempts.setText(Integer.toString(counter));
		login = (Button) findViewById(R.id.logingumb);

		String app_id = getString(R.string.facebook_app_id);
		fb = new Facebook(app_id);

		Button facebook = (Button) findViewById(R.id.facebookgumb);

		facebook.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (fb.isSessionValid()) {

					try {

						fb.logout(getApplicationContext());

					} catch (MalformedURLException e) {
						e.printStackTrace();

					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {

					fb.authorize(LoginAktivnost.this, new String[] { "email" },
							new DialogListener() {

								@Override
								public void onFacebookError(FacebookError e) {
									Toast.makeText(LoginAktivnost.this,
											"Nemate pristup internetu",
											Toast.LENGTH_SHORT).show();

								}

								@Override
								public void onError(DialogError e) {
									Toast.makeText(
											LoginAktivnost.this,
											"U ovom trenutku nije moguće pristupiti aplikaciji",
											Toast.LENGTH_SHORT).show();

								}

								@Override
								public void onComplete(Bundle values) {
									Intent i = new Intent(
											getApplicationContext(),
											GlavnaAktivnost.class);
									startActivity(i);

								}

								@Override
								public void onCancel() {
									Toast.makeText(LoginAktivnost.this,
											"Prekinuti login",
											Toast.LENGTH_SHORT).show();

								}

							});

				}

			}

		});

		login.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				if (ime.getText().toString().equals("admin")
						&& lozinka.getText().toString().equals("admin")) {
					Intent i = new Intent(getApplicationContext(),
							GlavnaAktivnost.class);
					startActivity(i);

				}

				else {
					Toast.makeText(getApplicationContext(),
							"Pokušajte ponovno", Toast.LENGTH_SHORT).show();
					attempts.setBackgroundColor(Color.RED);
					counter--;
					attempts.setText(Integer.toString(counter));
					if (counter == 0) {
						login.setEnabled(false);
					}

				}

			}

		});
	}
}
