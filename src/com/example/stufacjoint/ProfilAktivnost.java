package com.example.stufacjoint;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;


public class ProfilAktivnost extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profil_aktivnost);
		
		
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
		// as you specify a parent activity in AndroidManifest.xml.
		SharedPreferences preferences =PreferenceManager.getDefaultSharedPreferences(this);
		boolean ugasiDialog = preferences.getBoolean("otvaranje_dialoga", true);
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent novi=new Intent(this, SettingsActivity.class);
			startActivity(novi);
			return true;
		}else if(id==R.id.o_aplikaciji){
			if(ugasiDialog){
			ORazvoju sd = new ORazvoju(this);
			sd.show();
			}else{
				Toast.makeText(this, "Opcija je onemooguæena", Toast.LENGTH_LONG).show();
			}
			return true;
			
			
		}
		return super.onOptionsItemSelected(item);
	}
	

	
	float x1 = 0, x2 = 0;
	public boolean onTouchEvent(MotionEvent touchevent) {		
		// check if swipe is enabled in the preferences by the user
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		boolean swipeEnabled = preferences.getBoolean("pomicanjesvajpova", true);		
		if(!swipeEnabled) return false;
		
		switch (touchevent.getAction()) {
			case MotionEvent.ACTION_DOWN: {
				x1 = touchevent.getX();
				break;
			}
			case MotionEvent.ACTION_UP: {
				x2 = touchevent.getX();
				if (x1 < x2) {
					onBackPressed();
					Intent i=new Intent(getApplicationContext(), GlavnaAktivnost.class);
					startActivity(i);
					
					
				}
			}
		}
		return false;
	

	}
	}
