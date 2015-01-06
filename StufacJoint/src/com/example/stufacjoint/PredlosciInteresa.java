package com.example.stufacjoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class PredlosciInteresa extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_predlosci_interesa);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.predlosci_interesa, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Toast.makeText(PredlosciInteresa.this, "Dio aplikacije koji je još u razvoju...", Toast.LENGTH_LONG).show();
			return true;
			
		}else if(id==R.id.o_aplikaciji){
			o_razvoju sd = new o_razvoju(this);
			sd.show();
			return true;
			
		}
		return super.onOptionsItemSelected(item);
	}
}
