package com.example.stufacjoint;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.stufacjoint.rsss.RssItem;
import com.example.stufacjoint.rsss.RssReader;

public class GlavnaAktivnost extends Activity {

	String a;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glavna_aktivnost);

		rss();

		ImageButton pocetna = (ImageButton) findViewById(R.id.homegumb);
		pocetna.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						LoginAktivnost.class);
				startActivity(i);

			}
		});

		ImageButton vlastitaLista = (ImageButton) findViewById(R.id.profilgumb);
		vlastitaLista.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						ProfilAktivnost.class);
				startActivity(i);

			}
		});

		ImageButton noviPredlozak = (ImageButton) findViewById(R.id.listagumb);
		noviPredlozak.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),
						ListaInteresa.class);
				startActivity(i);

			}
		});

	}

	public void rss() {

		try {
			// Create RSS reader
			RssReader rssReader = new RssReader(
					"http://www.mojamatura.net/index.php?format=feed&type=rss");
			// Get a ListView from main view
			ListView itcItems = (ListView) findViewById(R.id.listView1);

			// Create a list adapter
			ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(this,
					android.R.layout.simple_list_item_1, rssReader.getItems());
			// Set list adapter for the ListView
			itcItems.setAdapter(adapter);

			// Set list view item click listener
			itcItems.setOnItemClickListener(new ListListener(rssReader
					.getItems(), this));

		} catch (Exception e) {
			Log.e("ITCRssReader", e.getMessage());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.glavna_aktivnost, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.ðð
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
				Toast.makeText(this, "Opcija je onemoogućena",
						Toast.LENGTH_LONG).show();
			}
			return true;

		}
		return super.onOptionsItemSelected(item);
	}
}
