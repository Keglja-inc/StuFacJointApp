package com.example.stufacjoint;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

public class WebLokacija extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_lokacija);
		
		WebView web=(WebView)findViewById(R.id.webView1);
		web.loadUrl("https://www.google.hr/maps/place/Fakultet+organizacije+i+informatike,+Sveu%C4%8Dili%C5%A1te+u+Zagrebu,+42000,+Vara%C5%BEdin/@46.3076166,16.3381411,17z/data=!3m1!4b1!4m2!3m1!1s0x4768aad79e27d3a7:0x81f1b23b4ffa2fe4?hl=hr");
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
				Toast.makeText(this, "Opcija je onemoogućena", Toast.LENGTH_LONG).show();
			}
			return true;
			
			
		}
		return super.onOptionsItemSelected(item);
	}
}
