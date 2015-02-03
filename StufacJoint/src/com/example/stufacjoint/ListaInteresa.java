package com.example.stufacjoint;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ListaInteresa extends ListActivity {

	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static String url = "http://stufacjoint.us.wak-apps.com/ucilista";

	// JSON Node names
	private static final String TAG_PODACI = "podaci";
	private static final String ID_idVisokogUcilista = "idVisokogUcilista";
	private static final String TAG_NAZIV = "naziv";
	private static final String TAG_tipVisokogUcilista_idtipVisokogUcilista = "tipVisokogUcilista_idtipVisokogUcilista";
	private static final String TAG_statusVisokogUcilista_idstatusVisokogUcilista = "statusVisokogUcilista_idstatusVisokogUcilista";
	private static final String TAG_zdanjIzmjena = "zadnjaIzmjena";

	public ListAdapter adapter = null;
	// contacts JSONArray
	JSONArray podaci = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> contactList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_interesa);

		contactList = new ArrayList<HashMap<String, String>>();

		@SuppressWarnings("unused")
		ListView lv = getListView();

		new GetContacts().execute();

		ImageButton pocetna = (ImageButton) findViewById(R.id.homegumb);
		pocetna.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						GlavnaAktivnost.class);
				startActivity(i);

			}
		});

		ImageButton novi_predlozak = (ImageButton) findViewById(R.id.novipredlozak);
		novi_predlozak.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						MojalListaInteresa.class);
				startActivity(i);

			}
		});

	}

	private class GetContacts extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(ListaInteresa.this);
			pDialog.setMessage("Dohvaæanje podataka...prièekajte molim...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);

					// Getting JSON Array node
					podaci = jsonObj.getJSONArray(TAG_PODACI);

					// looping through All Contacts
					for (int i = 0; i < podaci.length(); i++) {
						JSONObject c = podaci.getJSONObject(i);

						String id = c.getString(ID_idVisokogUcilista);
						String name = c.getString(TAG_NAZIV);
						String email = c
								.getString(TAG_tipVisokogUcilista_idtipVisokogUcilista);
						@SuppressWarnings("unused")
						String address = c
								.getString(TAG_statusVisokogUcilista_idstatusVisokogUcilista);
						@SuppressWarnings("unused")
						String gender = c.getString(TAG_zdanjIzmjena);

						// tmp hashmap for single contact
						HashMap<String, String> contact = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						contact.put(ID_idVisokogUcilista, id);
						contact.put(TAG_NAZIV, name);
						contact.put(
								TAG_tipVisokogUcilista_idtipVisokogUcilista,
								email);

						// adding contact to contact list
						contactList.add(contact);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			adapter = new SimpleAdapter(ListaInteresa.this, contactList,
					R.layout.list_item, new String[] { TAG_NAZIV,
							TAG_tipVisokogUcilista_idtipVisokogUcilista,
							ID_idVisokogUcilista }, new int[] { R.id.name,
							R.id.email });

			setListAdapter(adapter);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_interesa, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean ugasiDialog = preferences
				.getBoolean("otvaranje_dialoga", true);

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent novi = new Intent(this, SettingsActivity.class);
			startActivity(novi);
			return true;
		} else if (id == R.id.o_aplikaciji) {
			if (ugasiDialog) {
				ORazvoju sd = new ORazvoju(this);
				sd.show();
			} else {
				Toast.makeText(this, "Opcija je onemooguæena",
						Toast.LENGTH_LONG).show();
			}
			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	float x1 = 0, x2 = 0;

	public boolean onTouchEvent(MotionEvent touchevent) {
		// check if swipe is enabled in the preferences by the user
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean swipeEnabled = preferences.getBoolean("pomicanjesvajpova",
				true);
		if (!swipeEnabled)
			return false;

		switch (touchevent.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			x1 = touchevent.getX();
			break;
		}
		case MotionEvent.ACTION_UP: {
			x2 = touchevent.getX();
			if (x1 < x2) {
				onBackPressed();
				Intent i = new Intent(getApplicationContext(),
						GlavnaAktivnost.class);
				startActivity(i);
				setListAdapter(adapter);

			}
		}
		}
		return false;
	}
}
