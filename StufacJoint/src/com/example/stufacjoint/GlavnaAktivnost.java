package com.example.stufacjoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class GlavnaAktivnost extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_glavna_aktivnost);
		
		
		ImageButton loginforma=(ImageButton)findViewById(R.id.homegumb);
		
		loginforma.setOnClickListener(new OnClickListener() {
					
					
		@Override
					
		public void onClick(View v) {
						
		Intent i=new Intent(getApplicationContext(), LoginAktivnos.class);
						
		startActivity(i);
						
					}
				
		});
				
				
		ImageButton profilgumb=(ImageButton)findViewById(R.id.profilgumb);
				
		profilgumb.setOnClickListener(new OnClickListener() {
					
				
			@Override
					public void onClick(View v) {
			
					Intent i=new Intent(getApplicationContext(),ProfilAktivnost.class);
			
					startActivity(i);

						
					}
				
		});
				
				
		ImageButton lista=(ImageButton)findViewById(R.id.listagumb);

				lista.setOnClickListener(new OnClickListener() {

					
					@Override

					public void onClick(View v) {

						Intent i=new Intent(getApplicationContext(), ListaInteresa.class);

						startActivity(i);

						
					}
				});
			
			
				
				ImageButton infogumb=(ImageButton)findViewById(R.id.infogumb);
			
			infogumb.setOnClickListener(new OnClickListener() {
					
					
		@Override
					public void onClick(View v) {
				
				Intent i=new Intent(getApplicationContext(), InfoAktivnost.class);
			
					startActivity(i);
						
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.glavna_aktivnost, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem lista) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = lista.getItemId();
		if (id == R.id.settings_list) {
			return true;
		}
		return super.onOptionsItemSelected(lista);
	}
}
